package es.rachelcarmena;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Engine implements Runnable {

	private int floor;
	private State state;
	private Request requestedFloors;
	private Logger logger;

	public enum State {
		GOING_UP, GOING_DOWN, WAITING, REQUIRES_MAINTENANCE
	}

	public Engine(Request request) {
		floor = 0;
		state = State.WAITING;
		requestedFloors = request;
		logger = LogManager.getLogger(Engine.class);
	}

	public int getFloor() {
		return floor;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isActive() {
		return getState() != State.REQUIRES_MAINTENANCE;
	}

	public boolean inRequestedFloor() {
		return requestedFloors.isRequestedFloor(getFloor());
	}

	public void visitFloor() {
		logger.info("Stopping on floor: {}", getFloor());
		requestedFloors.removeFloor(getFloor());
	}

	public boolean moreUpFloors() {
		return requestedFloors.moreUpFloors(getFloor());
	}

	public boolean moreDownFloors() {
		return requestedFloors.moreDownFloors(getFloor());
	}

	public void goDownAndToTheNextFloor() {
		state = State.GOING_DOWN;
		floor--;
	}

	public void goUpAndToTheNextFloor() {
		state = State.GOING_UP;
		floor++;
	}

	public boolean isGoingUp() {
		return getState() == State.GOING_UP;
	}

	public boolean isGoingDown() {
		return getState() == State.GOING_DOWN;
	}

	public void goToTheNextFloor() {
		if (getState() == State.GOING_UP) {
			floor++;
			return;
		}
		floor--;
	}

	public void checkAndUpdateStateFromGoing() {
		boolean moreUpFloors = moreUpFloors();
		boolean moreDownFloors = moreDownFloors();

		if (!moreUpFloors && !moreDownFloors) {
			state = State.WAITING;
			return;
		}
		if (isGoingDown() && moreUpFloors && !moreDownFloors) {
			state = State.GOING_UP;
			return;
		}
		if (isGoingUp() && !moreUpFloors && moreDownFloors) {
			state = State.GOING_DOWN;
			return;
		}
	}

	public void provideServiceFromWaiting() {
		if (moreUpFloors()) { // preference: going up
			goUpAndToTheNextFloor();
			return;
		}
		if (moreDownFloors()) {
			goDownAndToTheNextFloor();
		}
	}

	public void provideServiceGoing() {
		if (!inRequestedFloor()) {
			goToTheNextFloor();
		} else {
			visitFloor();
			checkAndUpdateStateFromGoing();
		}
	}

	public void run() {
		try {
			while (isActive()) {
				logger.info("On floor: {}", getFloor());
				switch (getState()) {
				case WAITING:
					provideServiceFromWaiting();
					break;
				case GOING_UP:
				case GOING_DOWN:
					provideServiceGoing();
					break;
				case REQUIRES_MAINTENANCE:
					break;
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			return;
		}
	}

}
