package es.rachelcarmena;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Greeting {

    private static final String USUAL_GREETING = "Hello, %s.";
    private static final String SHOUTING_GREETING = "HELLO %s!";

    private static final String ANONYMOUS_NAME = "my friend";
    private static final String SEPARATOR = ",";

    public String greet(String... names) {
        if (names == null)
            return String.format(USUAL_GREETING, ANONYMOUS_NAME);

        List<String> notUpperCaseNames = getNotUpperCaseName(names);
        Optional<String> upperCaseName = getUpperCaseNames(names);

        StringBuilder greeting = new StringBuilder();
        if (notUpperCaseNames.size() != 0) {
            greeting.append(createGreetingWithoutUppercaseNames(notUpperCaseNames));
        }

        if (upperCaseName.isPresent()) {
            if (greeting.length() > 0)
                greeting.append(" AND ");
            greeting.append(createGreetingWithUpperCaseNames(upperCaseName.get()));
        }

        return greeting.toString();
    }

    private boolean isUpperCase(String name) {
        return name.toUpperCase().equals(name);
    }

    private Optional<String> getUpperCaseNames(String[] names) {
        return Arrays.stream(names).filter(name -> isUpperCase(name)).findFirst();
    }

    private List<String> getNotUpperCaseName(String[] names) {
        return Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());
    }

    private String createGreetingWithUpperCaseNames(String name) {
        return String.format(SHOUTING_GREETING, name);
    }

    private String createGreetingWithoutUppercaseNames(List<String> names) {
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
}