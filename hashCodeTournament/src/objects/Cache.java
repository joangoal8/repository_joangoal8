package objects;

import java.util.List;
import java.util.ArrayList;

public class Cache {

	public static Integer capacity = 100;
	public Integer pos;
	public List<Video> llistaVideos;
	
	public Cache(Integer pos) {
		this.pos = pos;
		llistaVideos = new ArrayList<Video>();
	}
	
	public Integer getPos() {
		return pos;
	}

	public List<Video> getLlistaVideos() {
		return llistaVideos;
	}

	public void addVideo(Video video){
		if(canAddVideo(video)){
			this.getLlistaVideos().add(video);
		}else{
			System.out.println("No es pot afegir");
		}
	}
	
	public boolean canAddVideo(Video video){
		Integer storage = 0;
		
		for(Video v : this.llistaVideos) {
			storage += v.getSizeVideo();
		}
		
		return (Cache.capacity > (storage + video.getSizeVideo()));
	}
}
