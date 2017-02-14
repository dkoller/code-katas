package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class FizzBuzz {

	private Map<Integer, String> mapping;

	public FizzBuzz() {
		mapping = new TreeMap<>();
		mapping.put(3, "fizz");
		mapping.put(5, "buzz");
		mapping.put(7, "pop");
	}

	public String process(int number) {
		List<String> result = new ArrayList<>();
		for (Entry<Integer, String> entry : mapping.entrySet()) {
			if (number % entry.getKey() == 0)
				result.add(entry.getValue());
		}
		if (result.isEmpty())
			return String.valueOf(number);
		return String.join(" ", result);
	}

	public void addRule(int key, String value) {
		mapping.put(key, value);
	}
}
