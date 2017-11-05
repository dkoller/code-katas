package es.rachelcarmena.creator;

import es.rachelcarmena.common.Name;

import java.util.Arrays;
import java.util.Optional;

public class UpperCaseName {

    private static final String SHOUTING_GREETING = "HELLO %s!";

    private final Optional<String> name;

    UpperCaseName(String[] names) {
        this.name = Arrays.stream(names).filter(name -> Name.isUpperCase(name)).findFirst();
    }

    boolean exists() {
        return name.isPresent();
    }

    String getGreeting() {
        return String.format(SHOUTING_GREETING, name.get());
    }
}