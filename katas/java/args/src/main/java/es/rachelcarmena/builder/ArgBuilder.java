package es.rachelcarmena.builder;

import java.util.Arrays;

import es.rachelcarmena.model.Arg;
import es.rachelcarmena.model.BooleanArg;
import es.rachelcarmena.model.IntegerArg;
import es.rachelcarmena.model.IntegerListArg;
import es.rachelcarmena.model.StringArg;
import es.rachelcarmena.model.StringListArg;

public class ArgBuilder {

	private static final String BOOLEAN_TYPE = "bool";
	private static final String INTEGER_TYPE = "int";
	private static final String STRING_TYPE = "str";
	private static final String INTEGER_LIST_TYPE = "intList";
	private static final String STRING_LIST_TYPE = "strList";

	private static final String[] types = { BOOLEAN_TYPE, INTEGER_TYPE, STRING_TYPE, INTEGER_LIST_TYPE,
			STRING_LIST_TYPE };

	public static Arg build(String typeName) {
		switch (typeName) {
		case BOOLEAN_TYPE:
			return new BooleanArg();
		case INTEGER_TYPE:
			return new IntegerArg();
		case STRING_TYPE:
			return new StringArg();
		case STRING_LIST_TYPE:
			return new StringListArg();
		case INTEGER_LIST_TYPE:
			return new IntegerListArg();
		default:
			throw new IllegalArgumentException("Type not valid: argType");
		}
	}

	public static boolean typeExists(String typeName) {
		return Arrays.asList(ArgBuilder.types).contains(typeName);
	}
}