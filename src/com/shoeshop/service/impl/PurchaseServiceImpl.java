package com.shoeshop.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.shoeshop.Dao.PurchaseDao;
import com.shoeshop.Model.Customer;
import com.shoeshop.Model.Purchase;
import com.shoeshop.Model.Shoe;

public class PurchaseServiceImpl extends BaseServiceImpl {

    private CustomerServiceImpl customerServiceImpl;
    private ShoeServiceImpl shoeServiceImpl;
    
    public  PurchaseServiceImpl() throws IOException, ClassNotFoundException, SQLException {
    	customerServiceImpl=new CustomerServiceImpl();
    	shoeServiceImpl=new ShoeServiceImpl();
        String flag=null;
        do{
            doPurchase();
            System.out.println("Do you want to purchase more Yes or No??");
            flag=br.readLine();
        }while(flag.equals("yes"));

    }

    public Purchase doPurchase() throws IOException, ClassNotFoundException, SQLException {
        Customer customer=customerServiceImpl.getCustomerDataByEmail();
        Shoe shoe=shoeServiceImpl.getShoeData();
        System.out.println("Enter Qty of purchase::");
        int qty=Integer.parseInt(br.readLine());
        Purchase purchase=new Purchase(shoe,customer,qty);
        PurchaseDao purchaseDao = new PurchaseDao();
       // purchaseDao.insert(purchase);
        purchaseDao.insertPurchase(purchase);
        return purchase;
    }

	
	
}


