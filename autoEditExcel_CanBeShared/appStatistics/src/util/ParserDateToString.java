package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserDateToString {
	
	/**
	 * @author joangoal
	 */
	
	static public String getStringDate(Date date) {
		
		String newDateString = null;
		
		if(date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			
			newDateString = formatter.format(date);
		}
		
		return newDateString;
	}
}
