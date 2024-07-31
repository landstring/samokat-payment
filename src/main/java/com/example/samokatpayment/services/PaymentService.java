package com.example.samokatpayment.services;


import com.example.samokatpayment.DTO.PaymentInfoDto;
import com.example.samokatpayment.DTO.PaymentStatusDto;
import com.example.samokatpayment.entities.PaymentInfo;
import com.example.samokatpayment.exceptions.BadWebHookException;
import com.example.samokatpayment.exceptions.PaymentNotFoundException;
import com.example.samokatpayment.exceptions.WebHookConnectionException;
import com.example.samokatpayment.mappers.PaymentInfoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service

public class PaymentService {

    private final String paymentPassword;
    private final HttpClient paymentHttpClient;
    private final Map<String, PaymentInfo> paymentInfoHashMap;
    private final PaymentInfoMapper paymentInfoMapper;

    public PaymentService(@Value("${payment-password}") String paymentPassword,
                          HttpClient paymentHttpClient,
                          PaymentInfoMapper paymentInfoMapper) {
        this.paymentPassword = paymentPassword;
        this.paymentHttpClient = paymentHttpClient;
        this.paymentInfoMapper = paymentInfoMapper;
        this.paymentInfoHashMap = new HashMap<>();
    }

    public String initPayment(PaymentInfoDto paymentInfoDto) {
        String payment_code;
        do {
            payment_code = UUID.randomUUID().toString();
        } while (paymentInfoHashMap.containsKey(payment_code));
        if (uriCheck(paymentInfoDto.getUri())) {
            PaymentInfo paymentInfo = paymentInfoMapper.fromDto(paymentInfoDto);
            paymentInfo.setId(payment_code);
            paymentInfoHashMap.put(payment_code, paymentInfo);
            return payment_code;
        } else {
            throw new BadWebHookException();
        }
    }

    public void sendStatus(String payment_code, String status) throws JsonProcessingException {
        if (!paymentInfoHashMap.containsKey(payment_code)){
            throw new PaymentNotFoundException();
        }
        PaymentStatusDto paymentStatusDto = PaymentStatusDto.builder()
                .paymentCode(payment_code)
                .status(status)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentStatusDto);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(paymentInfoHashMap.get(payment_code).getUri() + "/pay"))
                    .header("Authorization", paymentPassword)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            paymentHttpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            throw new WebHookConnectionException();
        }
    }

    private boolean uriCheck(String uriString) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uriString + "/check"))
                    .header("Authorization", paymentPassword)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception ex) {
            throw new WebHookConnectionException();
        }
    }
}
