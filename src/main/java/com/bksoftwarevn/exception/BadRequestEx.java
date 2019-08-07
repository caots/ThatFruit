package com.bksoftwarevn.exception;


public class BadRequestEx extends RuntimeException {

    private String message;

    public BadRequestEx(String message) {
        super(message);
        this.message = message;
    }
}
