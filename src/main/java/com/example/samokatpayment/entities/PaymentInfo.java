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
    String card_number;
    String expiration_date;
    Integer cvc;
    Long totalPrice;
    String url;
}
