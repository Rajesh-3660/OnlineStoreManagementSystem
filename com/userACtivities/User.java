package com.userACtivities;

import com.Store.Dress;
import com.Store.ElectronicDevice;
import com.Store.Store;

public class User{
    String name;
    String email;
    String password;

    public User(){}

    public User(String name,String email,String password)
    {
        this.name=name;
        this.email=email;
        this.password=password;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean credentials(String name,String email,String password)
    {
        if((this.name).equalsIgnoreCase(name) && (this.password).equalsIgnoreCase(password) || (this.email).equalsIgnoreCase(email) && (this.password).equalsIgnoreCase(password))
        {
             return true;
        }
        return false;
    }

    //display the dresslist
    public void dressesList()      
    {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("Product ID\t|  Size\t\t|  Color\t\t|  Gender\t\t|  Category\t| Price\t\t|");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (Dress dress : Store.dresses) {
            System.out.println(dress);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

     // display the electronic devices list 
    public void electronicDevicesList()  
    {  
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Product ID\t|  Model\t|  Size\t\t|  RAM Size\t|  ROM Size\t| Color\t|");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (ElectronicDevice electronic : Store.electronicsDevices) 
        {
            System.out.println(electronic);
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

     //searching item in the dresses
    public int searchItemInCloth(int productID) 
    {  

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("Product ID\t|  Size\t\t|  Color\t\t|  Gender\t\t|  Category\t| Price\t\t|");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for(Dress dress: Store.dresses)
        {
            if(dress.getProductId()==productID)
            {
                System.out.println(dress);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                return dress.getProductId();
            }
        }
       
        return -1;
    }

     //searching item in the dresses
    public int searchItemInElectronic(int productID) 
    {  

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Product ID\t|  Model\t|  Size\t\t|  RAM Size\t|  ROM Size\t| Color\t|");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(ElectronicDevice elecDevice: Store.electronicsDevices)
        {
            if(elecDevice.getProductId()==productID)
            {
                System.out.println(elecDevice);
                System.out.println("-----------------------------------------------------------------------------------");
                return elecDevice.getProductId();
            }
        }
     
        return -1;
    }


    // display the user requrirements product for dress (filtering)
    public boolean categoryBasedShowListForDress(String gender,String category)
    {
        int flag=0;  // for if user requirements is not available
        for (Dress dress : Store.dresses) {
            if(dress.getGender().equalsIgnoreCase(gender) && dress.getCategory().equalsIgnoreCase(category))
             {
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("Product ID\t|  Size\t\t|  Color\t\t|  Gender\t\t|  Category\t| Price\t\t|");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println(dress);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                flag=1;
                break;
             }
        }
        if(flag!=0)
        {  
            return true; // if available
        }
        return false; // if not available
    }

     // display the user requrirements product for Electronic Device
    public boolean categoryBasedShowListForElectronicDevice(String category)
    {
        int flag=0;  // for if user requirements is not available
        for (ElectronicDevice eleDevice : Store.electronicsDevices) 
        {
            if( eleDevice.getProductName().equalsIgnoreCase(category))
             {
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.println("Product ID\t|  Model\t|  Size\t\t|  RAM Size\t|  ROM Size\t| Color\t|");
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.println(eleDevice);
                System.out.println("-------------------------------------------------------------------------------------------");
                flag=1;
                break;
             }
        }
        if(flag!=0)
        {  
            return true; // if available
        }
        return false; // if not available
    }

}