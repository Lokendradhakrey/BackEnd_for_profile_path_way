package com.lokendra.BackEnd_for_profile_path_way.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExceptionHandle.class)
    public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundExceptionHandle exceptionHandle) {
        String message = exceptionHandle.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
