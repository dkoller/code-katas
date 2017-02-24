package es.rachelcarmena.model;

public class StringListArg implements Arg {

	String[] value;

	@Override
	public void setValue(String value) {
		this.value = value.split(",");
	}

	@Override
	public boolean hasValue() {
		return true;
	}

	public static String[] getValue(Arg arg) {
		if (!(arg instanceof StringListArg))
			throw new IllegalArgumentException("Type of flag doesn't match with query");
		return ((StringListArg) arg).value;
	}

}
