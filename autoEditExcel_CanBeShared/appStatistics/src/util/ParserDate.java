package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserDate {

	/**
	 * @author joangoal
	 */
	
	static public Date getParserDate(String date) {
		
		Date newDate = null;
		
		if (date != null && !"".equals(date)) {
			String[] d = date.split("/");
			
			if(d.length==3) {
				int day = Integer.parseInt(d[0]);
				
				int month = Integer.parseInt(d[1]);
				
				int year = Integer.parseInt(d[2]);
				
				newDate = new Date(day, month, year);
				
			}
				
		}
	
		return newDate;
	}
	
	static public Date getDatefromString(String date) {
		
		Date newDate = null;
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date parsedDate = formatter.parse(date);
		}catch (ParseException e) {
			// TODO: handle exception
			System.out.println("no s'ha pogut parsejar");
		}
		
	
		return newDate;
	}
}
