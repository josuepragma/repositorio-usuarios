package com.pragma.user.infrastructure.exception;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    protected ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    protected ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidEmailException.class)
    protected ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
