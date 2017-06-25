package es.rachelcarmena;

public class Console {
    public void write(String message) {
        System.out.println(message);
    }

    public String read() {
        return System.console().readLine();
    }
}
