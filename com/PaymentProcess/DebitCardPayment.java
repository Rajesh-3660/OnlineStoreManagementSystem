package com.PaymentProcess;

public class DebitCardPayment implements PaymentMethod
{
   private String cardNo;
   private String cardHolderName;
   private double amount;

   public DebitCardPayment()
   {
        cardNo=" ";
        cardHolderName=" ";
   }

    public DebitCardPayment(String cardNo,String cardHolderName,double amount) {
        this.cardNo = cardNo;
        this.cardHolderName=cardHolderName;
        this.amount=amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void paymentProcess(double amount) {
        System.out.println("Processing debit card payment of "+amount);
    }
}