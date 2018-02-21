package objects;

public class Video {
	
	public Integer sizeVideo;
	
	public Video(Integer size){
		this.sizeVideo = size;
	}

	public Integer getSizeVideo() {
		return sizeVideo;
	}

	@Override
	public String toString() {
		return "Video [sizeVideo=" + sizeVideo + "]";
	}
	
	
}
