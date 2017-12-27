package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class Server extends ServerSocket {
	
	private Map<String, String> map;
	
	public Server(int port, int backlog, InetAddress addr) throws IOException{
		super(port,backlog,addr);
		map = new HashMap<String, String>();
	}

	public Map<String, String> getMap() {
		return map;
	}
	
	public String toStringMap(){
		
		Map<String, String> map = this.getMap();
		StringBuilder stringMap= new StringBuilder();
		
		for (String key : map.keySet()) {
			stringMap.append(key + ":" + map.get(key) + ", ");
		}
		
		return stringMap.toString();
	}

}
