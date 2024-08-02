package com.example.samokatpayment.mappers;

import com.example.samokatpayment.DTO.PaymentDto;
import com.example.samokatpayment.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment fromDto(PaymentDto paymentDto) {
        return Payment.builder()
                .cvc(paymentDto.getCvc())
                .cardNumber(paymentDto.getCardNumber())
                .expirationDate(paymentDto.getExpirationDate())
                .build();
    }

}
