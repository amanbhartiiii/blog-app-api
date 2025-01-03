package com.bharti.blog_app_api.exception;

import com.bharti.blog_app_api.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String field = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            response.put(field, message);
        });
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<?> methodArgumentMisMatchException(MethodArgumentTypeMismatchException e){
//        String field = e.getName();
//        String message = e.getMessage();
//        ApiResponse response = new ApiResponse(message, false);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}