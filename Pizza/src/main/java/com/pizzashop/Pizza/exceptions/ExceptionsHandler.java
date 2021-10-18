package com.pizzashop.Pizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(CustomerNotFoundException exception) {
        return new HttpErrorResponse(
                404,
                "Customer Not Found",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler({CityNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpErrorResponse handleGenericException(CityNotSupportedException exception) {
        return new HttpErrorResponse(
                400,
                "No Pizza Shop available in your City",
                LocalDateTime.now()
        );
    }
}