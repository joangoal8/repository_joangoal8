package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.rmi.CORBA.Util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.DayDateConverter;
import util.ParserDateToString;

public class ExcelFileModificator {
	
	/**
	 * @author joangoal
	 */
	
	static public void editExcel(File file, String date, Map<String,String[]> map) throws FileNotFoundException{
		
		if (map != null && !map.isEmpty()) {
			
			try {
				if (file == null) {
					throw new FileNotFoundException();
				}
				
				String titleSheet = ExcelFileModificator.getNameSheet(date);
				int daysMonth = ExcelFileModificator.getDays(date);
				
				InputStream inpFile = new FileInputStream(file);
				
				XSSFWorkbook workbook = new XSSFWorkbook(inpFile);
				
				inpFile.close();
				
				XSSFSheet sheet = workbook.getSheet(titleSheet);
				
				if(sheet == null) {
					initSheet(workbook, titleSheet, date);
		
					sheet = workbook.getSheet(titleSheet);
					updateDateSheet(sheet, daysMonth, date);
					clearDataSheet(sheet, daysMonth, map.get(date).length);

				}
				
				XSSFRow row = null;
				XSSFCell cell = null;
				String d;
				
				for(int i = 1; i <= daysMonth; i++) {
					row = sheet.getRow(i);
					
					if (row != null) {
						cell = row.getCell(0);
						
						Date data = cell.getDateCellValue();
						
						d = ParserDateToString.getStringDate(data);
						
						if(map.containsKey(d)) {
							for(int j=1; j <= map.get(d).length; j++) {
								cell = row.getCell(j);

								if (cell == null) {
									cell = row.createCell(j);
								}
								
								cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
								try {
									cell.setCellValue(Integer.parseInt(map.get(d)[j-1]));
								}catch(NumberFormatException n) {
									cell.setCellType(XSSFCell.CELL_TYPE_STRING);
									
									cell.setCellValue(map.get(d)[j-1]);
								}
								
								cell.setCellStyle(sheet.getRow(1).getCell(j).getCellStyle());
								
							}
						}
					}
				}
				
				FileOutputStream output = new FileOutputStream(file);
				workbook.write(output);
				output.close();
				
			}catch(Exception io) {
				String msg = "ERROR. File is already opened or output cannot be sent to File";
				
				ExceptionPopUp popUp = new ExceptionPopUp(msg, io);
				
			}
		}
	}
	
	static public String getNameSheet(String date) {
		String nameSheet = "";
		
		String[] fieldDates = date.split("/");
		
		int month = Integer.parseInt(fieldDates[1]);
		String year = fieldDates[2];
		
		switch (month) {
		case 1:
			nameSheet = "Ene-" + year;
			break;
		case 2:
			nameSheet = "Feb-" + year;
			break;
		case 3:
			nameSheet = "Mar-" + year;
			break;
		case 4:
			nameSheet = "Abr-" + year;
			break;
		case 5:
			nameSheet = "Mai-" + year;
			break;
		case 6:
			nameSheet = "Jun-" + year;
			break;
		case 7:
			nameSheet = "Jul-" + year;
			break;
		case 8:
			nameSheet = "Ago-" + year;
			break;
		case 9:
			nameSheet = "Sep-" + year;
			break;
		case 10:
			nameSheet = "Oct-" + year;
			break;
		case 11:
			nameSheet = "Nov-" + year;
			break;
		case 12:
			nameSheet = "Dec-" + year;
			break;
		default:
			break;
		}
		
		return nameSheet;
	}
	
	static public int getDays(String date) {
		int nameSheet = 0;
		
		String[] fieldDates = date.split("/");
		
		Integer month = Integer.parseInt(fieldDates[1]);
		int year = Integer.parseInt(fieldDates[2]);
		List<Integer> list31d = new ArrayList<Integer>();
		
		list31d.add(1); list31d.add(3); list31d.add(5); list31d.add(7); list31d.add(8); list31d.add(10); list31d.add(12);
		
		if(list31d.contains(month)) {
			nameSheet = 31;
		}else {
			if(month == 2) {
				if((year % 4) == 0) {
					nameSheet = 29;
				}else {
					nameSheet = 28;
				}
			}else {
				nameSheet = 30;
			}
		}
		
		return nameSheet;
	}
	
	static public void initSheet(XSSFWorkbook workbook, String title, String date){
		
		XSSFSheet newSheet = workbook.createSheet(title);
		
		String titleLastMonth = getLastMonthNameSheet(date);
		
		XSSFRow row = null;
		XSSFCell cell = null;
		
		XSSFRow newRow = null;
		XSSFCell newCell = null;
		
		XSSFSheet sheetLastMonth = workbook.getSheet(titleLastMonth);	
		for(int n = sheetLastMonth.getFirstRowNum(); n <= sheetLastMonth.getLastRowNum(); n++) {
			row = sheetLastMonth.getRow(n);
			
			newRow = newSheet.createRow(n);
			if ( row != null) {
				for(int m = row.getFirstCellNum(); m <= row.getLastCellNum(); m++) {
					cell = row.getCell(m);
					
					if(cell != null) {
						newCell = newRow.createCell(m);
						
						switch(cell.getCellType()) {
						case XSSFCell.CELL_TYPE_BLANK:
							break;
						case XSSFCell.CELL_TYPE_FORMULA:
							newCell.setCellType(cell.getCellType());
							
							newCell.setCellStyle(cell.getCellStyle());
							
							newCell.setCellFormula(cell.getCellFormula());
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							
							newCell.setCellType(cell.getCellType());
							
							newCell.setCellStyle(cell.getCellStyle());
							
							newCell.setCellValue(cell.getNumericCellValue());;
							break;
						case XSSFCell.CELL_TYPE_STRING:
							newCell.setCellType(cell.getCellType());
							
							newCell.setCellStyle(cell.getCellStyle());
							
							newCell.setCellValue(cell.getStringCellValue());;
							break;
						}
					}		
				}
			}	
		}	
	}
	
	static public void updateDateSheet(XSSFSheet sheet, int daysMonth, String date) {
		XSSFRow row = null;
		XSSFCell cell = null;
		
		String[] fieldDates = date.split("/");
		
		String day = fieldDates[0];
		String month = fieldDates[1];
		String year = fieldDates[2];
		
		if("01".equals(month)) {
			month = "12";
			year = String.valueOf(Integer.parseInt(year)-1);
		}else {
			month = String.valueOf(Integer.parseInt(month)-1);
		}
		
		String newDate = null;
		
		if(Integer.parseInt(day)<10) {
			newDate = '0' + day + "/" + month + "/" + year;
		}else {
			newDate = day + "/" + month + "/" + year;
		}
		
		int dayslastMonth = ExcelFileModificator.getDays(newDate);
		
		String action = "";
		if (dayslastMonth < daysMonth) {
			action = "create";
		}else if(dayslastMonth > daysMonth) {
			action = "delete";
		}
		
		Date d=null;
		
		for(int x = 1; x <= daysMonth; x++){	
			
			if(x > dayslastMonth && "create".equals(action)) {
				
				cell = row.createCell(0);
				d = DayDateConverter.getDateDay(x, fieldDates[1], fieldDates[2]);
				cell.setCellValue(d);
				
			}else {
				row = sheet.getRow(x);
				cell = row.getCell(0);
				
				d = DayDateConverter.getDateDay(x, fieldDates[1], fieldDates[2]);
				
				cell.setCellValue(d);
			}
		}
		if("delete".equals(action)) {
			for(int i = dayslastMonth + 1; i <= daysMonth; i++){
				row = sheet.getRow(i);
				cell = row.getCell(0);
				
				if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
					cell.setCellType(Cell.CELL_TYPE_BLANK);
				}
			}
		}
	}
	static public void clearDataSheet(XSSFSheet sheet,int daysMonth, int size) {
		XSSFRow row = null;
		XSSFCell cell = null;
		
		for(int i = 1; i <= daysMonth; i++){
			row = sheet.getRow(i);
			
			for(int j=1; j <= size; j++) {
				cell = row.getCell(j);
				
				if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
					cell.setCellType(Cell.CELL_TYPE_BLANK);
				}
			}
		}
	}
	
	static public String getLastMonthNameSheet(String date) {
		String nameSheet = "";
		
		String[] fieldDates = date.split("/");
		
		int month = Integer.parseInt(fieldDates[1]);
		String year = fieldDates[2];
		
		switch (month) {
		case 1:
			nameSheet = "Dec-" + String.valueOf(Integer.parseInt(year)-1);
			break;
		case 2:
			nameSheet = "Ene-" + year;
			break;
		case 3:
			nameSheet = "Feb-" + year;
			break;
		case 4:
			nameSheet = "Mar-" + year;
			break;
		case 5:
			nameSheet = "Abr-" + year;
			break;
		case 6:
			nameSheet = "Mai-" + year;
			break;
		case 7:
			nameSheet = "Jun-" + year;
			break;
		case 8:
			nameSheet = "Jul-" + year;
			break;
		case 9:
			nameSheet = "Ago-" + year;
			break;
		case 10:
			nameSheet = "Sep-" + year;
			break;
		case 11:
			nameSheet = "Oct-" + year;
			break;
		case 12:
			nameSheet = "Nov-" + year;
			break;
		default:
			break;
		}
		
		return nameSheet;
	}
	
	
}
