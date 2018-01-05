package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayDateConverter {

	/**
	 * @author joangoal
	 */
	
	static public Date getDateDay(int day, String month, String year) {
		
		String data = null;
		Date parsedDate = null;
		if(day<10) {
			data = "0" + day + "/" + month + "/" + year; 
		}else {
			data = day + "/" + month + "/" + year;
		}

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			parsedDate = formatter.parse(data);
		}catch (ParseException e) {
			// TODO: handle exception
			System.out.println("no s'ha pogut parsejar");
		}

		return parsedDate;
	}
}
