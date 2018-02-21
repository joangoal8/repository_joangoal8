package objects;

import java.util.HashMap;
import java.util.Map;

public class EndPoint {

	public Integer costDataCenter;
	public Integer numConnec;
	public Map<Integer, Integer> connections;
	
	public EndPoint(Integer costDataCenter, Integer numConnec) {
		this.costDataCenter = costDataCenter;
		this.numConnec = numConnec;	
		
		connections = new HashMap<Integer, Integer>();
	}

	public Integer getCostDataCenter() {
		return costDataCenter;
	}

	public Integer getNumConnec() {
		return numConnec;
	}

	public Map<Integer, Integer> getConnections() {
		return connections;
	}

	@Override
	public String toString() {
		return "EndPoint [costDataCenter=" + costDataCenter + ", numConnec=" + numConnec + ", connections="
				+ connections + "]";
	}
	
	
}
