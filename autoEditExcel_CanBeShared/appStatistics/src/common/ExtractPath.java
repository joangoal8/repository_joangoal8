package common;

public class ExtractPath {

	/**
	 * @author joangoal
	 */
	
	//DEFINE your excel path with the difference Options
	
	static public String getPath(String date, String partialPath, String excelPath, String selItem) {
		
		String path = "";
		String[] fieldDates = date.split("/");
		
		path = excelPath;
		path += partialPath;
		if(!"CO".equals(selItem)) {
			path += "-" + selItem + " 20" + fieldDates[2];
		}else {
			path += " 20" + fieldDates[2];
		}
		return path;
	}
}
