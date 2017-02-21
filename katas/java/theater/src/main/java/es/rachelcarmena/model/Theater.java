package es.rachelcarmena.model;

import java.io.IOException;

import es.rachelcarmena.TheaterInfoReader;

public class Theater {

	private Seat[][] structure;

	public Theater(String fileName) throws IOException {
		TheaterInfoReader infoReader = new TheaterInfoReader();
		structure = infoReader.createTheaterStructure(fileName);
	}

	public int getRowsNumber() {
		return structure.length;
	}

	public int getSeatsNumber(int rowNumber) {
		return structure[rowNumber].length;
	}

	public String getSeatName(int rowNumber, int seatNumber) {
		return structure[rowNumber][seatNumber].toString();
	}

	public void book(int rowNumber, int seatNumber) {
		structure[rowNumber][seatNumber].book();
	}

	public void markSeatAsSuggested(int rowNumber, int seatNumber) {
		structure[rowNumber][seatNumber].markAsSuggested();
	}

	public boolean areSeatsAvailable(int numberOfSeats, int rowNumber, int fromSeat) {
		for (int seat = 0; seat < numberOfSeats; seat++)
			if (!isAvailable(rowNumber, fromSeat + seat))
				return false;
		return true;
	}

	public boolean isAvailable(int rowNumber, int seatNumber) {
		return structure[rowNumber][seatNumber].isAvailable();
	}
}