package es.rachelcarmena;

public class Greeting {
    public String greet(String name) {
        if (name == null)
            return "Hello, my friend.";
        if (name.toUpperCase().equals(name))
            return String.format("HELLO %s!", name);
        return String.format("Hello, %s.", name);
    }
}
