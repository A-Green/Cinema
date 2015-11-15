package com.epam.learning.springcore.cinema.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Look like date date format in the database somehow different , so I need this class  
//to be able to perform queries based on date comparison 
public class Formatter {
	
	private static final String format = "yyyy-MM-dd hh:mm";
	private static Format formatter = new SimpleDateFormat(format);
	
	public static Date getCurrentDate() {
		Date current = new Date(System.currentTimeMillis());
		String formatted = formatter.format(current);
		Date date;
		try {
			date = ((DateFormat) formatter).parse(formatted);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}	
}
