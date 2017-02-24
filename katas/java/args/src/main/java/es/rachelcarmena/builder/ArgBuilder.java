package es.rachelcarmena.builder;

import es.rachelcarmena.model.Arg;
import es.rachelcarmena.model.BooleanArg;
import es.rachelcarmena.model.IntegerArg;
import es.rachelcarmena.model.IntegerListArg;
import es.rachelcarmena.model.StringArg;
import es.rachelcarmena.model.StringListArg;

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
			throw new IllegalArgumentException("Type not valid: argType");
		}
	}
}