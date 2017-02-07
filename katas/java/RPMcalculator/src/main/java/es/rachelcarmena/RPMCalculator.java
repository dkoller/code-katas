package es.rachelcarmena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RPMCalculator {

	private boolean areDigits(String expression) {
		return Pattern.matches("\\d+(\\s\\d+)*", expression);
	}

	private String[] getComponents(String expression) {
		return expression.split("\\s");
	}

	private boolean isOperator(String component) {
		return Pattern.matches("[\\+\\-\\*\\/]", component);
	}

	private int operate(int left, int right, char operator) {
		switch (operator) {
		case '+':
			return left + right;
		case '-':
			return left - right;
		case '*':
			return left * right;
		case '/':
			return left / right;
		}
		return 0;
	}

	private int getIndexOfFirstOperator(String[] components) {
		int index = 0;
		for (String component : components) {
			if (isOperator(component))
				break;
			index++;
		}
		return index;
	}

	private String operateToIndexOfOperator(String[] components, int indexOfOperator) {
		char operator = components[indexOfOperator].charAt(0);
		int rightOperand = Integer.valueOf(components[indexOfOperator - 1]);
		int leftOperand = Integer.valueOf(components[indexOfOperator - 2]);

		return String.valueOf(operate(leftOperand, rightOperand, operator));
	}

	private String getNewExpression(String[] components, int indexOfOperator, String partialResult) {
		List<String> newListOfComponents = new ArrayList<String>(Arrays.asList(components));
		newListOfComponents.subList(indexOfOperator - 2, indexOfOperator + 1).clear();
		newListOfComponents.add(indexOfOperator - 2, partialResult);
		return newListOfComponents.stream().collect(Collectors.joining(" "));
	}

	public String evaluate(String expression) throws IllegalArgumentException {
		if (areDigits(expression))
			return expression;

		String[] components = getComponents(expression);
		int indexOfFirstOperator = getIndexOfFirstOperator(components);
		if (indexOfFirstOperator < 2)
			throw new IllegalArgumentException();

		String partialResult = operateToIndexOfOperator(components, indexOfFirstOperator);

		if (components.length == 3)
			return partialResult;
		return evaluate(getNewExpression(components, indexOfFirstOperator, partialResult));
	}
}
