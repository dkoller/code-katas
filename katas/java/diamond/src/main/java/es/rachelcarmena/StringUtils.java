package es.rachelcarmena;

public class StringUtils {

	public static String getWhitespacesString(int numberOfSpaces) {
		StringBuilder format = new StringBuilder("%");
		format.append(numberOfSpaces);
		format.append("s");
		return String.format(format.toString(), "");
	}
}
