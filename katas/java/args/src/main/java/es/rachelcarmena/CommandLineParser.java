package es.rachelcarmena;

import es.rachelcarmena.model.Args;

public class CommandLineParser {

	private Args args;

	public CommandLineParser(String schema) {
		args = new Args(schema);
	}

	public void parse(String commandLine) {
		if ("".equals(commandLine.trim()))
			return;

		args.updateValues(commandLine);
	}

	public Object getValue(String argName) {
		return args.getValue(argName);
	}
}