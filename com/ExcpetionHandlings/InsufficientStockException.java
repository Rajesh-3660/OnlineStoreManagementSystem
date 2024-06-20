package com.ExcpetionHandlings;
public class InsufficientStockException extends RuntimeException{
     public InsufficientStockException(String message)
     {
        super(message);
     }
}
