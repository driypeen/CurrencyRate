package com.driypeen.CurrencyRate.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
    public abstract HttpStatus getHttpStatus();
}