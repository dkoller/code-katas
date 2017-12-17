package es.rachelcarmena;

public class Parser {
    public Command parse(String input) {
        String[] inputParts = input.split(" -> ");
        return new PostCommand(inputParts[0], inputParts[1]);
    }
}
