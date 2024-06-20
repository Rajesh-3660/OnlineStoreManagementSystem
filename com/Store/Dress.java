package com.Store;


public class Dress extends Product{
    private int size;
    private String color;
    private String gender;
    private String category;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    double productPrice() {
        return getPrice();
    }

    @Override
    public String toString() {
        return " "+getProductId() + "\t\t|  "+size + "\t\t|  "+ color + "\t\t|  " + gender + "\t\t\t|  " + category+"\t\t|  "+getPrice()+"\t|";
    }
}
