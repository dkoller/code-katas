package es.rachelcarmena.model;

public class StringArg implements Arg {

	private String value;

	public StringArg() {
		this.value = "";
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean hasValue() {
		return true;
	}

	public static String getValue(Arg arg) {
		if (!(arg instanceof StringArg))
			throw new IllegalArgumentException();
		return ((StringArg) arg).value;
	}
}
