package es.rachelcarmena;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandLineParserTest {

	private static final String SCHEMA = "l bool:p int:d str";
	private final CommandLineParser parser = new CommandLineParser(SCHEMA);

	@Test
	public void no_args() {
		parser.parse("");

		assertEquals(false, parser.getValue("l"));
		assertEquals(0, parser.getValue("p"));
		assertEquals("", parser.getValue("d"));
	}

	@Test
	public void valid_boolean_flag() {
		parser.parse("-l");
		assertEquals(true, parser.getValue("l"));
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
		assertEquals(8080, parser.getValue("p"));
	}

	@Test
	public void negative_integer_argument() {
		parser.parse("-p -300");
		assertEquals(-300, parser.getValue("p"));
	}

	@Test
	public void string_argument() {
		parser.parse("-d /usr/logs");
		assertEquals("/usr/logs", parser.getValue("d"));
	}

	@Test
	public void valid_full_command_line() {
		parser.parse("-l -p 8080 -d /usr/logs");
		assertEquals(true, parser.getValue("l"));
		assertEquals(8080, parser.getValue("p"));
		assertEquals("/usr/logs", parser.getValue("d"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalid_full_command_line() {
		parser.parse("-l -p 8080 -l /usr/logs");
	}
}
