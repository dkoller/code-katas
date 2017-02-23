package es.rachelcarmena.model;

import java.util.Arrays;

public class IntegerListArg implements Arg {

	int[] value;

	@Override
	public void setValue(String value) {
		String[] values = value.split(",");
		this.value = Arrays.asList(values).stream().mapToInt(Integer::valueOf).toArray();
	}

	@Override
	public boolean hasValue() {
		return true;
	}

	public static int[] getValue(Arg arg) {
		if (!(arg instanceof IntegerListArg))
			throw new IllegalArgumentException();
		return ((IntegerListArg) arg).value;
	}
}
