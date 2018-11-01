package com.revolut.rate.common.exception;

public class InvalidBaseCurrencyException extends Exception {

    public InvalidBaseCurrencyException() {
        super("Invalid base currency");
    }
}
