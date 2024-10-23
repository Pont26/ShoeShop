package com.shoeshop.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.shoeshop.Dao.ShoeDao;
import com.shoeshop.Model.Shoe;

public class ShoeServiceImpl extends BaseServiceImpl {


    public ShoeDao shoeServiceDao;
    public ShoeServiceImpl() {
        this.shoeServiceDao=new ShoeDao();
    }
 
    public Shoe getShoeData() throws IOException, ClassNotFoundException, SQLException {
        Shoe shoe=null;
        do{
            System.out.println("Enter Your Ordered Shoe ID::");
            int id=Integer.parseInt(br.readLine());
            shoe=this.shoeServiceDao.getById(id);
            if(shoe==null){System.out.println("Enter Correct Shore ID!!");}
        }while(shoe==null);

        return shoe;
    }
	
	
}
