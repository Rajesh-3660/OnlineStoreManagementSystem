package com.ExcpetionHandlings;
public class InvalidOptionException extends RuntimeException{

    public InvalidOptionException(String message)
    {
        super(message);
    }
}