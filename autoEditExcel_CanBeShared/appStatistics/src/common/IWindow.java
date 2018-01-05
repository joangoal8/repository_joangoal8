package common;

import java.awt.Desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import util.ExtractInfoFile;
import util.ParserDateToString;
import util.SocketHTTPConnection;

public class IWindow extends JPanel {

	/**
	 * @author joangoal
	 */
	
	private static final long serialVersionUID = 8023937936035130899L;
	
	private static final String path = "\\DEC Documents 2011\\0.Gestió\\Estadístiques\\Estadistiques DEC";
	private static final String extension = ".xlsx";
	
	private String[] canalSelected = { "CO", "CA", "CN", "INT", "LO", "SCH" }; 

	public IWindow(AppFrame app) {
		
		final JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd/MM/yy"));

        this.add(picker);
        
        JLabel descLabel = new JLabel("<html><i>Specify the date that you desire to analyze</i></html>");
        //descLabel.setFont(new Font("Verdana", 1,10));;
        descLabel.setBounds(10,20,80,25);
        this.add(descLabel);
        
        JLabel excelLabel = new JLabel("<html> <h3>Path WorkSpace</h3> </html>");
        excelLabel.setBounds(10,20,80,25);
        this.add(excelLabel);

        final JComboBox canalBox = new JComboBox(canalSelected);
        canalBox.setBounds(150,20,250,50);
        this.add(canalBox);
        
        JButton textButton = new JButton("EditExcel");
        textButton.setBounds(10, 80, 80, 25);
        this.add(textButton);
        
        textButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				Map<String,String[]> mapFields = new HashMap<String, String[]>();
				// TODO Auto-generated method stub
				String pathExcel = "";
				try {
					mapFields = SocketHTTPConnection.getInfoTXT(ParserDateToString.getStringDate(picker.getDate()), canalBox.getSelectedItem().toString());
				}catch(IOException e) {
					String msg = "ERROR. HTTP Connection error. It cannot get INFO from " + MapperURLDEC.getURLMapped(canalBox.getSelectedItem().toString());
					
					ExceptionPopUp popUp = new ExceptionPopUp(msg, e);
				}
				
				String workSpaceExcel = "";
				
				try {
					String sourcePath = AppFrame.class.getProtectionDomain().getCodeSource().getLocation().toString();
					
					sourcePath = sourcePath.substring(sourcePath.indexOf("/"), sourcePath.lastIndexOf("/") + 1);
					
					File f = new File(sourcePath + "properties.txt");
					
					workSpaceExcel = ExtractInfoFile.getPathWorkSpace(f);
					
				}catch(Exception e) {
					
					File fi = new File("src/properties.txt");
					
					workSpaceExcel = ExtractInfoFile.getPathWorkSpace(fi);
				}
				
				//pathExcel = ExtractPath.getPath(ParserDateToString.getStringDate(picker.getDate()), path, excelText.getText(), canalBox.getSelectedItem().toString());
				pathExcel = ExtractPath.getPath(ParserDateToString.getStringDate(picker.getDate()), path, workSpaceExcel, canalBox.getSelectedItem().toString());
				
				pathExcel += extension;
				
				try {
										
					File file = new File(pathExcel);
					
					ExcelFileModificator.editExcel(file, ParserDateToString.getStringDate(picker.getDate()), mapFields);
					
				}catch(Exception e){
					String msg = "ERROR. Cannot modify excel";
					
					ExceptionPopUp popUp = new ExceptionPopUp(msg, e);
					
				}
				
				try {
					Desktop desc = Desktop.getDesktop();
					
					File fileToOpen = new File(pathExcel);
					
					desc.open(fileToOpen);
				}catch (Exception e) {
					// TODO: handle exception
					String msg = "ERROR. File cannot be opened in IWindow";
					
					ExceptionPopUp popUp = new ExceptionPopUp(msg, e);
					
				}
				
			}
		});
        
        JLabel authorLabel = new JLabel("<html> <br/><p>Created by: jgomezal@viewnext.com</p> </html>");
        this.add(authorLabel);
	}
}