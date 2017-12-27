package commons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowClient extends JPanel {
	private FrameClient frameClient;
	/**
	 * joangoal8
	 */
	private static final long serialVersionUID = -2509986433227856768L;

	public WindowClient(FrameClient frameClient) {
		this.frameClient=frameClient;
		
		JTextField keyTextField = new JTextField();
		//keyTextField.setBounds(150, 150, 150, 150);
		keyTextField.setPreferredSize(new Dimension(50,50));
		add(keyTextField);
		
		JTextField valueTextField = new JTextField();
		//valueTextField.setBounds(150, 150, 150, 150);
		valueTextField.setPreferredSize(new Dimension(50,50));
		add(valueTextField);
		
		JButton boton1=new JButton("Send Message");
        boton1.setBounds(10,50,50,25);
        add(boton1);
        
        boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String message;
				if(keyTextField.getText() != null && !"".equals(keyTextField.getText())) {
					message = keyTextField.getText() + "," + valueTextField.getText();
					frameClient.getClient().setMessage(message);
				}
				
				frameClient.getClient().start();
			}
		});
	}

	public FrameClient getFrameClient() {
		return frameClient;
	}
	
	
}
