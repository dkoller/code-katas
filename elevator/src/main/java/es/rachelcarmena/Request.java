package es.rachelcarmena;

import java.util.HashSet;
import java.util.Set;

public class Request {

	// Another option: 
	//   Set s = Collections.synchronizedSet(new HashSet(...));
	private Set<Integer> requestedFloors;

	public Request() {
		requestedFloors = new HashSet<Integer>();
	}

	public synchronized boolean isRequestedFloor(int floor) {
		return requestedFloors.contains(floor);
	}

	public synchronized void removeFloor(int floor) {
		requestedFloors.remove(floor);
	}

	public synchronized void addFloor(int floor) {
		requestedFloors.add(floor);
	}

	public synchronized boolean moreUpFloors(int floor) {
		return requestedFloors.stream().anyMatch(f -> f > floor);
	}

	public synchronized boolean moreDownFloors(int floor) {
		return requestedFloors.stream().anyMatch(f -> f < floor);
	}
}
