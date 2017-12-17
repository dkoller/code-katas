package es.rachelcarmena;

public class Parser {
    public Command parse(String input) {
        if (input.contains(" -> ")) {
            String[] inputParts = input.split(" -> ");
            return new PostCommand(inputParts[0], inputParts[1]);
        }
        return new ReadCommand(input);
    }
}
