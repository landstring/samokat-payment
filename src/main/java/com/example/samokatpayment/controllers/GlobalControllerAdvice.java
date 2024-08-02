package com.example.samokatpayment.controllers;

import com.example.samokatpayment.exceptions.BadWebHookException;
import com.example.samokatpayment.exceptions.PaymentNotFoundException;
import com.example.samokatpayment.exceptions.WebHookConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(WebHookConnectionException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String handleWebHookConnectionException(WebHookConnectionException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(BadWebHookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadWebHookException(BadWebHookException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePaymentNotFoundException(PaymentNotFoundException exception) {
        return exception.getMessage();
    }

}
