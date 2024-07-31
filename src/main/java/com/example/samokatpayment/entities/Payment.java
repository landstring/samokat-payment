package com.example.samokatpayment.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    String cardNumber;
    String expirationDate;
    Integer cvc;
}
