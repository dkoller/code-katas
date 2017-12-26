package es.rachelcarmena.infraestructure;

public class Console {
    java.io.Console console;

    public Console() {
        this.console = System.console();
    }

    public void print(String... messages) {
        for (String message: messages)
            console.printf("%s%n", message);
    }

    public String read() {
        return console.readLine();
    }
}
