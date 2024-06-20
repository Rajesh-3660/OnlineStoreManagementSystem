package com.userACtivities;

public class Customer extends User
{

    private String phoneNumber;
    private String address;
    private int productID;
    private String productName;
    private int quantityPurchased;
    private int discountGetWhenBuy;
    private double pricePurchase;
    private Double originalPrice;

    public static Customer customer;
    
    private Customer(){}

    public static Customer getInstance()
    {
        if(customer==null)
        {
            customer=new Customer();
        }
        return customer;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public Customer(String userName,String email,String password)
    {
        super(userName, email, password);
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
			this.address = address;
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public int getDiscountGetWhenBuy() {
        return discountGetWhenBuy;
    }

    public void setDiscountGetWhenBuy(int discountGetWhenBuy) {
        this.discountGetWhenBuy = discountGetWhenBuy;
    }

    public double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(double pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Override
    public String toString() 
    {
        return super.getName() + "|" + phoneNumber + "  |" + productName
                + "\t\t|"+ originalPrice + " \t\t|" + discountGetWhenBuy
                + "  \t|" + pricePurchase+" \t\t|";
    }
}
