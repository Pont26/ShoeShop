package com.shoeshop.Dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import annotation.Columns;
import annotation.Id;
import annotation.Table;
import util.Util;

public abstract class GeneralDao<T> {
	private ConnectionDao connectionDao;
	private Class<T> clazzType;
	
	public GeneralDao(Class<T> classType) {
		this.clazzType = classType;
		this.connectionDao = new ConnectionDao();
	}
	
	public abstract T convertToObject(ResultSet rs) throws SQLException, ClassNotFoundException, IOException ;

	
	//Table name from annotation
	private String getTableName() {
		Table tableAnnotation = clazzType.getAnnotation(Table.class);
		if(tableAnnotation != null) {
		return tableAnnotation.name();
		}else {
			throw new RuntimeException("Table annotation not found in class " + clazzType.getName());
		}
		
	}
	
	//id name from annotation
	private String getId() {
		for(Field field: clazzType.getDeclaredFields()) {
			if(field.isAnnotationPresent(Id.class)) {
				Id idAnnotation = field.getAnnotation(Id.class);
				return idAnnotation.name();
			}
		}	
		throw new RuntimeException("Id annotation not found in class " + clazzType.getName());
	}
	
	//columns from annotation
	private Object[] getColumns(T t) {
		List<Field> fields = Util.getAllFields(t.getClass());

		List<Object> values = new ArrayList<>();
		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(Columns.class)) {
					field.setAccessible(true);
					values.add(field.get(t));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return values.toArray();
	}
	
	private void setParameters(PreparedStatement sql, Object... objects) throws SQLException {
		int index = 1;
		for (Object param : objects) {
			sql.setObject(index++, param);
		}
	}
	
	private boolean execute(String sql, Object... params) throws ClassNotFoundException {
		try (Connection connection = connectionDao.connectionWithDb();
		    PreparedStatement stmt = connection.prepareStatement(sql)) 
		{
			setParameters(stmt, params);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
            throw new RuntimeException(e);
        } 
    }
	
	
	//INSERT INTO TABLE_NAME (ID,NAME,EMAIL) VLAUE (?,?..)
	  private String insertQuery(T t) {
			StringBuilder query = new StringBuilder("INSERT INTO ");
			query.append(getTableName()).append(" (");

			Field[] fields = Util.getAllFields(t.getClass()).toArray(new Field[0]);
			for (Field field : fields) {
				if (field.isAnnotationPresent(Columns.class)) {
					Columns column = field.getAnnotation(Columns.class);
					query.append(column.name()).append(", ");
				}
			}
			
			query.delete(query.length() - 2, query.length()); 
			query.append(") VALUES (");

			for (Field field : fields) {
				if (field.isAnnotationPresent(Columns.class)) {
					query.append("?, ");
				}
			}
			query.delete(query.length() - 2, query.length());
			query.append(")");
			return query.toString();
	}
	  

	public boolean insert(T t) throws ClassNotFoundException {
		String sql = insertQuery(t);
		return execute(sql, getColumns(t));
	}
	
	public T getById(int id) throws SQLException, ClassNotFoundException, IOException {
		 StringBuilder query = new StringBuilder("SELECT * FROM ");
		    query.append(getTableName()); 
		    query.append(" WHERE ").append(getId()).append(" = ?");
	    try (Connection connection = connectionDao.connectionWithDb();
	         PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
	        preparedStatement.setInt(1, id);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            return convertToObject(rs); 
	        }
	    }
	    return null; 
	}



	
}
