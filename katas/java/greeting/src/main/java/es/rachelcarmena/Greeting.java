package es.rachelcarmena;

import static es.rachelcarmena.UsualNames.USUAL_GREETING;

public class Greeting {

    private static final String ANONYMOUS_NAME = "my friend";

    public String greet(String... names) {
        if (names == null) {
            return String.format(USUAL_GREETING, ANONYMOUS_NAME);
        }

        GreetingCreator greetingCreator = new GreetingCreator(names);
        StringBuilder greeting = new StringBuilder();
        greeting.append(greetingCreator.withUsualNames());
        greeting.append(greetingCreator.withUpperCaseName());
        return greeting.toString();
    }
}