package com.example.samokatpayment.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentInfoDto {
    String card_number;
    String expiration_date;
    Integer cvc;
    Long totalPrice;
    String url;
}