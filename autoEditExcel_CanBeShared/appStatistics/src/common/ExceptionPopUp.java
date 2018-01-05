package common;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExceptionPopUp extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3782238629811197392L;

	public ExceptionPopUp(String msg, Exception e){
		final JFrame parent = new JFrame();
        JButton button = new JButton();
        
        final String message = msg;
        
        JPanel panel = new JPanel();
        JLabel descError = new JLabel(message);
        
        descError.setBounds(10,20,80,25);
        panel.add(descError);
        
        button.setText("Details");
        
        panel.add(button);
        parent.add(panel);
        parent.pack();
        parent.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                JOptionPane.showMessageDialog(parent, errors, message, JOptionPane.ERROR_MESSAGE);
            }
        });
	}
}
