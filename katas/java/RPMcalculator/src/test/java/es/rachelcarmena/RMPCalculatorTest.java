package es.rachelcarmena;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RMPCalculatorTest {

	@Test
	@Parameters({ "2, 2, +, 4", "5, 3, -, 2", "2, 4, *, 8", "6, 2, /, 3" })
	public void operate(int leftOperand, int rightOperand, char operator, String result) {
		assertEquals(result, RPMCalculator.operate(leftOperand, rightOperand, operator));
	}

	@Test
	@Parameters({ "4", "1 4", "2 1 4" })
	public void evaluateDigits(String expression) {
		assertEquals(expression, RPMCalculator.evaluate(expression));
	}

	@Test
	@Parameters({ "20 5 /, 4", "4 2 +, 6" })
	public void evaluateSimpleExpression(String expression, String result) {
		assertEquals(result, RPMCalculator.evaluate(expression));
	}

	@Test
	@Parameters({ "4 2 + 3 -, 3", "3 5 8 * 7 + *, 141", "7 2 - 3 4, 5 4 3" })
	public void evaluateComplexExpression(String expression, String result) {
		assertEquals(result, RPMCalculator.evaluate(expression));

	}

	@Test(expected = IllegalArgumentException.class)
	public void evaluateWrongComplexExpression() {
		RPMCalculator.evaluate("4 + 3 -");
	}
}
