package com.example.samokatpayment.DTO;

import com.example.samokatpayment.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentStatusDto {

    @JsonProperty("payment_code")
    String paymentCode;

    PaymentStatusEnum status;
}