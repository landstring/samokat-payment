package com.example.samokatpayment.exceptions;

public class WebHookConnectionException extends RuntimeException {

    public WebHookConnectionException() {
    }

    public WebHookConnectionException(String message) {
        super(message);
    }
}
