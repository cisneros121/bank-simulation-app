package com.ccydeo.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String s) {
        super(s);
    }
}
