package com.tss.databaseconnection.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {

    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException(String resource, Object identifier) {

        super(resource + " not found: " + identifier,
                "RESOURCE_NOT_FOUND",
                HttpStatus.NOT_FOUND);

        logger.warn("{} not found with identifier={}", resource, identifier);
    }
}