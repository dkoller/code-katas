package es.rachelcarmena;

import es.rachelcarmena.creator.GreetingCreator;
import es.rachelcarmena.creator.NamesSplitter;

public class Greeting {

    public String greet(String... names) {
        if (names == null) {
            return GreetingCreator.anonymous();
        }

        NamesSplitter namesSplitter = new NamesSplitter(names);
        GreetingCreator greetingCreator = new GreetingCreator(namesSplitter);

        StringBuilder greeting = new StringBuilder();
        greeting.append(greetingCreator.withCommonNames());
        greeting.append(greetingCreator.withUpperCaseName());
        return greeting.toString();
    }
}