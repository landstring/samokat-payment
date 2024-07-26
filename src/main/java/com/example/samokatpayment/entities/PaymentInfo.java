package com.example.samokatpayment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentInfo {
    private String id;
    private String card_number;
    private String expiration_date;
    private Integer cvc;
    private Long totalPrice;
    private String url;
}
