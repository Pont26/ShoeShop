package com.shoeshop.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shoeshop.Model.Customer;

public class CustomerDao extends GeneralDao<Customer>{
	private ConnectionDao connectionDao;


	public CustomerDao() {
		super(Customer.class);
		connectionDao =new ConnectionDao();
		
	}

	@Override
	public Customer convertToObject(ResultSet rs) throws SQLException {
	
			int id =	rs.getInt("id");
			String name =	rs.getString("name");
			String email =	rs.getString("email");
			String phone = 	rs.getString("phone");
			String address =	rs.getString("address");
				
			return new Customer(id,name,email,phone,address);
	}
	
	public Customer findCustomerByEmail(String email) throws ClassNotFoundException, IOException {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE email = ?";
		 try (Connection connection = connectionDao.connectionWithDb();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {
			    stmt.setString(1, email);
			    ResultSet resultSet = stmt.executeQuery(); 
	            
	            if (resultSet.next()) { 
	                customer = convertToObject(resultSet); 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }

	        return customer; 
	    }
	}



