package com.stefanini.StefaniniFood.exceptions;

public class InvalidAuthenticationException extends RuntimeException{

    public InvalidAuthenticationException(String message){
        super(message);
    }
}
