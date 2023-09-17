package com.example.alphavantageapi.aggregation;

public class AlphaVantageException extends RuntimeException {

    public AlphaVantageException() {
        super();
    }

    public AlphaVantageException(String message) {
        super(message);
    }

    public AlphaVantageException(String message, Throwable cause) {
        super(message, cause);
    }
}
