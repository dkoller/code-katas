package es.rachelcarmena.model;

public class BooleanArg implements Arg {

	private boolean value;

	public BooleanArg() {
		this.value = false;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = true;
	}

	@Override
	public boolean hasValue() {
		return false;
	}
}
