package es.rachelcarmena;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RPMCalculator {

	public static String operate(int left, int right, char operator) {
		switch (operator) {
		case '+':
			return String.valueOf(left + right);
		case '-':
			return String.valueOf(left - right);
		case '*':
			return String.valueOf(left * right);
		case '/':
			return String.valueOf(left / right);
		}
		return "";
	}

	public static String formatStringFromIndex(String[] parts, int index) {
		return String.join(" ", Arrays.copyOfRange(parts, index, parts.length));
	}

	public static String formatStringToIndex(String[] parts, int index) {
		if (index == 0)
			return parts[0];
		return String.join(" ", Arrays.copyOfRange(parts, 0, index));
	}

	public static boolean areDigits(String expression) {
		return Pattern.matches("\\d+(\\s\\d+)*", expression);
	}

	public static boolean isOperator(String part) {
		return Pattern.matches("[\\+\\-\\*\\/]", part);
	}

	public static String[] getParts(String expression) {
		return expression.split("\\s");
	}

	public static String evaluate(String expression) throws IllegalArgumentException {
		if (areDigits(expression))
			return expression;

		int index = 0;
		char operator = ' ';
		String[] parts = getParts(expression);
		for (String part : parts) {
			if (isOperator(part)) {
				operator = part.charAt(0);
				break;
			}
			index++;
		}
		if (index < 2)
			throw new IllegalArgumentException();
		int leftOperand = Integer.valueOf(parts[index - 2]);
		int rightOperand = Integer.valueOf(parts[index - 1]);
		String result = operate(leftOperand, rightOperand, operator);

		if (parts.length == 3)
			return result;
		if (index == 2)
			return evaluate(String.format("%s %s", result, formatStringFromIndex(parts, index + 1)));
		return evaluate(String.format("%s %s %s", formatStringToIndex(parts, index - 3), result,
				formatStringFromIndex(parts, index + 1)));

	}
}