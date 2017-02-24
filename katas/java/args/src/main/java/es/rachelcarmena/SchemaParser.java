package es.rachelcarmena;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import es.rachelcarmena.builder.ArgBuilder;
import es.rachelcarmena.model.Arg;

public class SchemaParser {

	public static Map<String, Arg> calculateDefaultValues(String schema) {
		final String SCHEMA_SEPARATOR = ":";
		final String ARGS_SEPARATOR = "\\s+";
		final String SCHEMA_FORMAT = "[a-z]\\s\\w+(:[a-z]\\s\\w+)*";

		Map<String, Arg> defaultArgs = new HashMap<>();
		if (!Pattern.matches(SCHEMA_FORMAT, schema))
			throw new IllegalArgumentException("Schema format not valid");

		String[] schemaParts = schema.split(SCHEMA_SEPARATOR);
		for (String schemaPart : schemaParts) {
			String[] schemaArg = schemaPart.split(ARGS_SEPARATOR);
			String argName = schemaArg[0];
			String argType = schemaArg[1];

			Arg arg = ArgBuilder.build(argType);
			defaultArgs.put(argName, arg);
		}
		return defaultArgs;
	}

}
