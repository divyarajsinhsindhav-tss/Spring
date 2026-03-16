package com.tss.databaseconnection.exception;

import org.springframework.http.HttpStatus;

public class BusinessRuleException extends ApplicationException{
    public BusinessRuleException(String message) {
        super(message,"BUSINESS_RULE_VOILATION", HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
