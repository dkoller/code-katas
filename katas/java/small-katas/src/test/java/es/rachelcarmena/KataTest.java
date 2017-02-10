package es.rachelcarmena;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KataTest {

	@Test
	public void createPhoneNumber() {
		assertEquals("(123) 456-7890", Kata.createPhoneNumber(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }));
	}

	@Test
	public void getNumberFromWord() {
		assertEquals(-1, Kata.getNumberFromWord("abcd"));
		assertEquals(2, Kata.getNumberFromWord("is2"));
		assertEquals(1, Kata.getNumberFromWord("Thi1s"));
		assertEquals(3, Kata.getNumberFromWord("3a"));
	}

	@Test
	public void orderString() {
		assertEquals("Thi1s is2 3a T4est", Kata.order("is2 Thi1s T4est 3a"));
		assertEquals("Fo1r the2 g3ood 4of th5e pe6ople", Kata.order("4of Fo1r pe6ople g3ood th5e the2"));
	}

	@Test
	public void emptyInputShoudReturnEmptyString() {
		assertEquals("", Kata.order(""));
	}

	@Test
	public void findEvenIndex() {
		assertEquals(3, Kata.findEvenIndex(new int[] { 1, 2, 3, 4, 3, 2, 1 }));
		assertEquals(1, Kata.findEvenIndex(new int[] { 1, 100, 50, -51, 1, 1 }));
		assertEquals(-1, Kata.findEvenIndex(new int[] { 1, 2, 3, 4, 5, 6 }));
		assertEquals(3, Kata.findEvenIndex(new int[] { 20, 10, 30, 10, 10, 15, 35 }));
		assertEquals(-1, Kata.findEvenIndex(new int[] { -8505, -5130, 1926, -9026 }));
		assertEquals(1, Kata.findEvenIndex(new int[] { 2824, 1774, -1490, -9084, -9696, 23094 }));
		assertEquals(6, Kata.findEvenIndex(new int[] { 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4 }));
	}

	@Test
	public void seriesSum() {
		assertEquals(Kata.seriesSum(1), "1.00");
		assertEquals(Kata.seriesSum(2), "1.25");
		assertEquals(Kata.seriesSum(5), "1.57");
		assertEquals(Kata.seriesSum(15), "1.94");
	}

}
