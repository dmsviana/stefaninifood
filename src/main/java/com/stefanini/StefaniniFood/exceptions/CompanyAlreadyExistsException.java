package com.stefanini.StefaniniFood.exceptions;

public class CompanyAlreadyExistsException extends RuntimeException{

    public CompanyAlreadyExistsException(String message){
        super(message);
    }

}
