package es.rachelcarmena;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class FizzBuzzTest {

	private static FizzBuzz fizzBuzz;

	@BeforeClass
	public static void beforeClass() {
		fizzBuzz = new FizzBuzz();
	}

	@Test
	@Parameters({ "31, 31", "47, 47" })
	public void getNumberFromNumber(int number, String result) {
		assertEquals(result, fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "3", "9", "123" })
	public void getFizzFromMultipleOfThree(int number) {
		assertEquals("fizz", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "5", "20", "200" })
	public void getBuzzFromMultipleOfFive(int number) {
		assertEquals("buzz", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "15", "45" })
	public void getFizzBuzzFromMultipleOfThreeAndFive(int number) {
		assertEquals("fizz buzz", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "7", "28", "77" })
	public void getPopFromMultipleOfSeven(int number) {
		assertEquals("pop", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "21", "63", "126" })
	public void getFizzPopFromMultipleOfThreeAndSeven(int number) {
		assertEquals("fizz pop", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "35", "70", "140" })
	public void getFizzPopFromMultipleOfFiveAndSeven(int number) {
		assertEquals("buzz pop", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "105", "315" })
	public void getFizzPopFromMultipleOfThreeAndFiveAndSeven(int number) {
		assertEquals("fizz buzz pop", fizzBuzz.process(number));
	}

	@Test
	@Parameters({ "210", "630" })
	public void addNewRule(int number) {
		fizzBuzz.addRule(2, "fuzz");
		assertEquals("fuzz fizz buzz pop", fizzBuzz.process(number));
	}

}
