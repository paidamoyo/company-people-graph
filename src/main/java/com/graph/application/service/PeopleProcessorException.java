package com.graph.application.service;

public class PeopleProcessorException extends RuntimeException {
    public PeopleProcessorException(String message, Exception exception) {
        super(message, exception);
    }

    public PeopleProcessorException(String message) {
        super(message);
    }
}
