package com.example.samokatpayment.services;


import com.example.samokatpayment.DTO.PaymentInfoDto;
import com.example.samokatpayment.DTO.PaymentStatusDto;
import com.example.samokatpayment.entities.PaymentInfo;
import com.example.samokatpayment.exceptions.BadWebHookException;
import com.example.samokatpayment.exceptions.WebHookConnectionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.UUID;

@Service
public class PaymentService {
    private static final String token = "PaymentPassword";
    private final HashMap<String, PaymentInfo> paymentInfoHashMap;

    public PaymentService() {
        paymentInfoHashMap = new HashMap<>();
    }

    public String initPayment(PaymentInfoDto paymentInfoDto) {
        String payment_code;
        do{
            payment_code = UUID.randomUUID().toString();
        } while (paymentInfoHashMap.containsKey(payment_code));
        if (urlCheck(paymentInfoDto.getUrl())){
            PaymentInfo paymentInfo = new PaymentInfo(
                    payment_code,
                    paymentInfoDto.getCard_number(),
                    paymentInfoDto.getExpiration_date(),
                    paymentInfoDto.getCvc(),
                    paymentInfoDto.getTotalPrice(),
                    paymentInfoDto.getUrl()
            );
            paymentInfoHashMap.put(payment_code, paymentInfo);
            return payment_code;
        }
        else{
            throw new BadWebHookException();
        }
    }

    public void sendStatus(String payment_code, String status) throws JsonProcessingException {
        PaymentStatusDto paymentStatusDto = new PaymentStatusDto(payment_code, status);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentStatusDto);
        try (HttpClient client = HttpClient.newHttpClient()){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(paymentInfoHashMap.get(payment_code).getUrl() + "/pay"))
                    .header("Authorization", token)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception ex){
            throw new WebHookConnectionException();
        }
    }

    private boolean urlCheck(String urlString) {
        try (HttpClient client = HttpClient.newHttpClient()){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString + "/check"))
                    .header("Authorization", token)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        }
        catch (Exception ex){
            throw new WebHookConnectionException();
        }
    }
}
