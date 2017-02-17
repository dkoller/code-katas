package es.rachelcarmena;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BerlinClockTest {

	@Test
	public void create_representation_for_00_00() {
		BerlinClock clock = new BerlinClock("00:00");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[O][O][O][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_01() {
		BerlinClock clock = new BerlinClock("00:01");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[O][O][O][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[Y]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_04() {
		BerlinClock clock = new BerlinClock("00:04");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[O][O][O][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[Y]      [Y]      [Y]      [Y]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_15() {
		BerlinClock clock = new BerlinClock("00:15");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_19() {
		BerlinClock clock = new BerlinClock("00:19");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[Y]      [Y]      [Y]      [Y]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_30() {
		BerlinClock clock = new BerlinClock("00:30");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][R][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_32() {
		BerlinClock clock = new BerlinClock("00:32");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][R][O][O][O][O][O]";
		representationElements[3] = "[Y]      [Y]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_45() {
		BerlinClock clock = new BerlinClock("00:45");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][R][Y][Y][R][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_00_59() {
		BerlinClock clock = new BerlinClock("00:59");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][R][Y][Y][R][Y][Y]";
		representationElements[3] = "[Y]      [Y]      [Y]      [Y]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_01_10() {
		BerlinClock clock = new BerlinClock("01:10");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][O][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_02_30() {
		BerlinClock clock = new BerlinClock("02:30");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [R]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][R][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_03_00() {
		BerlinClock clock = new BerlinClock("03:00");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [R]      [R]      [O]";
		representationElements[2] = "[O][O][O][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_03_15() {
		BerlinClock clock = new BerlinClock("03:15");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [R]      [R]      [O]";
		representationElements[2] = "[Y][Y][R][O][O][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_04_25() {
		BerlinClock clock = new BerlinClock("04:25");
		String[] representationElements = new String[4];
		representationElements[0] = "[O]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [R]      [R]      [R]";
		representationElements[2] = "[Y][Y][R][Y][Y][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_05_25() {
		BerlinClock clock = new BerlinClock("05:25");
		String[] representationElements = new String[4];
		representationElements[0] = "[R]      [O]      [O]      [O]";
		representationElements[1] = "[O]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_06_25() {
		BerlinClock clock = new BerlinClock("06:25");
		String[] representationElements = new String[4];
		representationElements[0] = "[R]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [O]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_07_25() {
		BerlinClock clock = new BerlinClock("07:25");
		String[] representationElements = new String[4];
		representationElements[0] = "[R]      [O]      [O]      [O]";
		representationElements[1] = "[R]      [R]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_12_25() {
		BerlinClock clock = new BerlinClock("12:25");
		String[] representationElements = new String[4];
		representationElements[0] = "[R]      [R]      [O]      [O]";
		representationElements[1] = "[R]      [R]      [O]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}

	@Test
	public void create_representation_for_23_25() {
		BerlinClock clock = new BerlinClock("23:25");
		String[] representationElements = new String[4];
		representationElements[0] = "[R]      [R]      [R]      [R]";
		representationElements[1] = "[R]      [R]      [R]      [O]";
		representationElements[2] = "[Y][Y][R][Y][Y][O][O][O][O][O][O]";
		representationElements[3] = "[O]      [O]      [O]      [O]";
		assertEquals(String.join("%n", representationElements), clock.toString());
	}
}
