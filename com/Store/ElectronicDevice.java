package com.Store;

public class ElectronicDevice extends Product{
    private String model;
    private String size;
    private String RAMSize;
    private String ROMSize;
    private String color;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRAMSize() {
        return RAMSize;
    }

    public void setRAMSize(String rAMSize) {
        RAMSize = rAMSize;
    }
    
    public String getROMSize() {
        return ROMSize;
    }

    public void setROMSize(String rOMSize) {
        ROMSize = rOMSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    double price()
    {
        return getPrice();
    }

    @Override
    public String toString() {
        return " "+getProductId() + "\t\t|  " + model + "\t|  " + size + "\t|  " + RAMSize + "\t\t|  "+ ROMSize+"\t|  " + getStockQuantity() + "\t\t|  " + color +"\t\t|";
    }
    
}
