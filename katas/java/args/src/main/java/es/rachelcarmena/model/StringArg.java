package es.rachelcarmena.model;

public class StringArg implements Arg {

	private String value;

	public StringArg() {
		this.value = "";
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean hasValue() {
		return true;
	}
}
