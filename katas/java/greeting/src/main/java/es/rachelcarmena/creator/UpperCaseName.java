package es.rachelcarmena.creator;

import es.rachelcarmena.common.Name;

import java.util.List;
import java.util.Optional;

public class UpperCaseName {

    private static final String SHOUTING_GREETING = "HELLO %s!";

    private final Optional<String> name;

    UpperCaseName(List<String> names) {
        this.name = names.stream().filter(name -> Name.isUpperCase(name)).findFirst();
    }

    boolean exists() {
        return name.isPresent();
    }

    String getGreeting() {
        return String.format(SHOUTING_GREETING, name.get());
    }
}