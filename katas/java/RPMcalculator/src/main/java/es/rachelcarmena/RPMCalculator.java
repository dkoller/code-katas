package es.rachelcarmena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RPMCalculator {

	private boolean isOperator(String component) {
		return component.matches("[\\+\\-\\*\\/]");
	}

	private boolean areDigits(List<String> components) {
		return !components.stream().anyMatch(this::isOperator);
	}

	private List<String> getComponents(String expression) {
		return Arrays.asList(expression.split("\\s"));
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
		default:
			return 0;
		}
	}

	private int getIndexOfFirstOperator(List<String> components) {
		int index = 0;
		for (String component : components) {
			if (isOperator(component))
				break;
			index++;
		}
		return index;
	}

	private String operateToIndexOfOperator(List<String> components, int indexOfOperator) {
		char operator = components.get(indexOfOperator).charAt(0);
		int rightOperand = Integer.parseInt(components.get(indexOfOperator - 1));
		int leftOperand = Integer.parseInt(components.get(indexOfOperator - 2));

		return String.valueOf(operate(leftOperand, rightOperand, operator));
	}

	private List<String> reduceComponents(List<String> components, int indexOfOperator) {
		String partialResult = operateToIndexOfOperator(components, indexOfOperator);

		List<String> newListOfComponents = new ArrayList<>(components);
		newListOfComponents.subList(indexOfOperator - 2, indexOfOperator + 1).clear();
		newListOfComponents.add(indexOfOperator - 2, partialResult);
		return newListOfComponents;
	}

	private List<String> evaluateComponents(List<String> components) {
		if (areDigits(components))
			return components;

		int indexOfFirstOperator = getIndexOfFirstOperator(components);
		if (indexOfFirstOperator < 2)
			throw new IllegalArgumentException();

		List<String> newComponents = reduceComponents(components, indexOfFirstOperator);
		return evaluateComponents(newComponents);
	}

	public String evaluateExpression(String expression) {
		List<String> components = getComponents(expression);
		List<String> newComponents = evaluateComponents(components);
		return newComponents.stream().collect(Collectors.joining(" "));
	}
}
