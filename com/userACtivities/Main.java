package com.UserACtivities;


import java.io.*;

import java.util.Scanner;
import com.ExcpetionHandlings.*;
import com.Store.*;
import com.PaymentProcess.*;

public class Main 
{
    // static is because, every can use these and change based on their requirements
    
    static Scanner scanner = new Scanner(System.in); // class level
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static boolean option = false;  // choosing options
    static boolean flag=false;
    static StoreServices storeService=new StoreServices(); // store service for services...chillakds
    
    public static void main(String[] args) throws IOException 
    {
        Store store=new Store();                // creating Store object in first because of the initial values 
        store.populateDresses();
        store.populateElectronicDevices();

        Admin admin=Admin.getInstance();
        Customer customer=Customer.getInstance();

        boolean purchased=false; // checking purchased or not
        System.out.println("\t\t\tWELCOME TO THE ONLINE STORE");
        System.out.print("Enter your role (admin or customer) : "); // Enter Role
        String role = scanner.nextLine();

        int customerThing=0;// for thank you purpose...(you can remove it in future)
        if (role.equalsIgnoreCase("admin")) 
        {
              adminActivities(admin); 
        } 
        else 
        {
            purchased=customerActivities(customer);
        }

        // admin activity
        if(purchased==true)
        {
            PaymentMethod payment=null;
            int id=0;
            if(customer.getProductName().equalsIgnoreCase("dress"))
            {
                id=customer.getProductID();
                admin.decreaseQuantityDress(id,customer);
              //  admin.dressesList(); // for the sake of to understand i.e., stock--
            }
            else if(customer.getProductName().equalsIgnoreCase("ElectronicDevice"))
            {
                id=customer.getProductID();
                admin.decreaseQuantityDevice(id,customer);
              //  admin.electronicDevicesList();
            }
            System.out.println("Please..Fill your details");
            System.out.println();
            System.out.print("Name : ");
            customer.setName(bufferedReader.readLine());
            System.out.print("Email : ");
            customer.setEmail(bufferedReader.readLine());
            System.out.print("Phone Number : ");
            customer.setPhoneNumber(bufferedReader.readLine());
            System.out.print("Address : ");
            customer.setAddress(bufferedReader.readLine());
            int paymentMethod=0;
            do{
                System.out.println("1.Credit Card Payment\t\t2.Debit Card Payment\t\t3.UPI Payment\t\t4.Cash On Delivery");
                System.out.print("Enter the Payment Methood : ");
                paymentMethod=scanner.nextInt();
                System.out.print("Do you want to change the Payment Method...(Yes/No) : ");
                String changePaymentMethod=scanner.next();
                option=changePaymentMethod.equalsIgnoreCase("yes") ? true : false; // ternary operation
            }while(option);

            switch (paymentMethod) {
                case 1:
                     payment=new CreditCardPayment(); // parent-child relationship (up-casting)
                     CreditCardPayment creditCardPayment= (CreditCardPayment)payment;  // down-casting
                     System.out.print("Enter your Card Number : ");
                     creditCardPayment.setCardNo(scanner.next());
                     System.out.print("Enter Holder Name : (fill the holder name as in your bank book)");
                     creditCardPayment.setCardHolderName(bufferedReader.readLine());
                     creditCardPayment.paymentProcess(customer.getPricePurchase());
                     try{
                        Thread.sleep(5000);
                     }
                    catch (Exception e) {
                        System.out.println(e.toString());
                     }
                    break;
                case 2:
                    payment=new DebitCardPayment(); // parent-child relationship (up-casting)
                    DebitCardPayment debitCardPayment= (DebitCardPayment)payment;  // down-casting
                    System.out.print("Enter your Card Number : ");
                    debitCardPayment.setCardNo(scanner.next());
                    System.out.print("Enter Holder Name : (fill the holder name as in your bank book)");
                    debitCardPayment.setCardHolderName(bufferedReader.readLine());
                    System.out.println();
                    debitCardPayment.paymentProcess(customer.getPricePurchase());
                    try{
                        Thread.sleep(5000);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    payment=new UPIPayment(); // parent-child relationship (up-casting)
                    UPIPayment upiPayment= (UPIPayment)payment;  // down-casting
                    System.out.print("Enter UPI method (Phone Pay / Gpay / Amazaon Pay / Paytm) : ");
                    upiPayment.setUpiMethod(bufferedReader.readLine());
                    System.out.println();
                    upiPayment.paymentProcess(customer.getPricePurchase());
                    try{
                        Thread.sleep(5000);
                    }
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                     System.out.println("You need to pay 50rs...for delivery charges");
                     break;
            }
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(" Name  |Phone Number|Product Name\t|Original Price\t|Discount|Purchase Price |");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(customer);
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("\t\t\tThank you for buying the product...The product will be delivered to your address soon...");

        }
        else if(purchased==false && customerThing==0)
        {
                System.out.println("\t\tThank You For Visit our website...!");
        }
    }
                                                                                                                        
    // admin activities
    public static void adminActivities(Admin admin) throws IOException 
    {
        int action = 0,quantity=0,productID=0; // action= add || remove || search , quantity=stock 
        boolean duplicateID=false; // for ID duplication  checking

        Dress itemOfCloth = new Dress(); // added content will be stored here...(admin) add operation values for dresses
        ElectronicDevice itemOfElectronic = new ElectronicDevice(); // admin add operation for electronic devices

        // choose product to perform an action
        do{
            option=false;
            System.out.println();
            System.out.println("1.Clothes\t2.Electronic Devices\t3.Exit");
            System.out.println();
            int adminChoiceToPerformAction;

            // if admin enters an invalid option
            do {
                option=false;
                System.out.print("For which item you want to perform an action : "); // choose an product
                adminChoiceToPerformAction = scanner.nextInt();
                try {
                    CustomExceptionHandler.validateOption(adminChoiceToPerformAction);
                } catch (InvalidOptionException e) {
                    System.out.println("Exception occurred : " + e.getMessage());
                    System.out.println();
                    option = true;
                }
            } while (option);

            String addItemExsistOrNot=""; // checking whether the adding product is exsisting or not

            // An action can be performed based on the admin choice #product
            switch (adminChoiceToPerformAction) 
            {
                // for Dress
                case 1:
                            System.out.println();
                            System.out.println("1.Add\t2.Remove\t3.Search");
                            System.out.println();

                            // if entered an invalid option
                            do 
                            {
                                option = false;
                                System.out.print("Choose any one option : ");
                                action = scanner.nextInt();
                                try {
                                    CustomExceptionHandler.validateOption(action);
                                } catch (InvalidOptionException e) {
                                    System.out.println("Exception occurred : " + e.getMessage());
                                    System.out.println();
                                    option = true;
                                }
                            }while (option);

                            // for adding the product
                            if (action == 1)
                            {

                                System.out.print("Do you want to add an exsisting item...(Yes/No) : ");
                                addItemExsistOrNot=scanner.next();

                                // if admin wants to add an exsisting item stock
                                if(addItemExsistOrNot.equalsIgnoreCase("yes"))
                                {
                                    System.out.print("Enter how much stock want to add : ");
                                    quantity=scanner.nextInt();
                                    do{
                                        flag=false;
                                        System.out.print("Product ID : ");
                                        productID=scanner.nextInt();
                                        
                                        // check ID is valid or not
                                        try{
                                            CustomExceptionHandler.checkValidIdForDress(productID);
                                        }
                                        catch(InvalidProductIDException e){
                                            flag=true;
                                            System.out.println("Exception Occurred : "+e.getMessage());
                                            System.out.println();
                                        }
                                    }while(flag);

                                    admin.increaseItemQuantityForDress(productID,quantity);  //  increasing the stock based on the product ID
                                }
                                else   // if admin wants to add a new dress
                                {
                                    System.out.print("Product ID : ");
                                    itemOfCloth.setProductId(scanner.nextInt());
                                    System.out.print("Size : ");
                                    itemOfCloth.setSize(scanner.nextInt());
                                    System.out.print("Color : ");
                                    itemOfCloth.setColor(bufferedReader.readLine());
                                    System.out.print("Gender : ");
                                    itemOfCloth.setGender(scanner.next());
                                    System.out.print("Category : ");
                                    itemOfCloth.setCategory(bufferedReader.readLine());
                                    System.out.print("Brand : ");
                                    itemOfCloth.setBrand(bufferedReader.readLine());
                                    System.out.print("Price : ");
                                    itemOfCloth.setPrice(scanner.nextDouble());  
                                    System.out.print("Enter Stock : ");
                                    itemOfCloth.setStockQuantity(scanner.nextInt());                            
                                    // cheching the entered ID is duplicate or not
                                    do
                                    {
                                            duplicateID=false;
                                            try{
                                                CustomExceptionHandler.checkDuplicationOfIDForDress(itemOfCloth.getProductId());
                                            }
                                            catch(IDDuplicationException e)
                                            {
                                                duplicateID=true;
                                                System.out.println("Exception Occurred : "+e.getMessage());
                                            }                                        
                                            if(duplicateID==false){
                                                admin.addItemInCloth(itemOfCloth);
                                            }
                                            else{
                                                System.out.print("Enter Product ID : ");
                                                itemOfCloth.setProductId(scanner.nextInt());
                                            }
                                    }while(duplicateID);
                                }
                                    admin.dressesList();
                            } 
                            else if (action == 2) 
                            {
                                do
                                {
                                        flag=false;
                                        System.out.print("Product ID : ");
                                        productID=scanner.nextInt();
                                        
                                        // check ID is valid or not
                                        try{
                                            CustomExceptionHandler.checkValidIdForDress(productID);
                                        }
                                        catch(InvalidProductIDException e){
                                            flag=true;
                                            System.out.println("Exception Occurred : "+e.getMessage());
                                            System.out.println();
                                        }
                                }while(flag);
                                    
                                    // if ID is valid then remove the product otherwise the above loop will iterate infinitly
                                admin.removeItemInCloth(productID);
                                admin.dressesList();
                            } 
                            else if (action == 3) 
                            {
                                System.out.println();
                                do{
                                    flag=false;
                                    System.out.print("Product ID : ");
                                    productID=scanner.nextInt();
                                    
                                    // check ID is valid or not
                                    try{
                                        CustomExceptionHandler.checkValidIdForDress(productID);
                                    }
                                    catch(InvalidProductIDException e){
                                        flag=true;
                                        System.out.println("Exception Occurred : "+e.getMessage());
                                        System.out.println();
                                    }
                                }while(flag);
                                System.out.println("The dress is at : " + admin.searchItemInCloth(productID));                               
                            }
                    break;

                case 2:
                            // for Electronic Device
                            System.out.println("1.Add\t2.Remove\t3.Search");

                            do 
                            {
                                option = false;
                                System.out.print("Choose any one option : ");
                                action = scanner.nextInt();
                                System.out.println();
                                try {
                                    CustomExceptionHandler.validateOption(action);
                                } catch (InvalidOptionException e) {
                                    System.out.println("Exception occurred : " + e.getMessage());
                                    option = true;
                                }
                            } while (option);

                            if (action == 1) 
                            {
                                System.out.print("Do you want to add an exsisting product...(Yes/No) : ");
                                addItemExsistOrNot=scanner.next();

                                // if admin wants to add an exsisting product stock
                                if(addItemExsistOrNot.equalsIgnoreCase("yes"))
                                {
                                    System.out.print("Enter how much stock want to add : ");
                                    quantity=scanner.nextInt();
                                    do{
                                        flag=false;
                                        System.out.print("Product ID : ");
                                        productID=scanner.nextInt();
                                        
                                        // check ID is valid or not
                                        try{
                                            CustomExceptionHandler.checkValidIdForDevice(productID);
                                        }
                                        catch(InvalidProductIDException e){
                                            flag=true;
                                            System.out.println("Exception Occurred : "+e.getMessage());
                                            System.out.println();
                                        }
                                    }while(flag);
                                    admin.increaseItemQuantityForElectronicDevice(productID, quantity);
                                }
                                else
                                {
                                    System.out.println();
                                    System.out.print("Product ID : ");
                                    itemOfElectronic.setProductId(scanner.nextInt());
                                    System.out.print("Product Name : ");
                                    itemOfElectronic.setProductName(scanner.next());
                                    System.out.print("Model : ");
                                    itemOfElectronic.setModel(bufferedReader.readLine());
                                    System.out.print("Size : ");
                                    itemOfElectronic.setSize(bufferedReader.readLine());
                                    System.out.print("RAM Size : ");
                                    itemOfElectronic.setRAMSize(bufferedReader.readLine());
                                    System.out.print("ROM Size : ");
                                    itemOfElectronic.setROMSize(bufferedReader.readLine());
                                    System.out.print("Color : ");
                                    itemOfElectronic.setColor(bufferedReader.readLine());
                                    System.out.print("Brand : ");
                                    itemOfElectronic.setBrand(bufferedReader.readLine());
                                    System.out.print("Price : ");
                                    itemOfElectronic.setPrice(scanner.nextDouble());
                                    System.out.print("Stock : ");
                                    itemOfElectronic.setStockQuantity(scanner.nextInt());
                                    System.out.println();
                                    do{
                                        duplicateID=false;
                                        try{
                                            CustomExceptionHandler.checkDuplicationOfIDForDevice(itemOfElectronic.getProductId());
                                        }
                                        catch(IDDuplicationException e)
                                        {
                                            duplicateID=true;
                                            System.out.println("Exception Occurred : "+e.getMessage());
                                        }
                                        if(duplicateID==false){
                                            admin.addItemInElectronic(itemOfElectronic);
                                        }
                                        else{
                                            System.out.print("Enter Product ID : ");
                                            itemOfElectronic.setProductId(scanner.nextInt());
                                        }
                                    }while(duplicateID);
                                }
                                admin.electronicDevicesList();
                            } 
                            else if (action == 2) 
                            {
                                do{
                                    flag=false;
                                    System.out.print("Product ID : ");
                                    productID=scanner.nextInt();
                                    
                                    // check ID is valid or not
                                    try{
                                        CustomExceptionHandler.checkValidIdForDevice(productID);
                                    }
                                    catch(InvalidProductIDException e){
                                        flag=true;
                                        System.out.println("Exception Occurred : "+e.getMessage());
                                        System.out.println();
                                    }
                                }while(flag);
                                
                                admin.removeItemInElectronic(productID);
                                admin.electronicDevicesList();
                            } 
                            else if (action == 3) 
                            {
                                do{
                                    flag=false;
                                    System.out.print("Product ID : ");
                                    productID=scanner.nextInt();
                                    
                                    // check ID is valid or not
                                    try{
                                        CustomExceptionHandler.checkValidIdForDevice(productID);
                                    }
                                    catch(InvalidProductIDException e){
                                        flag=true;
                                        System.out.println("Exception Occurred : "+e.getMessage());
                                        System.out.println();
                                    }
                                }while(flag);
                                admin.searchItemInElectronic(productID);
                            }
                    break;

                    case 3:                       
                           System.exit(0);
        }
        if(adminChoiceToPerformAction!=3)
        {
            System.out.print("Do you want to do any action again...(Yes/No) : ");
            String continueTheAction=scanner.next();
            if(continueTheAction.equalsIgnoreCase("yes"))
            {
                option=true;
            }
        }
    }while (option);
    }

    // customer activities
    public static boolean customerActivities(Customer customer) 
    {
        String toBuy="";
        int flag=0; // checking stock for continue the next process
        System.out.println("1.Dresses\t2.Electronic Devices\t3.Exit");

        int choiceToChoose = 0;    // for checking invalid option 
        do {
            option = false; 
            System.out.print("Choose an product to buy : ");
            choiceToChoose = scanner.nextInt();
            try {
                CustomExceptionHandler.validateOption(choiceToChoose);
            } catch (InvalidOptionException e) {
                System.out.println("Exception occurred : " + e.getMessage());
                option = true;
            }
        } while (option);  // when out of option is entered....the loop will be iterated until user enter correct option

        switch (choiceToChoose)  // based on the option the case can be performed...
        {
            case 1:

                do{
                    // asking customer requirements
                    System.out.print("Which gender you are (male/female) : ");
                    String chooseGender=scanner.next();

                    System.out.print("Enter Category : ");
                    String chooseCategory=scanner.next();

                    // displaying the available dresse
                    System.out.println();
                    if(customer.categoryBasedShowListForDress(chooseGender, chooseCategory))
                    {
                        System.out.println("\tThese are the available dresses");
                        System.out.print("Do you want to buy it ?...(Yes/No) : ");
                        toBuy = scanner.next();
                        if (toBuy.equalsIgnoreCase("yes")) 
                        {
                            // enter product id for purchasing the product
                            System.out.print(" Please enter the dress ID : ");
                            int dressID=scanner.nextInt();

                            // stock checking based on the product ID
                            try{
                                flag=1;   // 
                                storeService.checkDressStock(dressID);
                            }
                            catch(InsufficientStockException e)
                            {
                                flag=0;
                                System.out.println("Exception Occurred : "+e.getMessage());
                            }

                            // if the product is in the stock then assign the product name and id to the customer for retreving easily
                            if(flag==1)
                            {                                                    
                                customer.setProductID(dressID);
                                customer.setProductName("dress");
                                return true;
                            }   
                        }
                        else if(toBuy.equalsIgnoreCase("no")) // if customer don't want to buy(they dosen't like the products) eg :- don't like the color
                        {
                             return false;
                        }
                    }
                    else  // if the product is out of stock...(the requrirements is no available)
                    {
                        System.out.println("Sorry...The product is out of stock...please visit some more time...Thank You");
                    }
                    System.out.print("Do you want to buy another product (Yes/No) : ");
                    toBuy=scanner.next();
                    if(toBuy.equalsIgnoreCase(toBuy))
                    {
                        option=true;
                    }
                    else return false;                                       
                }while (option);
                    break;
            case 2:
                  
                do{
                    System.out.print("Which electronic device want to buy (mobile/laptop) : ");
                    String chooseTheCategory=scanner.next(); 

                    System.out.println();
                    if(customer.categoryBasedShowListForElectronicDevice(chooseTheCategory))
                    {
                        
                        System.out.println("These are the available devices");
                        System.out.print("Do you want to buy ?...(Yes/No) : ");
                        toBuy = scanner.next();
                        if (toBuy.equalsIgnoreCase("yes")) 
                        {
                            System.out.print(" Please enter the device ID : ");
                            int deviceID=scanner.nextInt();

                            // stock checking based on the product ID
                            try{
                                flag=1;
                                storeService.checkDeviceStock(deviceID);
                            }
                            catch(InsufficientStockException e)
                            {
                                flag=0;
                                System.out.println("Exception Occurred : "+e.getMessage());
                            }

                            // if the product is in the stock then assign the product name and id to the customer for retreving easily
                            if(flag==1)
                            {                            
                                customer.setProductID(deviceID);
                                customer.setProductName("ElectronicDevice");
                                return true;
                            }
                        }
                        else if(toBuy.equalsIgnoreCase("no"))  // is they don't want to buy even the product is in the stock...eg, they don't like the product
                        {
                        return false;
                        }
                    }
                    else // if the product is out of stock...
                    {
                        System.out.println("Sorry...The product is out of stock...please visit some more time...Thank You");
                    }
                    System.out.println("Do you want to buy another product (Yes/No)");
                    toBuy=scanner.next();
                    if(toBuy.equalsIgnoreCase(toBuy))
                    {
                        option=true;
                    }
                    else return false;  
                }while (option);
        case 3: return false;
    }
    return false;
  }
}

