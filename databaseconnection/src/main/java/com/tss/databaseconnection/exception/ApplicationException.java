package com.tss.databaseconnection.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
public abstract class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;

    public ApplicationException(String message, String errorCode, HttpStatus status){
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
