package com.example.samokatpayment.controllers;

import com.example.samokatpayment.DTO.PaymentInfoDto;
import com.example.samokatpayment.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@Tag(name = "Методы для работы с оплатой заказа")
@RequestMapping("/api")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(
            summary = "Инициализация оплаты",
            description =
                    "Данный метод создаёт платёжную операцию и возвращает его id. " +
                    "На вход принимаются данные о банковской карте, сумма платежа и ссылка для обратной связи"
    )
    @PostMapping("/init")
    public ResponseEntity<?> initPayment(
           @RequestBody PaymentInfoDto paymentInfoDto){
        return new ResponseEntity<>(paymentService.initPayment(paymentInfoDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Успешная оплата",
            description =
                    "Данный метод фиксирует успешную оплату"
    )
    @GetMapping ("/{payment_code}/success")
    public ResponseEntity<?> successPay(
            @PathVariable("payment_code") String payment_code){
        try{
            paymentService.sendStatus(payment_code, "Success");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Неспешная оплата",
            description =
                    "Данный метод фиксирует неуспешную оплату"
    )
    @GetMapping ("/{payment_code}/failure")
    public ResponseEntity<?> failurePay(
            @PathVariable("payment_code") String payment_code){
        try{
            paymentService.sendStatus(payment_code, "Failure");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
