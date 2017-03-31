package es.rachelcarmena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direction {

    private static final List<Type> cardinalPoints = new ArrayList<>(Arrays.asList(Type.NORTH, Type.EAST, Type.SOUTH, Type.WEST));

    public static Type turnRight(Type direction) {
        return getNextCardinalPoint(direction, 1);
    }

    public static Type turnLeft(Type direction) {
        return getNextCardinalPoint(direction, 3);
    }

    private static Type getNextCardinalPoint(Type direction, int steps) {
        int index = cardinalPoints.indexOf(direction);
        index = (index + steps) % 4;
        return cardinalPoints.get(index);
    }

    public enum Type {NORTH, EAST, WEST, SOUTH;}
}
