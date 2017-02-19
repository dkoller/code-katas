package es.rachelcarmena;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SeatsSuggestionGeneratorTest {

	private Theater theater;

	@Before
	public void before() throws IOException {
		theater = new Theater("theater_seats.json");
	}

	@Test
	public void suggest_one_seat_with_empty_theater() throws IOException {
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A5" }, suggester.calculateSuggestion(1));
	}

	@Test
	public void suggest_two_seats_with_empty_theater() throws IOException {
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A5", "A6" }, suggester.calculateSuggestion(2));
	}

	@Test
	public void suggest_three_seats_with_empty_theater() throws IOException {
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A4", "A5", "A6" }, suggester.calculateSuggestion(3));
	}

	@Test
	public void suggest_four_seats_with_empty_theater() throws IOException {
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A4", "A5", "A6", "A7" }, suggester.calculateSuggestion(4));
	}

	@Test
	public void suggest_four_seats_with_not_empty_center_in_first_row() throws IOException {
		book(0, 3, 6);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "B4", "B5", "B6", "B7" }, suggester.calculateSuggestion(4));
	}

	@Test
	public void suggest_four_seats_with_first_four_empty_seats_beginning_second_row() throws IOException {
		book(0, 0, 9);
		book(1, 4, 9);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "B1", "B2", "B3", "B4" }, suggester.calculateSuggestion(4));
	}

	@Test
	public void suggest_four_seats_with_first_four_empty_seats_ending_third_row() throws IOException {
		book(0, 0, 9);
		book(1, 0, 9);
		book(2, 0, 5);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "C7", "C8", "C9", "C10" }, suggester.calculateSuggestion(4));
	}

	@Test
	public void suggest_five_seats_from_two_rows() throws IOException {
		book(0, 3, 9);
		book(1, 2, 9);
		for (int row = 2; row < 7; row++)
			book(row, 0, 9);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A1", "A2", "A3", "B1", "B2" }, suggester.calculateSuggestion(5));
	}

	@Test
	public void suggest_six_seats_from_three_rows() throws IOException {
		book(0, 2, 9);
		book(1, 2, 9);
		book(2, 2, 9);
		for (int row = 3; row < 7; row++)
			book(row, 0, 9);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A1", "A2", "B1", "B2", "C1", "C2" }, suggester.calculateSuggestion(6));
	}

	@Test
	public void suggest_seven_seats_from_three_rows() throws IOException {
		book(0, 2, 9);
		book(1, 3, 9);
		book(2, 2, 9);
		for (int row = 3; row < 7; row++)
			book(row, 0, 9);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A1", "A2", "B1", "B2", "B3", "C1", "C2" }, suggester.calculateSuggestion(7));
	}

	@Test
	public void suggest_seven_seats_with_individual_seats() throws IOException {
		for (int row = 0; row < 7; row++)
			book(row, 1, 9);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A1", "B1", "C1", "D1", "E1", "F1", "G1" }, suggester.calculateSuggestion(7));
	}

	@Test
	public void suggest_eight_seats_with_individual_seats() throws IOException {
		book(0, 2, 9);
		book(1, 3, 9);
		book(2, 2, 8);
		for (int row = 3; row < 7; row++)
			book(row, 0, 9);
		SeatsSuggestionGenerator suggester = new SeatsSuggestionGenerator(theater);
		assertArrayEquals(new String[] { "A1", "A2", "B2", "B3", "C1", "C2", "B1", "C10" }, suggester.calculateSuggestion(8));
	}

	private void book(int row, int from, int to) {
		for (int seat = from; seat <= to; seat++) {
			theater.book(row, seat);
		}
	}
}