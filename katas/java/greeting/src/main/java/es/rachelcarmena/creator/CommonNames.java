package es.rachelcarmena.creator;

import java.util.List;

public class CommonNames {

    public static final String COMMON_GREETING = "Hello, %s.";
    private static final String SEPARATOR = ",";

    private final List<String> names;

    CommonNames(List<String> names) {
        this.names = names;
    }

    boolean exists() {
        return names.size() != 0;
    }

    String getGreeting() {
        if (names.size() == 1) {
            return String.format(COMMON_GREETING, names.get(0));
        }

        StringBuilder greetingNames = new StringBuilder(names.get(0));
        if (names.size() > 2) {
            greetingNames.append(SEPARATOR);
            for (int index = 1; index < names.size() - 1; index++) {
                greetingNames.append(" ");
                greetingNames.append(names.get(index));
                greetingNames.append(SEPARATOR);
            }
        }
        greetingNames.append(" and ");
        greetingNames.append(names.get(names.size() - 1));
        return String.format(COMMON_GREETING, greetingNames);
    }
}
