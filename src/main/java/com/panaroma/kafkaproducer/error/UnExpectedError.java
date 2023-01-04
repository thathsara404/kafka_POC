package com.panaroma.kafkaproducer.error;

public class UnExpectedError extends RuntimeException{

    public UnExpectedError(String message, Throwable cause) {
        super(message, cause);
    }

}
