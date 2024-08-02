package com.example.samokatpayment.mappers;

import com.example.samokatpayment.DTO.PaymentInfoDto;
import com.example.samokatpayment.entities.PaymentInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentInfoMapper {
    private final PaymentMapper paymentMapper;

    public PaymentInfo fromDto(PaymentInfoDto paymentInfoDto) {
        return PaymentInfo.builder()
                .payment(paymentMapper.fromDto(paymentInfoDto.getPaymentDto()))
                .totalPrice(paymentInfoDto.getTotalPrice())
                .uri(paymentInfoDto.getUri())
                .build();
    }
}
