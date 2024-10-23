package com.shoeshop.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shoeshop.Model.Shoe;

public class ShoeDao extends GeneralDao<Shoe>{
	public ShoeDao() {
		super(Shoe.class);
	}

	@Override
	public Shoe convertToObject(ResultSet rs) throws SQLException {
		return new Shoe(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getInt("size"),
				rs.getDouble("price")
				);
	}

}
