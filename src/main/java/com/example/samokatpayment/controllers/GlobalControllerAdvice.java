package com.example.samokatpayment.controllers;

import com.example.samokatpayment.exceptions.BadWebHookException;
import com.example.samokatpayment.exceptions.WebHookConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(WebHookConnectionException.class)
    public ResponseEntity<?> handleWebHookConnectionException(WebHookConnectionException ex){
        return new ResponseEntity<>("Ошибка подключения по адресу вебхука", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(BadWebHookException.class)
    public ResponseEntity<?> handleBadWebHookException(BadWebHookException ex){
        return new ResponseEntity<>("Некорректный адрес вебхука", HttpStatus.BAD_REQUEST);
    }
}
