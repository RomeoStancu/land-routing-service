package com.example.routing.exception;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String code) {
        super("Country not found: " + code);
    }
}
