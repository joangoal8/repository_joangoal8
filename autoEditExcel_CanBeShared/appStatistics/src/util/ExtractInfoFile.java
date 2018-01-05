package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import common.ExceptionPopUp;

public class ExtractInfoFile {

	static public String getPathWorkSpace(File f){
		
		String path = "";
		
		if (f != null) {
			
			InputStream inpFile = null;
			
			try {
				
				inpFile = new FileInputStream(f);
				Scanner scan = new Scanner(inpFile);
				
				String line = "";
				while(scan.hasNextLine()) {
					line = scan.nextLine();

					if (line.contains("workspace=")) {
						String[] valueKey = line.split("=");
						if(valueKey.length > 1) {
							if (valueKey[1] != null && !"".equals(valueKey[1])) {
								path = valueKey[1];
							}
						}
					}
				}
			}catch(FileNotFoundException e) {
				String msg = "ERROR. No s'ha trobat el fitxer properties in ExtractInfoFile " + f.getPath();
				
				ExceptionPopUp popUp = new ExceptionPopUp(msg, e);
			}finally {
				if(inpFile != null) {
					try {
						inpFile.close();
					}catch(IOException io){
						String msg = "ERROR. No s'ha pogut tancar el InputStream in ExtractInfoFile";
						
						ExceptionPopUp popUp = new ExceptionPopUp(msg, io);
					}
					
				}
			}
		}
		
		return path;
	}
}
