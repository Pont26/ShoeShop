package com.shoeshop.service.impl;

import java.io.IOException;

import com.shoeshop.Dao.CustomerDao;
import com.shoeshop.Model.Customer;

public class CustomerServiceImpl extends BaseServiceImpl {
    public CustomerDao customerDao;
    
    public CustomerServiceImpl(){
        this.customerDao=new CustomerDao();
    }
    
    
    public Customer getCustomerDataByEmail() throws IOException, ClassNotFoundException {
        Customer customer=null;
        do{
            System.out.println("Enter Your Registered Email!!");
            String email=br.readLine();
            customer=this.customerDao.findCustomerByEmail(email);
            if(customer==null){System.out.println("User does not exist int the System!!");}
        }while(customer==null);

        return customer;
    }

   }



