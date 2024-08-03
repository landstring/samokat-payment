package com.example.samokatpayment.services;


import com.example.samokatpayment.DTO.PaymentInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    String initPayment(PaymentInfoDto paymentInfoDto);

    void successPayment(String paymentCode);

    void failurePayment(String paymentCode);

}
