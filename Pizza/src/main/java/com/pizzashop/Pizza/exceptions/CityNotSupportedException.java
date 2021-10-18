package com.pizzashop.Pizza.exceptions;

public class CityNotSupportedException extends RuntimeException {
    public CityNotSupportedException(String message) {
        super(message);
    }
}
