package es.rachelcarmena;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandLineParserTest {

	private static final String SCHEMA = "l bool:p int:d str:e strList:q intList";
	private final CommandLineParser parser = new CommandLineParser(SCHEMA);

	@Test
	public void no_args() {
		parser.parse("");

		assertEquals(false, parser.getBoolean("l"));
		assertEquals(0, parser.getInteger("p"));
		assertEquals("", parser.getString("d"));
	}

	@Test
	public void valid_boolean_flag() {
		parser.parse("-l");
		assertEquals(true, parser.getBoolean("l"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalid_boolean_flag() {
		parser.parse("-s");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalid_integer_flag() {
		parser.parse("-l 300");
	}

	@Test(expected = IllegalArgumentException.class)
	public void integer_flag_as_boolean_flag() {
		parser.parse("-p");
	}

	@Test(expected = IllegalArgumentException.class)
	public void boolean_flag_as_integer_flag() {
		parser.parse("-l 8080");
	}

	@Test
	public void positive_integer_argument() {
		parser.parse("-p 8080");
		assertEquals(8080, parser.getInteger("p"));
	}

	@Test
	public void negative_integer_argument() {
		parser.parse("-p -300");
		assertEquals(-300, parser.getInteger("p"));
	}

	@Test
	public void string_argument() {
		parser.parse("-d /usr/logs");
		assertEquals("/usr/logs", parser.getString("d"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalid_request_of_integer_argument() {
		parser.parse("-d /usr/logs");
		parser.getInteger("d");
	}

	@Test
	public void valid_full_command_line() {
		parser.parse("-l -p 8080 -d /usr/logs");
		assertEquals(true, parser.getBoolean("l"));
		assertEquals(8080, parser.getInteger("p"));
		assertEquals("/usr/logs", parser.getString("d"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalid_full_command_line() {
		parser.parse("-l -p 8080 -l /usr/logs");
	}

	@Test
	public void list_of_strings() {
		parser.parse("-e this,is,a,list");
		assertArrayEquals(new String[] { "this", "is", "a", "list" }, parser.getStringList("e"));
	}

	@Test
	public void list_of_integers() {
		parser.parse("-q 1,2,-3,5");
		assertArrayEquals(new int[] { 1, 2, -3, 5 }, parser.getIntegerList("q"));
	}
}
