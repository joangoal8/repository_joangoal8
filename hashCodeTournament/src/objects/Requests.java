package objects;

public class Requests {

	public Video video;
	public EndPoint endpoint;
	public Integer numberOfRequest;
	
	public Requests(Video video, EndPoint endpoint, Integer numOfRequest) {
		this.video = video;
		this.endpoint = endpoint;
		this.numberOfRequest = numOfRequest;
	}

	public Video getVideo() {
		return video;
	}

	public EndPoint getEndpoint() {
		return endpoint;
	}

	public Integer getNumberOfRequest() {
		return numberOfRequest;
	}

	@Override
	public String toString() {
		return "Requests [video=" + video + ", endpoint=" + endpoint + ", numberOfRequest=" + numberOfRequest + "]";
	}
	
	
}
