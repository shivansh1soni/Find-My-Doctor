package com.shivansh.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCityException extends RuntimeException {

    public InvalidCityException(String message) {
        super(message);
    }

    public InvalidCityException(String message, Throwable cause) {
        super(message, cause);
    }
}