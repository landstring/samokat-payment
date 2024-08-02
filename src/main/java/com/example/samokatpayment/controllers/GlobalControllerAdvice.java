package com.example.samokatpayment.controllers;

import com.example.samokatpayment.exceptions.BadWebHookException;
import com.example.samokatpayment.exceptions.PaymentNotFoundException;
import com.example.samokatpayment.exceptions.WebHookConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({WebHookConnectionException.class,})
    public ResponseEntity<?> handleWebHookConnectionException(WebHookConnectionException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(BadWebHookException.class)
    public ResponseEntity<?> handleBadWebHookException(BadWebHookException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<?> handlePaymentNotFoundException(PaymentNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
