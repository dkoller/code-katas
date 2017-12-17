package es.rachelcarmena;

public class Parser {
    public Command parse(String input) {
        if (input.contains(" -> ")) {
            String[] inputParts = input.split(" -> ");
            return new PostCommand(inputParts[0], inputParts[1]);
        }
        if (input.contains(" follows ")) {
            String[] inputParts = input.split(" follows ");
            return new FollowCommand(inputParts[0], inputParts[1]);
        }
        if (input.contains(" wall")) {
            String[] inputParts = input.split(" wall");
            return new WallCommand(inputParts[0]);
        }
        return new ReadCommand(input);
    }
}
