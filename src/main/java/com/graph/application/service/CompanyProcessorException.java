package com.graph.application.service;

public class CompanyProcessorException extends RuntimeException {
    public CompanyProcessorException(String message, Exception exception) {
        super(message, exception);
    }

    public CompanyProcessorException(String message) {
        super(message);
    }
}
