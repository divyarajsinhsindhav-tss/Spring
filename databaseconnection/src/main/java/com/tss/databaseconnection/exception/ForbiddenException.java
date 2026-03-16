package com.tss.databaseconnection.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApplicationException {
    public ForbiddenException(String message) {
        super(message,"ACCESS_DENIED", HttpStatus.FORBIDDEN);
    }
}
