package es.rachelcarmena.model;

public class ArgBuilder {

	public static Arg build(String argType) {
		switch (argType) {
		case "bool":
			return new BooleanArg();
		case "int":
			return new IntegerArg();
		case "str":
			return new StringArg();
		case "strList":
			return new StringListArg();
		case "intList":
			return new IntegerListArg();
		default:
			return null;
		}
	}
}