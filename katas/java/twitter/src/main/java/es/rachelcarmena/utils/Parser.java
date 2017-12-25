package es.rachelcarmena.utils;

import es.rachelcarmena.domain.command.Command;
import es.rachelcarmena.domain.command.FollowCommand;
import es.rachelcarmena.domain.command.PostCommand;
import es.rachelcarmena.domain.command.ReadCommand;
import es.rachelcarmena.domain.command.WallCommand;

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
