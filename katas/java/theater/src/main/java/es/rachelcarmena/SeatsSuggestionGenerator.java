package es.rachelcarmena;

import java.util.LinkedHashSet;
import java.util.Set;

import es.rachelcarmena.model.Theater;

public class SeatsSuggestionGenerator {

	private static final int MIN_GROUP_SIZE = 2;
	private Theater theater;
	private Set<String> suggestedSeats;
	private int ordered;

	public SeatsSuggestionGenerator(Theater theater) {
		this.theater = theater;
	}

	public String[] calculateSuggestion(int number) {
		this.ordered = number;
		this.suggestedSeats = new LinkedHashSet<>(ordered);

		if (findSeatsTogether(ordered))
			return getSuggestedSeats();
		if (findGroupsOfSeats())
			return getSuggestedSeats();
		findIndividualSeats();
		return getSuggestedSeats();
	}

	private String[] getSuggestedSeats() {
		return suggestedSeats.toArray(new String[0]);
	}

	private boolean isCompleted() {
		return suggestedSeats.size() == ordered;
	}

	private boolean findSeatsTogether(int seatsNumber) {
		for (int row = 0; row < theater.getRowsNumber(); row++) {
			if (findSeatsFromCenterToLeft(seatsNumber, row))
				return true;
			if (findSeatsFromCenterToRight(seatsNumber, row))
				return true;
		}
		return false;
	}

	private boolean findSeatsFromCenterToLeft(int seatsNumber, int rowNumber) {
		int startPoint = calculateStartPoint(seatsNumber, rowNumber);
		for (int left = startPoint; left >= 0; left--) {
			if (!theater.areSeatsAvailable(seatsNumber, rowNumber, left))
				continue;
			addSeatsNames(seatsNumber, rowNumber, left);
			return true;
		}
		return false;
	}

	private boolean findSeatsFromCenterToRight(int seatsNumber, int rowNumber) {
		int startPoint = calculateStartPoint(seatsNumber, rowNumber);
		int rowSeatsNumber = theater.getSeatsNumber(rowNumber);
		int maxStartPoint = rowSeatsNumber - seatsNumber;

		for (int right = startPoint + 1; right <= maxStartPoint; right++) {
			if (!theater.areSeatsAvailable(seatsNumber, rowNumber, right))
				continue;
			addSeatsNames(seatsNumber, rowNumber, right);
			return true;
		}
		return false;
	}

	private int calculateStartPoint(int seatsNumber, int rowNumber) {
		int rowSeatsNumber = theater.getSeatsNumber(rowNumber);
		int rowMiddle = rowSeatsNumber / 2;
		int backPositions = (seatsNumber + 1) / 2;
		return rowMiddle - backPositions;
	}

	private void addSeatsNames(int seatsNumber, int rowNumber, int fromSeatNumber) {
		for (int seat = 0; seat < seatsNumber; seat++) {
			int seatNumber = fromSeatNumber + seat;
			addSeatAndMarkAsSuggested(rowNumber, seatNumber);
		}
	}

	private void addSeatAndMarkAsSuggested(int rowNumber, int seatNumber) {
		String seatName = theater.getSeatName(rowNumber, seatNumber);
		suggestedSeats.add(seatName);
		theater.markSeatAsSuggested(rowNumber, seatNumber);
	}

	private boolean findGroupsOfSeats() {
		int groupSize = suggestGroupSize();
		while (groupSize > 0) {
			boolean groupsFound = findSeatsTogether(groupSize);
			groupsFound = findSeatsTogether(MIN_GROUP_SIZE) || groupsFound;
			if (!groupsFound)
				return false;
			if (isCompleted())
				return true;
			groupSize = suggestGroupSize();
		}
		return false;
	}

	private int suggestGroupSize() {
		return ordered - suggestedSeats.size() - MIN_GROUP_SIZE;
	}

	private void findIndividualSeats() {
		for (int row = 0; row < theater.getRowsNumber(); row++)
			for (int seat = 0; seat < theater.getSeatsNumber(row); seat++) {
				if (!theater.isAvailable(row, seat))
					continue;
				addSeatAndMarkAsSuggested(row, seat);
				if (isCompleted())
					return;
			}
	}
}
