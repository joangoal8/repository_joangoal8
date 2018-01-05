package common;

import javax.swing.JFrame;

public class AppFrame extends JFrame{

	/**
	 * @author joangoal
	 */
	
	private static final long serialVersionUID = -7292472018370382306L;

	public AppFrame(){
		this.setSize(300, 200);
		this.setTitle("Excel Statistics Writer");
		
		IWindow window = new IWindow(this);
		
		this.add(window);
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
