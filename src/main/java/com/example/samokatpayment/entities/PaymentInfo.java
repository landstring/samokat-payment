package com.example.samokatpayment.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentInfo {
    String id;
    Payment payment;
    Long totalPrice;
    String uri;
}
