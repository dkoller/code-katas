package es.rachelcarmena;

import org.junit.Before;
import org.junit.Test;
import es.rachelcarmena.Engine.State;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EngineTest {

	public Request requestedFloors;
	public Engine engine;
	public int currentFloor;

	@Before
	public void before() {
		requestedFloors = mock(Request.class);
		engine = new Engine(requestedFloors);
		currentFloor = engine.getFloor();
	}

	@Test
	public void createEngine() {
		assertEquals(0, currentFloor);
		assertEquals(State.WAITING, engine.getState());
	}

	@Test
	public void provideServiceFromWaitingWhenNoMoreFloors() {
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(false);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(false);

		engine.provideServiceFromWaiting();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.WAITING, engine.getState());
	}

	@Test
	public void provideServiceFromWaitingWhenMoreUpAndDownFloors() {
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(true);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(true);

		engine.provideServiceFromWaiting();

		assertEquals(currentFloor + 1, engine.getFloor());
		assertEquals(State.GOING_UP, engine.getState());
	}

	@Test
	public void provideServiceFromWaitingWhenMoreUpFloors() {
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(true);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(false);

		engine.provideServiceFromWaiting();

		assertEquals(currentFloor + 1, engine.getFloor());
		assertEquals(State.GOING_UP, engine.getState());
	}

	@Test
	public void provideServiceFromWaitingWhenMoreDownFloors() {
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(false);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(true);

		engine.provideServiceFromWaiting();

		assertEquals(currentFloor - 1, engine.getFloor());
		assertEquals(State.GOING_DOWN, engine.getState());
	}

	@Test
	public void provideServiceFromGoingUpWhenNotInRequestedFloor() {
		engine.setState(State.GOING_UP);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(false);

		engine.provideServiceGoing();

		assertEquals(currentFloor + 1, engine.getFloor());
		assertEquals(State.GOING_UP, engine.getState());
	}

	@Test
	public void provideServiceFromGoingDownWhenNotInRequestedFloor() {
		engine.setState(State.GOING_DOWN);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(false);

		engine.provideServiceGoing();

		assertEquals(currentFloor - 1, engine.getFloor());
		assertEquals(State.GOING_DOWN, engine.getState());
	}

	@Test
	public void provideServiceFromGoingUpWhenInRequestedFloorAndNoMoreRequestedFloors() {
		engine.setState(State.GOING_UP);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(false);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(false);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.WAITING, engine.getState());
	}

	@Test
	public void provideServiceFromGoingDownWhenInRequestedFloorAndNoMoreRequestedFloors() {
		engine.setState(State.GOING_DOWN);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(false);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(false);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.WAITING, engine.getState());
	}

	@Test
	public void provideServiceFromGoingUpWhenInRequestedFloorAndMoreUpAndDownFloors() {
		engine.setState(State.GOING_UP);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(true);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(true);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.GOING_UP, engine.getState());
	}

	@Test
	public void provideServiceFromGoingUpWhenInRequestedFloorAndMoreUpFloors() {
		engine.setState(State.GOING_UP);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(true);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(false);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.GOING_UP, engine.getState());
	}

	@Test
	public void provideServiceFromGoingUpWhenInRequestedFloorAndMoreDownFloors() {
		engine.setState(State.GOING_UP);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(false);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(true);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.GOING_DOWN, engine.getState());
	}

	@Test
	public void provideServiceFromGoingDownWhenInRequestedFloorAndMoreUpAndDownFloors() {
		engine.setState(State.GOING_DOWN);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(true);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(true);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.GOING_DOWN, engine.getState());
	}

	@Test
	public void provideServiceFromGoingDownWhenInRequestedFloorAndMoreUpFloors() {
		engine.setState(State.GOING_DOWN);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(true);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(false);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.GOING_UP, engine.getState());
	}

	@Test
	public void provideServiceFromGoingDownWhenInRequestedFloorAndMoreDownFloors() {
		engine.setState(State.GOING_DOWN);
		when(requestedFloors.isRequestedFloor(currentFloor)).thenReturn(true);
		when(requestedFloors.moreUpFloors(currentFloor)).thenReturn(false);
		when(requestedFloors.moreDownFloors(currentFloor)).thenReturn(true);

		engine.provideServiceGoing();

		assertEquals(currentFloor, engine.getFloor());
		assertEquals(State.GOING_DOWN, engine.getState());
	}
}
