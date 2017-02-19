package es.rachelcarmena;

import java.io.IOException;

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

	public void book(int rowCoordinate, int seatCoordinate) {
		structure[rowCoordinate][seatCoordinate].book();
	}

	public void markAsSuggested(int rowCoordinate, int seatCoordinate) {
		structure[rowCoordinate][seatCoordinate].markAsSuggested();
	}

	public boolean areAvailable(int ordered, int row, int fromSeat) {
		for (int i = 0; i < ordered; i++)
			if (!isAvailable(row, fromSeat + i))
				return false;
		return true;
	}

	public boolean isAvailable(int rowCoordinate, int seatCoordinate) {
		return structure[rowCoordinate][seatCoordinate].isAvailable();
	}
}