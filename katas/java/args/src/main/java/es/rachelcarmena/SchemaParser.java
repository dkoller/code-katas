package es.rachelcarmena;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import es.rachelcarmena.builder.ArgBuilder;

public class SchemaParser {

	private Map<String, String> argsInfo;

	public SchemaParser(String schema) {
		final String SCHEMA_SEPARATOR = ":";
		final String ARGS_SEPARATOR = "\\s+";
		final String SCHEMA_FORMAT = "[a-z]\\s\\w+(:[a-z]\\s\\w+)*";

		if (!Pattern.matches(SCHEMA_FORMAT, schema))
			throw new IllegalArgumentException("Schema format not valid");

		argsInfo = new HashMap<>();
		String[] schemaParts = schema.split(SCHEMA_SEPARATOR);
		for (String schemaPart : schemaParts) {
			String[] schemaArg = schemaPart.split(ARGS_SEPARATOR);
			String flagName = schemaArg[0];
			String flagType = schemaArg[1];

			if (!ArgBuilder.typeExists(flagType))
				throw new IllegalArgumentException("Schema type not valid: " + flagType);

			argsInfo.put(flagName, flagType);
		}
	}

	public Set<String> getFlagNames() {
		return argsInfo.keySet();
	}

	public String getType(String flagName) {
		return argsInfo.get(flagName);
	}
}
