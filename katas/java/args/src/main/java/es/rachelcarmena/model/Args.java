package es.rachelcarmena.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Args {

	private static final String ARGS_SEPARATOR = "\\s+";
	private Map<String, Arg> argValues = new HashMap<>();

	public Args(String schema) {
		loadDefaultValues(schema);
	}

	public void updateValues(String commandLine) {
		Iterator<String> iterator = createIterator(commandLine);
		while (iterator.hasNext()) {
			String section = iterator.next();
			String flagName = getFlagName(section);

			Arg arg = argValues.get(flagName);
			String value = null;
			if (arg.hasValue()) {
				if (!iterator.hasNext())
					throw new IllegalArgumentException();
				value = iterator.next();
			}
			arg.setValue(value);
		}
	}

	public Arg getArg(String name) {
		if (!argValues.containsKey(name))
			throw new IllegalArgumentException();
		return argValues.get(name);
	}

	private Iterator<String> createIterator(String commandLine) {
		String[] parts = commandLine.split(ARGS_SEPARATOR);
		List<String> partsAsList = Arrays.asList(parts);
		return partsAsList.iterator();
	}

	private void loadDefaultValues(String schema) {
		final String SCHEMA_SEPARATOR = ":";
		String[] schemaParts = schema.split(SCHEMA_SEPARATOR);

		for (String schemaPart : schemaParts) {
			String[] schemaArg = schemaPart.split(ARGS_SEPARATOR);
			String argName = schemaArg[0];
			String argType = schemaArg[1];

			Arg arg = ArgBuilder.build(argType);
			argValues.put(argName, arg);
		}
	}

	private String getFlagName(String section) {
		final String FLAG_NAME_FORMAT = "-[a-z]";
		if (!Pattern.matches(FLAG_NAME_FORMAT, section))
			throw new IllegalArgumentException();

		String name = section.substring(1);
		if (!argValues.containsKey(name))
			throw new IllegalArgumentException();
		return name;
	}
}
