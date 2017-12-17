package es.rachelcarmena;

public class Parser {

    public static final String POST_SEPARATOR = " -> ";
    public static final String FOLLOWS = " follows ";
    public static final String WALL = " wall";

    public Command parse(String input) {
        if (input.contains(POST_SEPARATOR)) {
            String[] inputParts = input.split(POST_SEPARATOR);
            return new PostCommand(inputParts[0], inputParts[1]);
        }
        if (input.contains(FOLLOWS)) {
            String[] inputParts = input.split(FOLLOWS);
            return new FollowCommand(inputParts[0], inputParts[1]);
        }
        if (input.contains(WALL)) {
            String[] inputParts = input.split(WALL);
            return new WallCommand(inputParts[0]);
        }
        return new ReadCommand(input);
    }
}
