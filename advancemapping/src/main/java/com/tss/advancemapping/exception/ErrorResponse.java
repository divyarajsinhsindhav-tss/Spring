package com.tss.advancemapping.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@NoArgsConstructor
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private Instant timestamp;
    private String error;

    // For validation errors
    private Map<String, String> validationErrors;
}
