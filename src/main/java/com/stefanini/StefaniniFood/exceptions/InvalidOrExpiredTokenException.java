package com.stefanini.StefaniniFood.exceptions;

public class InvalidOrExpiredTokenException extends  RuntimeException{
    public InvalidOrExpiredTokenException(String message){
        super(message);
    }
}
