package com.Store;

import java.util.ArrayList;
public class Store 
{
   public static ArrayList<Dress> dresses=new ArrayList<>();
   public static ArrayList<ElectronicDevice> electronicsDevices= new ArrayList<>();
   
    public void populateDresses()
    {
        Dress cargo = new Dress();
        cargo.setSize(32);
        cargo.setColor("brown");
        cargo.setGender("male");
        cargo.setCategory("pant");
        cargo.setBrand("cargo");
        cargo.setProductId(1);
        cargo.setPrice(999);
        cargo.setStockQuantity(6);
        cargo.setDiscount(10);
        dresses.add(cargo);


        Dress linen = new Dress();
        linen.setSize(28);
        linen.setColor("green");
        linen.setGender("male");
        linen.setCategory("shirt");
        linen.setBrand("linen");
        linen.setProductId(2);
        linen.setPrice(1999);
        linen.setStockQuantity(3);
        linen.setDiscount(25);
        dresses.add(linen);


        Dress roadStar = new Dress();
        roadStar.setSize(32);
        roadStar.setColor("white");
        roadStar.setGender("male");
        roadStar.setCategory("pant");
        roadStar.setBrand("roadstar");
        roadStar.setProductId(3);
        roadStar.setPrice(999111);
        dresses.add(roadStar);

        Dress raymond = new Dress();
        raymond.setSize(32);
        raymond.setColor("pale pink");
        raymond.setGender("female");
        raymond.setCategory("pant");
        raymond.setBrand("raymond");
        raymond.setProductId(4);
        raymond.setPrice(699);
        raymond.setDiscount(15);
        dresses.add(raymond);

    }

    public void populateElectronicDevices()
    {
        ElectronicDevice acerAspireLite = new ElectronicDevice();
        acerAspireLite.setProductId(1);
        acerAspireLite.setProductName("Laptop");
        acerAspireLite.setModel("Acer Aspire");
        acerAspireLite.setSize("15.6 inches");
        acerAspireLite.setRAMSize("16 GB");
        acerAspireLite.setROMSize("1 TB");
        acerAspireLite.setColor("silver");
        acerAspireLite.setBrand("acer");
        acerAspireLite.setPrice(36699);
        acerAspireLite.setDiscount(7);
        acerAspireLite.setStockQuantity(4);
        electronicsDevices.add(acerAspireLite);

        ElectronicDevice hp13thGen = new ElectronicDevice();
        hp13thGen.setProductId(2);
        hp13thGen.setProductName("Laptop");
        hp13thGen.setModel("HP 13th Gen");
        hp13thGen.setSize("15.6 inches");
        hp13thGen.setRAMSize("16 GB");
        hp13thGen.setROMSize("512 GB");
        hp13thGen.setColor("black");
        hp13thGen.setBrand("HP");
        hp13thGen.setPrice(56999);
        electronicsDevices.add(hp13thGen);

        ElectronicDevice oppoA78 = new ElectronicDevice();
        oppoA78.setProductId(3);
        oppoA78.setProductName("Mobile");
        oppoA78.setModel("Oppo A 78");
        oppoA78.setSize("6 inches");
        oppoA78.setRAMSize("8 GB");
        oppoA78.setROMSize("128 GB");
        oppoA78.setColor("blue");
        oppoA78.setBrand("OPPO");
        oppoA78.setPrice(21990);
        oppoA78.setDiscount(15);
        oppoA78.setStockQuantity(6);
        electronicsDevices.add(oppoA78);
    }


}
