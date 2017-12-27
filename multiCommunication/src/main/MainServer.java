package main;

import java.io.*;
import java.net.*;

import javax.print.attribute.EnumSyntax;

import server.Server;
import utils.ParseMessageToIntroduce;

public class MainServer {

	static final int port = 8088;
	static Server serverSocket;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Starting server...");
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		serverSocket = new Server(port, 10, addr);
		System.out.println("Server started...");
		Socket socket = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		
		while(true) {
		
			socket = serverSocket.accept();
				
			System.out.println("Connection from: " + socket.getInetAddress() +":" + socket.getPort());
			
			/*out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF("Passant info");*/
			
			in = new DataInputStream(socket.getInputStream());
			
			String[] msg = ParseMessageToIntroduce.parseStringMessage(in.readUTF());
			
			if (msg.length > 0) {
				if (msg.length > 1) {
					serverSocket.getMap().put(msg[0], msg[1]);
				}
			}
			
			System.out.println("MAP : " + serverSocket.toStringMap());
			
			System.out.println("--------------Info passada--------------");
		
		}
	}

}
 