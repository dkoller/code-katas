package es.rachelcarmena;

public class Seat {

	private String rowName;
	private String seatName;
	private boolean isBooked;
	private boolean isSuggested;

	public Seat(String rowName, String seatName) {
		this.rowName = rowName;
		this.seatName = seatName;
		this.isBooked = false;
		this.isSuggested = false;
	}

	public boolean isAvailable() {
		return !isBooked && !isSuggested;
	}

	public void book() {
		isBooked = true;
	}

	public void markAsSuggested() {
		isSuggested = true;
	}

	@Override
	public String toString() {
		return String.format("%s%s", rowName, seatName);
	}
}