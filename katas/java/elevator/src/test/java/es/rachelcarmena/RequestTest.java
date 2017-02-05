package es.rachelcarmena;

import org.junit.Test;
import static org.junit.Assert.*;

public class RequestTest {

	@Test
	public void isRequestedFloor() {
		Request requestedFloors = new Request();
		requestedFloors.addFloor(1);
		requestedFloors.addFloor(3);

		assertTrue(requestedFloors.isRequestedFloor(1));
		assertTrue(requestedFloors.isRequestedFloor(3));
	}

	@Test
	public void notIsRequestedFloor() {
		Request requestedFloors = new Request();
		requestedFloors.addFloor(1);
		requestedFloors.addFloor(3);

		assertFalse(requestedFloors.isRequestedFloor(0));
		assertFalse(requestedFloors.isRequestedFloor(2));
	}

	@Test
	public void moreUpFloors() {
		Request requestedFloors = new Request();
		requestedFloors.addFloor(5);
		requestedFloors.addFloor(6);

		assertTrue(requestedFloors.moreUpFloors(5));
		assertTrue(requestedFloors.moreUpFloors(4));
	}

	@Test
	public void notMoreUpFloors() {
		Request requestedFloors = new Request();
		requestedFloors.addFloor(5);
		requestedFloors.addFloor(6);

		assertFalse(requestedFloors.moreUpFloors(6));
		assertFalse(requestedFloors.moreUpFloors(7));
	}

	@Test
	public void moreDownFloors() {
		Request requestedFloors = new Request();
		requestedFloors.addFloor(2);
		requestedFloors.addFloor(3);

		assertTrue(requestedFloors.moreDownFloors(4));
		assertTrue(requestedFloors.moreDownFloors(3));
	}

	@Test
	public void notMoreDownFloors() {
		Request requestedFloors = new Request();
		requestedFloors.addFloor(2);
		requestedFloors.addFloor(3);

		assertFalse(requestedFloors.moreDownFloors(2));
		assertFalse(requestedFloors.moreDownFloors(1));
	}

}
