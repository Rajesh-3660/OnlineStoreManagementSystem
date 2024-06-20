package com.Store;

import com.ExcpetionHandlings.InsufficientStockException;

public class StoreServices 
{
    // stock checking for dress

    public void checkDressStock(int id)
    {
        for (Dress dress : Store.dresses) {
            if(dress.getProductId()==id)
             {
                 if(dress.getStockQuantity()==0 )
                 {
                    throw new InsufficientStockException("The product is out of stock...please visit some more time or buy the another product");
                 }
             }
        }
        return;
    }

    // stock checking for electronic device 
     public void checkDeviceStock(int id){
        for (ElectronicDevice eleDevice : Store.electronicsDevices) {
            if(eleDevice.getProductId()==id)
             {
                 if(eleDevice.getStockQuantity()==0)
                 {
                    throw new InsufficientStockException("The product is out of stock...please visit some more time or buy the another product");
                 }
             }
        }
        return;
    }
}
