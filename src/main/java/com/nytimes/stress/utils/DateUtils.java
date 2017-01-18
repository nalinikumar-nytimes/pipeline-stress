package com.nytimes.stress.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {
	
	//This is not threadsafe
	//static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	private static Log m_log = LogFactory.getLog(DateUtils.class);
	
	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	
	private static final ThreadLocal<SimpleDateFormat> df = new ThreadLocal<SimpleDateFormat>(){
	    @Override
	    protected SimpleDateFormat initialValue() {
	        return new SimpleDateFormat(FORMAT);
	    }
	 };
	
	 
	
	public static long getDateInMillis(String date){
		
		try {
			m_log.info(date);
			//Date date = fmt.parse("2016-03-08T22:50:02.577Z");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.get().parse(date));
			return calendar.getTimeInMillis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			m_log.error(date + ":is INVALID");
			m_log.info(ExceptionUtils.getFullStackTrace(e));
			return -1;
			
		}
	}
	
	
	public static synchronized String getCurrentTimestamp(){
		Calendar c = Calendar.getInstance();
		return df.get().format(c.getTime());
		//c.getTime()
		//D
	}
	
	public static void main(String[] args) {
		System.out.println(getDateInMillis("2016-03-08T23:12:03.540Z"));
	}

}
