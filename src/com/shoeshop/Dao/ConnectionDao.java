package com.shoeshop.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionDao {
		public Connection connectionWithDb() throws ClassNotFoundException, SQLException, IOException {
			InputStream inStream = getClass().getClassLoader().getResourceAsStream("db.properties");
			if (inStream == null) {
			    throw new FileNotFoundException("Property file 'db.properties' not found in the classpath.");
			}
			Properties prop = new Properties();
			prop.load(inStream);

			
			String url= prop.getProperty("db.url");
			String user = prop.getProperty("db.user");
			String password= prop.getProperty("db.password");
			
			Connection c= DriverManager.getConnection(url,user,password);
			
			return c;
		
	}

}
