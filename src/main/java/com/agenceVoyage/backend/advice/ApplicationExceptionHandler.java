package com.agenceVoyage.backend.advice;


import com.agenceVoyage.backend.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleInvalidArgument(ConstraintViolationException exception) {

        exception.printStackTrace();

        Map<String, String> errorMap = new HashMap<String, String>();

        exception.getConstraintViolations().forEach(error -> {
            errorMap.put(
                    error.getPropertyPath().toString(), error.getMessage()
            );
        });
        return errorMap;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        exception.printStackTrace();
        Map<String, String> errorMap = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            errorMap.put(
                    err.getField(), err.getDefaultMessage()
            );
        });


        return errorMap;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserAdminErrors() {

        return ResponseEntity.status(BAD_REQUEST).body("User not found");

    }




//    @ResponseStatus(BAD_REQUEST)
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public Map<String, String> handleSqlIntegrationException(SQLIntegrityConstraintViolationException exception) {
//        return new HashMap<String, String>() {{
//            put("message", exception.getMessage());
//        }};
//    }



    // a function i use in the controller check
    public static Map<String, String> dtoErrorhandler(BindingResult bindingResult) {


            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<String, String>();

            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            return errorMap;

    }


//    @ResponseStatus(BAD_REQUEST)
//    @ExceptionHandler(RuntimeException.class)
//    public static String handleNotFoundErrors(RuntimeException exception) {
//
//        return exception.getMessage();
//
//    }

    // runtime exception handler gets executed along with method, constraint exception.


}



