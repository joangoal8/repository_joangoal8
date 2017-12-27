package commons;

import javax.swing.JFrame;

import server.Client;

public class FrameClient extends JFrame{
	
	private Client client;
	/**
	 * @author joangoal
	 */
	
	private static final long serialVersionUID = -7292472018370382306L;

	public FrameClient(Client client){
		this.client=client;
		
		this.setSize(300, 200);
		this.setTitle(this.client.getNameSocket());
		
		WindowClient window = new WindowClient(this);
		
		this.add(window);
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Client getClient() {
		return client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}