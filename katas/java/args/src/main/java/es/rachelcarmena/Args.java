package es.rachelcarmena;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import es.rachelcarmena.model.Arg;

public class Args {

	private Map<String, Arg> argValues;

	public Args(String schema) {
		argValues = SchemaParser.calculateDefaultValues(schema);
	}

	public void updateValues(String... args) {
		List<String> partsAsList = Arrays.asList(args);
		Iterator<String> iterator = partsAsList.iterator();
		while (iterator.hasNext()) {
			String section = iterator.next();
			String flagName = getFlagName(section);

			Arg arg = argValues.get(flagName);
			if (!arg.hasValue()) {
				arg.setValue("true");
				continue;
			}
			if (!iterator.hasNext())
				throw new IllegalArgumentException("This flag needs a value:" + flagName);
			String value = iterator.next();
			arg.setValue(value);
		}
	}

	public Arg getArg(String name) {
		if (!argValues.containsKey(name))
			throw new IllegalArgumentException();
		return argValues.get(name);
	}

	private String getFlagName(String section) {
		final String FLAG_NAME_FORMAT = "-[a-z]";

		if (!Pattern.matches(FLAG_NAME_FORMAT, section))
			throw new IllegalArgumentException("Format of this flag is not valid: " + section);

		String name = section.substring(1);
		if (!argValues.containsKey(name))
			throw new IllegalArgumentException("This flag doesn't exists: " + name);
		return name;
	}
}
