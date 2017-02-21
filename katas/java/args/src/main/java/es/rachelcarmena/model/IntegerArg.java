package es.rachelcarmena.model;

public class IntegerArg implements Arg {

	private int value;

	public IntegerArg() {
		this.value = 0;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = Integer.valueOf(value);
	}

	@Override
	public boolean hasValue() {
		return true;
	}
}
