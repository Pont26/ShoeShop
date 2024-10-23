package com.shoeshop.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shoeshop.Model.Customer;
import com.shoeshop.Model.Purchase;
import com.shoeshop.Model.Shoe;

public class PurchaseDao extends GeneralDao<Purchase> {
	  private ShoeDao shoeDao = new ShoeDao();
	  private CustomerDao customerDao = new CustomerDao();
	  private ConnectionDao connectionDao;
	public PurchaseDao() {
		super(Purchase.class);
		this.connectionDao = new ConnectionDao(); 
		
	}

	@Override
	public Purchase convertToObject(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		 Shoe shoe = shoeDao.getById(rs.getInt("shoe_id"));
		Customer customer= customerDao.getById(rs.getInt("customer_id"));
		 int qty = rs.getInt("qty"); 
		return new Purchase(shoe,customer,qty);
	
	}
	
	public boolean insertPurchase(Purchase purchase) throws ClassNotFoundException, IOException {
	    String sql = "INSERT INTO purchases (shoe_id, customer_id, qty, total_price) VALUES (?, ?, ?, ?)";

	    try (Connection connection = connectionDao.connectionWithDb();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        
	        preparedStatement.setInt(1, purchase.getShoe().getId());
	        preparedStatement.setInt(2, purchase.getCustomer().getId());
	        preparedStatement.setInt(3, purchase.getQty());
	        preparedStatement.setDouble(4, purchase.getTotalPrice()); 
	        
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}

	
	
	
	

    

}
