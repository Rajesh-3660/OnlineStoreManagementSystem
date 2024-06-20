package com.ExcpetionHandlings;

import com.Store.Dress;
import com.Store.ElectronicDevice;
import com.Store.Store;

public class CustomExceptionHandler 
{
    
    
    // invalid option
    public static void validateOption(int userChoice) 
    {
        if (userChoice > 3) {
            throw new InvalidOptionException("The option is invalid...please try again");
        }
    }


// stock checking for dress 
     

     // duplication checking for dress
    public static void checkDuplicationOfIDForDress(int productID)
    {
        for(Dress dress:Store.dresses)
        {
            if(dress.getProductId()==productID)
            {
                throw new IDDuplicationException("The ID you entered is already available in the store...please enter another ID number");
            }
        }
        return; // if id is not duplicate....if we remove this line then the above exception will always thrown 
    }

    // duplication checking for electronic device
    public static void checkDuplicationOfIDForDevice(int productID)
    {
        for(ElectronicDevice eleDevice:Store.electronicsDevices)
        {
            if(eleDevice.getProductId()==productID)
            {
                throw new IDDuplicationException("The ID you entered is already available in the store...please enter another ID number");
            }
        }
        return;    
    }

    // when admin wants to add an exsisting item (for incresing stock of that product)...then check weather the ID is valid or invalid...for dress
    public static void checkValidIdForDress(int productID)
    {
        for(Dress dress: Store.dresses)
        {
            if(dress.getProductId()==productID)
            {
                return;
            }
        }
        throw new InvalidProductIDException("Invalid Product ID..Please enter a valid Product ID...");
    }

   // when admin wants to add an exsisting item (for incresing stock of that product)...then check weather the ID is valid or invalid...for electronic device
    public static void checkValidIdForDevice(int productID)
    {
        for(ElectronicDevice eleDevice: Store.electronicsDevices)
        {
            if(eleDevice.getProductId()==productID)
            {
                return;
            }
        }
        throw new InvalidProductIDException("Invalid Product ID..Please enter a valid Product ID...");
    }
}