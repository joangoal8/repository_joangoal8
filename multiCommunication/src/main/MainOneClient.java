package main;

import java.io.IOException;

import server.Client;

public class MainOneClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = Integer.parseInt(args[1]);
		try {
			new Client(args[0], port);
		}catch(IOException io) {
			io.printStackTrace();
		}
		
	}

}
