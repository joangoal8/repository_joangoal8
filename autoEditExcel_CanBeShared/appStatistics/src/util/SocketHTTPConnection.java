package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
//import java.net.Socket;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import common.MapperURLDEC;

public class SocketHTTPConnection {
	
	/**
	 * @author joangoal
	 */
	
	public SocketHTTPConnection() {
		// TODO Auto-generated constructor stub
		
	}
	
	static public Map<String,String[]> getInfoTXT(String date, String canal) throws IOException {
		
		Map<String,String[]> mapFields = new HashMap<String, String[]>();
		
		try {
			boolean found = false;
			
			/* Define proxy settings if you have
			System.setProperty("http.proxyHost", "");
			System.setProperty("http.proxyPort", "8080");
			*/
			URL url = new URL(MapperURLDEC.getURLMapped(canal));
			
			Scanner scan = new Scanner(url.openStream());
			
		
			while(scan.hasNextLine()) {
				
				String fields = scan.nextLine();
				
				if(fields != null && !"".equals(fields)) {
					String field[] = fields.split(";");
					
					if(date.equals(field[0])) {
						String[] subArr = Arrays.copyOfRange(field, 1, (field.length));
						
						mapFields.put(field[0], subArr);

						found = true;
					}
				}
				
				if(found) break;
			}
			
			while(scan.hasNextLine() && found) {
				
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					
				String fields = scan.nextLine();
					
				Date dateIn = ParserDate.getParserDate(date);
				
				if(fields != null && !"".equals(fields)) {
					String field[] = fields.split(";");
						
					Date d = ParserDate.getParserDate(field[0]);
						
					if(dateIn.before(d)) {
						String[] subArr = Arrays.copyOfRange(field, 1, (field.length));
							
						mapFields.put(field[0], subArr);
					}
				}
				
			}
			
			scan.close();
			
		}catch(IOException i){
			i.printStackTrace();
			System.out.println("Exception in SocketHTTPConnection");
		}
		
		return mapFields;
	}
}
