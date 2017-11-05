package es.rachelcarmena;

public class Greeting {

    public String greet(String... names) {
        GreetingCreator greetingCreator = new GreetingCreator(names);

        if (names == null) {
            return greetingCreator.anonymousGreating();
        }

        StringBuilder greeting = new StringBuilder();
        greeting.append(greetingCreator.withUsualNames());
        greeting.append(greetingCreator.withUpperCaseName());
        return greeting.toString();
    }
}