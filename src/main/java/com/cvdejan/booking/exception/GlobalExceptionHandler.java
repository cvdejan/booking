package com.cvdejan.booking.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body=new HashMap<>();
        body.put("error: ", ex.getMessage());
        return new ResponseEntity<>(body,headers,status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body=new HashMap<>();
        body.put("error: ", ex.getMessage());
        body.put("content: ", ex.getContentType());
        return new ResponseEntity<>(body,headers,status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body=new HashMap<>();
        List<String> errors=ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors",errors);
        return new ResponseEntity<>(body,headers,status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error=new ApiError(Arrays.asList(ex.getMessage()),HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException ex){
        //Map<String, Object> body=new HashMap<>();
        List<String> errors=ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        //body.put("errors",errors);
        ApiError error=new ApiError(errors,HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Object> handleCityNotFound(ResourceNotFoundException ex, WebRequest request){
        ApiError error=new ApiError(Arrays.asList(ex.getMessage()),HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<Object> handleInvalidToken(InvalidTokenException ex, WebRequest request){
        ApiError error=new ApiError(Arrays.asList(ex.getMessage()),HttpStatus.FORBIDDEN, LocalDateTime.now());
        return new ResponseEntity<Object>(error,HttpStatus.FORBIDDEN);
    }

}
