package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import java.util.Date;
import java.util.GregorianCalendar;


public abstract class MyData {
	
	public static Date getLocaleData(){
		Date date = new Date();
		
		GregorianCalendar gc = new GregorianCalendar();
		date = gc.getTime();
		
		return date;
	}

}