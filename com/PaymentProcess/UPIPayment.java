package com.PaymentProcess;

public class UPIPayment implements PaymentMethod
{
    private String upiMethod;

    public UPIPayment(){
        upiMethod=" ";
    }

    public UPIPayment(String upiMethod)
    {
        this.upiMethod=upiMethod;
    }

    public String getUpiMethod() {
        return upiMethod;
    }

    public void setUpiMethod(String upiMethod) {
        this.upiMethod = upiMethod;
    }

    @Override
    public void paymentProcess(double amount) 
    {
        System.out.println("Processing UPI payment of "+amount);
    }
}
