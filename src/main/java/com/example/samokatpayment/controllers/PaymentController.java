package com.example.samokatpayment.controllers;

import com.example.samokatpayment.DTO.PaymentInfoDto;
import com.example.samokatpayment.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@Tag(name = "Методы для работы с оплатой заказа")
@RequestMapping("/api")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(
            summary = "Инициализация оплаты",
            description =
                    "Данный метод создаёт платёжную операцию и возвращает его id. " +
                            "На вход принимаются данные о банковской карте, сумма платежа и ссылка для обратной связи"
    )
    @PostMapping("/init")
    @ResponseStatus(HttpStatus.OK)
    public String initPayment(
            @RequestBody PaymentInfoDto paymentInfoDto) {
        log.info("Запрос на инициализацию платежа: {}", paymentInfoDto);
        return paymentService.initPayment(paymentInfoDto);
    }

    @Operation(
            summary = "Успешная оплата",
            description =
                    "Данный метод фиксирует успешную оплату"
    )
    @GetMapping("/{payment_code}/success")
    @ResponseStatus(HttpStatus.OK)
    public void successPay(
            @PathVariable("payment_code") String paymentCode) {
        log.info("Фиксация успешной оплаты для заказа с payment_code: {}", paymentCode);
        paymentService.successPayment(paymentCode);
    }

    @Operation(
            summary = "Неспешная оплата",
            description =
                    "Данный метод фиксирует неуспешную оплату"
    )
    @GetMapping("/{payment_code}/failure")
    @ResponseStatus(HttpStatus.OK)
    public void failurePay(
            @PathVariable("payment_code") String paymentCode) {
        log.info("Фиксация неуспешной оплаты для заказа с payment_code: {}", paymentCode);
        paymentService.failurePayment(paymentCode);
    }
}
