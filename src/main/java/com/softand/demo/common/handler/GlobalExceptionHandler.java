package com.softand.demo.common.handler;

import com.softand.demo.common.exception.ProductNotFoundException;
import com.softand.demo.common.exception.ValidationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @GraphQlExceptionHandler
    // public GraphQLError handleProductNotFoundException(ProductNotFoundException ex) {
    //     return GraphQLError.newError()
    //     .message(ex.getMessage())
    //     .build();
    // }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Robert
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
    //     Map<String, Object> response = new HashMap<>();
    //     response.put("timestamp", new Date());
    //     response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    //     response.put("error", "Internal Server Error");
    //     response.put("message", ex.getMessage());
    //     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    
}
