package com.pragma.user.infrastructure.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message) {
        super(message);
    }
}
