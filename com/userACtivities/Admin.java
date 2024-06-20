
package com.userACtivities;

import com.Store.Dress;
import com.Store.ElectronicDevice;
import com.Store.Store;

public class Admin extends User 
{
    public static Admin admin;
    private Admin(){}

    public static Admin getInstance()
    {
        if(admin==null)
        {
            admin=new Admin();
        }
        return admin;
    }

    public Admin(String userName, String email, String password) {
        super(userName, email, password);
    }

    public void addItemInCloth(Dress newCloth) // adding item in the dresses 
    {
          Store.dresses.add(newCloth); 
    }

    public void removeItemInCloth(int dressID)  //remove item in the dresses
    {
        for(Dress dress: Store.dresses)
        {
            if(dress.getProductId()==dressID)
            {
                Store.dresses.remove(dress);
                break;
            }
        }
    }

     public void addItemInElectronic(ElectronicDevice item) {  // adding item in electronic devices

        Store.electronicsDevices.add(item);
    }
   
    public void removeItemInElectronic(int productID)  //remove item in the dresses
    {
        for(ElectronicDevice eleDevice: Store.electronicsDevices)
        {
            if(eleDevice.getProductId()==productID)
            {
                Store.electronicsDevices.remove(eleDevice);
                break;
            }
        }
     }
     
     // add quatity stock for exsisting item for dress
    public void increaseItemQuantityForDress(int dressID,int quantityToAdd)
    {
        for(Dress dress: Store.dresses)
        {
            if(dress.getProductId()==dressID)
            {
                dress.setStockQuantity(dress.getStockQuantity()+quantityToAdd);
                break;
            }
        }
    }

    // add quantity stock for exsisting product for electronic device
    public void increaseItemQuantityForElectronicDevice(int productID,int quantityToAdd)
    {
        for(ElectronicDevice eleDevice: Store.electronicsDevices)
        {
            if(eleDevice.getProductId()==productID)
            {
                eleDevice.setStockQuantity(eleDevice.getStockQuantity()+quantityToAdd);
                break;
            }
        }
    }

    // for decreasing the quatity of a dress and discount calculation.
    public void decreaseQuantityDress(int dressID, Customer customer)
    {
        for(Dress dress: Store.dresses)
        {
            if(dress.getProductId()==dressID)
            {
                if(dress.getDiscount()!=0)
                {
                    customer.setOriginalPrice(dress.getPrice());
                    customer.setDiscountGetWhenBuy(dress.getDiscount());
                    customer.setPricePurchase(dress.getPrice() - ((dress.getDiscount()) * dress.getPrice()) / 100 );
                }
                else{
                    customer.setOriginalPrice(dress.getPrice());
                    customer.setPricePurchase(dress.getPrice());
                }
                dress.setStockQuantity(dress.getStockQuantity()-1);
                break;
            }
        }
    }

    // for decreasing the quatity of a electronic device and discount calculation
    public void decreaseQuantityDevice(int deviceID,Customer customer)
    {
        for(ElectronicDevice eleDevice: Store.electronicsDevices)
        {
            if(eleDevice.getProductId()==deviceID)
            {
                if(eleDevice.getDiscount()!=0)
                {
                    customer.setOriginalPrice(eleDevice.getPrice());
                    customer.setDiscountGetWhenBuy(eleDevice.getDiscount());
                    customer.setPricePurchase(eleDevice.getPrice() - ((eleDevice.getDiscount()) * eleDevice.getPrice()) / 100 );
                }
                else{
                    customer.setOriginalPrice(eleDevice.getPrice());
                    customer.setPricePurchase(eleDevice.getPrice());
                }
                eleDevice.setStockQuantity(eleDevice.getStockQuantity()-1);
                break;
            }
        }
    }
}
