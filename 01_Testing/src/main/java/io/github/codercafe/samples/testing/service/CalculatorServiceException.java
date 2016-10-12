package io.github.codercafe.samples.testing.service;

public class CalculatorServiceException extends RuntimeException {
    public CalculatorServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
