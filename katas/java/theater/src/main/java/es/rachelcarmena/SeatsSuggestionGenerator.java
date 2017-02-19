package es.rachelcarmena;

import java.util.LinkedHashSet;
import java.util.Set;

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

	private boolean findSeatsTogether(int number) {
		for (int row = 0; row < theater.getRowsNumber(); row++) {
			if (findSeatsFromCenterToLeft(number, row))
				return true;
			if (findSeatsFromCenterToRight(number, row))
				return true;
		}
		return false;
	}

	private boolean findSeatsFromCenterToLeft(int number, int row) {
		int startPoint = calculateStartPoint(row, number);
		for (int left = startPoint; left >= 0; left--) {
			if (!theater.areAvailable(number, row, left))
				continue;
			addSeatsNames(number, row, left);
			return true;
		}
		return false;
	}

	private boolean findSeatsFromCenterToRight(int number, int row) {
		int startPoint = calculateStartPoint(row, number);
		int rowSeatsNumber = theater.getSeatsNumber(row);
		int maxStartPoint = rowSeatsNumber - number;

		for (int right = startPoint + 1; right <= maxStartPoint; right++) {
			if (!theater.areAvailable(number, row, right))
				continue;
			addSeatsNames(number, row, right);
			return true;
		}
		return false;
	}

	private int calculateStartPoint(int row, int number) {
		int rowSeatsNumber = theater.getSeatsNumber(row);
		int rowMiddle = rowSeatsNumber / 2;
		int backPositions = (number + 1) / 2;
		return rowMiddle - backPositions;
	}

	private void addSeatsNames(int number, int rowNumber, int fromSeatNumber) {
		for (int seat = 0; seat < number; seat++) {
			int seatNumber = fromSeatNumber + seat;
			addSeatAndMarkAsSuggested(rowNumber, seatNumber);
		}
	}

	private void addSeatAndMarkAsSuggested(int rowNumber, int seatNumber) {
		String seatName = theater.getSeatName(rowNumber, seatNumber);
		suggestedSeats.add(seatName);
		theater.markAsSuggested(rowNumber, seatNumber);
	}

	private boolean findGroupsOfSeats() {
		int groupSize = suggestGroupSize();
		while (groupSize > 0) {
			boolean groupsFound = findSeatsTogether(groupSize);
			groupsFound = groupsFound || findSeatsTogether(MIN_GROUP_SIZE);
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
