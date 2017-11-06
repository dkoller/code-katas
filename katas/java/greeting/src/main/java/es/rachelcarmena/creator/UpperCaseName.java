package es.rachelcarmena.creator;

import java.util.Optional;

public class UpperCaseName {

    private static final String SHOUTING_GREETING = "HELLO %s!";

    private final Optional<String> name;

    UpperCaseName(Optional<String> name) {
        this.name = name;
    }

    boolean exists() {
        return name.isPresent();
    }

    String getGreeting() {
        return String.format(SHOUTING_GREETING, name.get());
    }
}