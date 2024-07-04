package com.shivansh.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDoctorFoundException extends RuntimeException {

    public NoDoctorFoundException(String message) {
        super(message);
    }

    public NoDoctorFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}