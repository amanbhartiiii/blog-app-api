package com.bharti.blog_app_api.exception;

public class InvalidUserDetailsException extends RuntimeException {
    String message;

    public InvalidUserDetailsException(String message) {
        super(message);
        this.message = message;
    }
}
