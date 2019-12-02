package com.tokigames.assessment.flightsearch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
	
	public static Date timestampToDate(String timestamp) {
		try {
			return new Date(TimeUnit.SECONDS.toMillis(Long.parseLong(timestamp)));
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String timestampToDateString(String timestamp) {
		try {
			 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");  
			 Date date = new Date(TimeUnit.SECONDS.toMillis(Long.parseLong(timestamp)));
			 return dateFormat.format(date);  
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
