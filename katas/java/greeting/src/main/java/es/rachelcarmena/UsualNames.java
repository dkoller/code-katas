package es.rachelcarmena;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsualNames {

    static final String USUAL_GREETING = "Hello, %s.";
    private static final String SEPARATOR = ",";

    private final List<String> names;

    UsualNames(String[] names) {
        this.names = Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());
    }

    boolean exists() {
        return names.size() != 0;
    }

    String getGreeting() {
        if (names.size() == 1) {
            return String.format(USUAL_GREETING, names.get(0));
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
        return String.format(USUAL_GREETING, greetingNames);
    }

    private boolean isUpperCase(String name) {
        return name.toUpperCase().equals(name);
    }
}
