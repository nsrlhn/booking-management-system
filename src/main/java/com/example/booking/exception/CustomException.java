package com.example.booking.exception;

public abstract class CustomException extends RuntimeException {

    protected CustomException(String s) {
        super(s);
    }
}
