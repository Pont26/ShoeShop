package com.shoeshop.controller;



/*
import java.io.IOException;

import com.shoeshop.Model.Customer;
import com.shoeshop.service.impl.CustomerServiceImpl;

public class CustomerController extends BaseController {
    private CustomerServiceImpl customerServiceImpl;
    public CustomerController() throws IOException {
        this.customerServiceImpl=new CustomerServiceImpl();
        System.out.println("Welcome to Customer Master Dashboard!!");
        System.out.println("Enter menu number that you want to perform::\n " +
                "1. Customer Registration\n ");
        int menu=Integer.parseInt(br.readLine());
        switch (menu) {
                case 1:
                    registerCustomer();
                    break;
                default:
                    System.out.println("Invalid Option!!");
                    break;

        }

    }
    public void registerCustomer() throws IOException {
        Customer customer=getCustomerInformation();
        if(this.customerServiceImpl.saveCustomer(customer)){
            System.out.println("Customer Registered Successfully!!");
        }else{
            System.out.println("Failed to Register Customer!!");
        }
    }
   

}
*/