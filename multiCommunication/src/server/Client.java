package server;

import java.io.*;
import java.net.*;

import commons.FrameClient;

public class Client extends Thread{
		
		private Socket socket;
		private String name;
		private String message;
		private String messageReceived;
		
		public Client(String dnsName, int port) throws IOException{
			this.socket = new Socket(dnsName, port);
			this.name = "Client" + socket.getLocalPort();
			FrameClient frame = new FrameClient(this);
		}
		
		public Client (Socket socket) {
			this.socket = socket;
			this.name = "Client" + socket.getLocalPort();
		}
		
		
		public void run(){
			
			if(this.hasMessageToReceive() && this.socket.isConnected()) {
				System.out.println("Connecting...");
				
				System.out.println("Connection succesful!");
				try {
					DataInputStream in = new DataInputStream(socket.getInputStream());
					
					System.out.println("Receiving information...");
					
					String test= in.readUTF();
					
					System.out.println(test);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(this.hasMessageToSend() && this.socket.isConnected()) {
				System.out.println("Connecting...");
				
				System.out.println("Connection succesful!");
				try {
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					
					System.out.println("Receiving information...");
					
					out.writeUTF(this.getMessage());
			
				}catch(Exception e) {
					e.printStackTrace();
				}
			}	
		}

		public String getNameSocket() {
			return name;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getMessageReceived() {
			return messageReceived;
		}

		public void setMessageReceived(String messageReceived) {
			this.messageReceived = messageReceived;
		}

		public boolean hasMessageToSend() {
			
			boolean result = true;
			
			if (this.getMessage() == null || "".equals(this.getMessage())) {
				result = false;
			}
			return result;
		}
		
		public boolean hasMessageToReceive() {
			
			boolean result = true;
			
			if (this.getMessageReceived() == null || "".equals(this.getMessageReceived())) {
				result = false;
			}
			return result;
		}
}
