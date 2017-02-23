package es.rachelcarmena.model;

public class BooleanArg implements Arg {

	private boolean value;

	public BooleanArg() {
		this.value = false;
	}

	@Override
	public void setValue(String value) {
		this.value = true;
	}

	@Override
	public boolean hasValue() {
		return false;
	}

	public static boolean getValue(Arg arg) {
		if (!(arg instanceof BooleanArg))
			throw new IllegalArgumentException();
		return ((BooleanArg) arg).value;
	}
}
