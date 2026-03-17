package com.tss.advancemapping.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApplicationException {
    public ForbiddenException(String message) {
        super(message,"ACCESS_DENIED", HttpStatus.FORBIDDEN);
    }
}
