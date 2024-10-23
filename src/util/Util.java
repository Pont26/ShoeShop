package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Util {
	  public static List<Field> getAllFields(Class<?> clazz) {
	        List<Field> fields = new ArrayList<>();
	        while (clazz != null) {
	            for (Field field : clazz.getDeclaredFields()) {
	                fields.add(field);
	            }
	          //  clazz = clazz.getSuperclass(); 
	        }
	        return fields;
	    }

}
