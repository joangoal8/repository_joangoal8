package hashCodeTournamentMain;

import java.util.List;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import objects.EndPoint;
import objects.Requests;
import objects.Video;

public class MainAppReduced {
	
	public static final Integer numVideos=100;
	public static final Integer numEndPoints=5;
	public static final Integer numRequests=48;
	public static final Integer numCaches=10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Initialize
		Scanner scan = null;
		List<Video> llistaVideos = new ArrayList<Video>();
		List<EndPoint> llistaEndPoints = new ArrayList<EndPoint>();
		List<Video> originalLlistaVideos = null;
		List<Requests> llistaRequest = new ArrayList<Requests>();
		
		try {
			FileInputStream fileIn = new FileInputStream("resources/inReduced.txt");
			
			scan = new Scanner(fileIn);
			
			String line = scan.nextLine();
			
			line = scan.nextLine();
			
			String[] columns = line.split(" ");
			
			for(int i=0; i < numVideos; i++) {
				if(columns[i] != null) {
					llistaVideos.add(new Video(Integer.valueOf(columns[i])));
				}	
			}
			
			originalLlistaVideos = cloneLlista(llistaVideos);
			
			if(scan.hasNextLine()) {
				
				EndPoint endPoint;
				for(int i=0; i < numEndPoints; i++) {
					line = scan.nextLine();
					
					columns = line.split(" ");

					if(columns[1] != null) {
						endPoint = new EndPoint(Integer.valueOf(columns[0]),Integer.valueOf(columns[1]));
						llistaEndPoints.add(endPoint);
						for(int j = 0; j < Integer.valueOf(columns[1]); j++) {
							line = scan.nextLine();
							
							String[] info = line.split(" ");
							
							endPoint.getConnections().put(Integer.valueOf(info[0]), Integer.valueOf(info[1]));
						}
					}
				}
			}
			
			llistaRequest = new ArrayList<Requests>();
			for(int i=0; i < numRequests; i++) {
				line = scan.nextLine();
				
				columns = line.split(" ");
				
				llistaRequest.add(new Requests(originalLlistaVideos.get(Integer.valueOf(columns[0])),llistaEndPoints.get(Integer.valueOf(columns[1])), Integer.valueOf(columns[2])));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			scan.close();
		}
		
		
		System.out.println("LLista Original Videos =" + originalLlistaVideos.toString());
		System.out.println("LLista Original EndPoints =" + llistaEndPoints.toString());
		System.out.println("LLista Original Requests =" + llistaRequest.toString());
		
	}

	public static List<Video> cloneLlista(List<Video> llista){
		List<Video> newList = new ArrayList<Video>();
		
		for(Video v: llista) {
			newList.add(v);
		}
		
		return newList;
	}
	
}
