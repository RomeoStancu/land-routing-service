package com.example.routing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> handleCountryNotFound(CountryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", Instant.now().toString(),
                "error", "INVALID_COUNTRY",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<?> handleNoRouteFound(RouteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", Instant.now().toString(),
                "error", "NO_ROUTE",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "timestamp", Instant.now().toString(),
                "error", "INTERNAL_ERROR",
                "message", "Something went wrong"
        ));
    }
}
