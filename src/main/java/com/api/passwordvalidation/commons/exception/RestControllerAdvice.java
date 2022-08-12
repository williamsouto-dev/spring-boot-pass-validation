package com.api.passwordvalidation.commons.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ValidationException;

@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ValidationException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
