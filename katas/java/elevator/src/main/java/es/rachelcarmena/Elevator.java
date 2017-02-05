package es.rachelcarmena;

import java.util.Scanner;

public class Elevator {

	private Request requestedFloors;

	public Elevator() {
		this.requestedFloors = new Request();
	}

	public void start() {
		new Thread(new Engine(this.requestedFloors)).start();
	}

	public void pressButton(int floor) {
		this.requestedFloors.addFloor(floor);
	}

	public static void main(String[] args) {
		Elevator elevator = new Elevator();
		elevator.start();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (scanner.hasNextInt()) {
				elevator.pressButton(scanner.nextInt());
			}
		}
	}
}
