package br.univille.projcolabassistant.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Util {
	
	public static Date toDate(String dateString) {
		if(dateString.isEmpty()) {
			return null;
		}
		
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = format.parse(dateString);
			
			return date;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
	
	public static String randomId() {
		return UUID.randomUUID().toString();
	}

}
