package es.rachelcarmena;

import es.rachelcarmena.model.Arg;
import es.rachelcarmena.model.BooleanArg;
import es.rachelcarmena.model.IntegerArg;
import es.rachelcarmena.model.IntegerListArg;
import es.rachelcarmena.model.StringArg;
import es.rachelcarmena.model.StringListArg;

public class CommandLineParser {

	private Args argValues;

	public CommandLineParser(SchemaParser schemaParser) {
		argValues = new Args(schemaParser);
	}

	public void parse(String... args) {
		argValues.updateValues(args);
	}

	public boolean getBoolean(String name) {
		Arg arg = argValues.getArg(name);
		return BooleanArg.getValue(arg);
	}

	public int getInteger(String name) {
		Arg arg = argValues.getArg(name);
		return IntegerArg.getValue(arg);
	}

	public String getString(String name) {
		Arg arg = argValues.getArg(name);
		return StringArg.getValue(arg);
	}

	public String[] getStringList(String name) {
		Arg arg = argValues.getArg(name);
		return StringListArg.getValue(arg);
	}

	public int[] getIntegerList(String name) {
		Arg arg = argValues.getArg(name);
		return IntegerListArg.getValue(arg);
	}
}