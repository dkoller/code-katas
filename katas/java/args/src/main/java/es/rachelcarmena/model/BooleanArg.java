package es.rachelcarmena.model;

public class BooleanArg implements Arg {

	private boolean value;

	public BooleanArg() {
		this.value = false;
	}

	@Override
	public void setValue(String value) {
		this.value = Boolean.valueOf(value);
	}

	@Override
	public boolean hasValue() {
		return false;
	}

	public static boolean getValue(Arg arg) {
		if (!(arg instanceof BooleanArg))
			throw new IllegalArgumentException("Type of flag doesn't match with query");
		return ((BooleanArg) arg).value;
	}
}
