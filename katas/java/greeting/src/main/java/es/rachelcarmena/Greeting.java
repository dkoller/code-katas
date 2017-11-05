package es.rachelcarmena;

public class Greeting {

    private static final String USUAL_GREETING = "Hello, %s.";
    private static final String ANONYMOUS_NAME = "my friend";

    public String greet(String... names) {
        if (names == null)
            return String.format(USUAL_GREETING, ANONYMOUS_NAME);

        if (names[0].toUpperCase().equals(names[0]))
            return String.format("HELLO %s!", names[0]);

        if (names.length == 1) {
            return String.format(USUAL_GREETING, names[0]);
        }

        StringBuilder greetingNames = new StringBuilder(names[0]);
        if (names.length > 2) {
            greetingNames.append(",");
            for (int index = 1; index < names.length - 1; index++) {
                greetingNames.append(" ");
                greetingNames.append(names[index]);
                greetingNames.append(",");
            }
        }
        greetingNames.append(" and ");
        greetingNames.append(names[names.length - 1]);
        return String.format(USUAL_GREETING, greetingNames);
    }
}