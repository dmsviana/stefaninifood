package com.stefanini.StefaniniFood.handler;

import com.stefanini.StefaniniFood.exceptions.BadRequestException;
import com.stefanini.StefaniniFood.exceptions.CompanyAlreadyExistsException;
import com.stefanini.StefaniniFood.exceptions.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBadRequestException(BadRequestException bre) {
        return new ApiErrors(bre.getMessage());
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleClientNotFoundException(CompanyNotFoundException bre) {
        return new ApiErrors(bre.getMessage());
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleClientAlreadyExistsException(CompanyAlreadyExistsException bre) {
        return new ApiErrors(bre.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }


    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMissingRequestHeaderException(MissingRequestHeaderException bre) {
        return new ApiErrors(bre.getMessage());
    }
}