package es.rachelcarmena;

public class Greeting {
    public String greet(String... names) {
        if (names == null)
            return "Hello, my friend.";
        if (names.length == 2) {
            return String.format("Hello, %s and %s.", names[0], names[1]);
        }
        if (names.length > 2) {
            String previousNames = "";
            for (int index = 0; index < names.length - 1; index++)
                previousNames += String.format(" %s,", names[index]);
            return String.format("Hello,%s and %s.", previousNames, names[names.length - 1]);
        }
        if (names[0].toUpperCase().equals(names[0]))
            return String.format("HELLO %s!", names[0]);
        return String.format("Hello, %s.", names[0]);
    }
}
