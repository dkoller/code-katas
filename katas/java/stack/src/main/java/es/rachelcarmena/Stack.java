package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;

public class Stack {

	private List<Object> stack = new ArrayList<>();

	public Object pop() {
		if (stack.isEmpty())
			throw new IllegalArgumentException();
		return stack.remove(stack.size() - 1);
	}

	public void push(Object object) {
		stack.add(object);
	}
}
