package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FizzBuzz {

	private Map<Integer, String> mapping;

	public FizzBuzz() {
		mapping = new TreeMap<Integer, String>();
		mapping.put(3, "fizz");
		mapping.put(5, "buzz");
		mapping.put(7, "pop");
	}

	public String process(int number) {
		List<String> result = new ArrayList<String>();
		for (int multiple : mapping.keySet()) {
			if (number % multiple == 0)
				result.add(mapping.get(multiple));
		}
		if (result.isEmpty())
			return String.valueOf(number);
		return String.join(" ", result);
	}

	public void addRule(int key, String value) {
		mapping.put(key, value);
	}
}
