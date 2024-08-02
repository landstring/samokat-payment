package com.example.samokatpayment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {
    String id;

    @JsonProperty("card_number")
    String cardNumber;

    @JsonProperty("expiration_date")
    String expirationDate;
    Integer cvc;
}
