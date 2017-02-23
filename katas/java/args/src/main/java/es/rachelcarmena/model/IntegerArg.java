package es.rachelcarmena.model;

public class IntegerArg implements Arg {

	private int value;

	public IntegerArg() {
		this.value = 0;
	}

	@Override
	public void setValue(String value) {
		this.value = Integer.valueOf(value);
	}

	@Override
	public boolean hasValue() {
		return true;
	}

	public static int getValue(Arg arg) {
		if (!(arg instanceof IntegerArg))
			throw new IllegalArgumentException();
		return ((IntegerArg) arg).value;
	}
}
