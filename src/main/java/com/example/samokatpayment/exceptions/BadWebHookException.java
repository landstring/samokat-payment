package com.example.samokatpayment.exceptions;

public class BadWebHookException extends RuntimeException {
    public BadWebHookException() {
        super();
    }

    public BadWebHookException(String message) {
        super(message);
    }
}
