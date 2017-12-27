package main;

import java.io.IOException;
import java.net.*;

import server.Client;

public class MainClients {
	
	static final int port = 8088;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int i = 1;
		//int portAccess;
		
		while ( i < 3) {
			System.out.println("Peto aqui");
			
			//portAccess = port + i;
			Socket socket = new Socket("localhost", port);
			
			System.out.println("Socket info=" + " address :" + socket.getInetAddress() + " port :" + socket.getPort()
			+ " and localPort :" + socket.getLocalPort());
			
			new Client(socket).start();
			
			i++;
		}
		
	}

}
