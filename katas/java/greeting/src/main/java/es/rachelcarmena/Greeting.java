package es.rachelcarmena;

public class Greeting {
    public String greet(String name) {
        if (name == null)
            return "Hello, my friend.";
        return String.format("Hello, %s.", name);
    }
}
