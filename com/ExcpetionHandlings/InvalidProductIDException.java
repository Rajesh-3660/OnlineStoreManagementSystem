package com.ExcpetionHandlings;

public class InvalidProductIDException extends RuntimeException{

    public InvalidProductIDException(String message)
    {
        super(message);
    }
}
