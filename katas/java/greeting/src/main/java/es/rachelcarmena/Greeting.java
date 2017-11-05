package es.rachelcarmena;

import es.rachelcarmena.creator.GreetingCreator;

public class Greeting {

    public String greet(String... names) {
        if (names == null) {
            return GreetingCreator.anonymous();
        }

        GreetingCreator greetingCreator = new GreetingCreator(names);
        StringBuilder greeting = new StringBuilder();
        greeting.append(greetingCreator.withCommonNames());
        greeting.append(greetingCreator.withUpperCaseName());
        return greeting.toString();
    }
}