package es.rachelcarmena;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RMPCalculatorTest {

	private static RPMCalculator calculator;
	
	@BeforeClass
	public static void beforeClass() {
		calculator = new RPMCalculator();
	}

	@Test
	@Parameters({ "4", "1 4", "2 1 4" })
	public void evaluateDigits(String expression) {
		assertEquals(expression, calculator.evaluate(expression));
	}

	@Test
	@Parameters({ "20 5 /, 4", "4 2 +, 6", "2 2 +, 4", "5 3 -, 2", "2 4 *, 8", "6 2 /, 3" })
	public void evaluateSimpleExpression(String expression, String result) {
		assertEquals(result, calculator.evaluate(expression));
	}

	@Test
	@Parameters({ "4 2 + 3 -, 3", "3 5 8 * 7 + *, 141", "7 2 - 3 4, 5 3 4" })
	public void evaluateComplexExpression(String expression, String result) {
		assertEquals(result, calculator.evaluate(expression));

	}

	@Test(expected = IllegalArgumentException.class)
	public void evaluateWrongComplexExpression() {
		calculator.evaluate("4 + 3 -");
	}
}
