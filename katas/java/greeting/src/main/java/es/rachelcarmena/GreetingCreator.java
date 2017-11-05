package es.rachelcarmena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GreetingCreator {

    private static final String USUAL_GREETING = "Hello, %s.";
    private static final String SHOUTING_GREETING = "HELLO %s!";
    private static final String ANONYMOUS_NAME = "my friend";
    private static final String EMPTY_GREETING = "";
    private static final String SEPARATOR = ",";

    private List<String> usualNames = new ArrayList<>();
    private Optional<String> upperCaseName = Optional.empty();

    public GreetingCreator(String... names) {
        if (names != null) {
            usualNames = Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());
            upperCaseName = Arrays.stream(names).filter(name -> isUpperCase(name)).findFirst();
        }
    }

    public String anonymousGreating() {
        return String.format(USUAL_GREETING, ANONYMOUS_NAME);
    }

    public String withUsualNames() {
        if (!hasUsualNames())
            return EMPTY_GREETING;
        return createGreetingWithUsualNames();
    }

    public String withUpperCaseName() {
        if (!hasUpperCaseName())
            return EMPTY_GREETING;

        String greeting = createGreetingWithUpperCaseName();
        if (!hasUsualNames())
            return greeting;

        return String.format(" AND %s", greeting);
    }

    private boolean hasUsualNames() {
        return usualNames.size() != 0;
    }

    private boolean hasUpperCaseName() {
        return upperCaseName.isPresent();
    }

    private String createGreetingWithUpperCaseName() {
        return String.format(SHOUTING_GREETING, upperCaseName.get());
    }

    private String createGreetingWithUsualNames() {
        if (usualNames.size() == 1) {
            return String.format(USUAL_GREETING, usualNames.get(0));
        }

        StringBuilder greetingNames = new StringBuilder(usualNames.get(0));
        if (usualNames.size() > 2) {
            greetingNames.append(SEPARATOR);
            for (int index = 1; index < usualNames.size() - 1; index++) {
                greetingNames.append(" ");
                greetingNames.append(usualNames.get(index));
                greetingNames.append(SEPARATOR);
            }
        }
        greetingNames.append(" and ");
        greetingNames.append(usualNames.get(usualNames.size() - 1));
        return String.format(USUAL_GREETING, greetingNames);
    }

    private boolean isUpperCase(String name) {
        return name.toUpperCase().equals(name);
    }
}