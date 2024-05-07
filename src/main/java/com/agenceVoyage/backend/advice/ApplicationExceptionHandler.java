package com.agenceVoyage.backend.advice;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleInvalidArgument(ConstraintViolationException exception) {

        Map<String, String> errorMap = new HashMap<String, String>();

        exception.getConstraintViolations().forEach( error -> {
            errorMap.put(
                    error.getPropertyPath().toString(), error.getMessage()
            );
        });
        return errorMap;
        }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        Map<String, String> errorMap = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach( err -> {
            errorMap.put(
                    err.getField(), err.getDefaultMessage()
            );
        });


        return errorMap;
    }
}



