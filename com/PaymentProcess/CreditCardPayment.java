package com.PaymentProcess;

public class CreditCardPayment implements PaymentMethod
{
   private String cardNo;
   private String cardHolderName;

    public CreditCardPayment()
   {
        cardNo=" ";
        cardHolderName=" ";
   }

    public CreditCardPayment(String cardNo,String cardHolderName) {
        this.cardNo = cardNo;
        this.cardHolderName=cardHolderName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void paymentProcess(double amount) {
        System.out.println("Processing credit card payment of "+amount);
    }
}