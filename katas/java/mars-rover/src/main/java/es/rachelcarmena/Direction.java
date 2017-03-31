package es.rachelcarmena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direction {

    public enum Type {NORTH, EAST, WEST, SOUTH;}
    private static final List<Type> cardinalPoints = new ArrayList<>(Arrays.asList(Type.NORTH, Type.EAST, Type.SOUTH, Type.WEST));

    public static Type oppositeOf(Type direction) {
        return getNextCardinalPoint(direction, 2);
    }

    public static Type turnRight(Type direction) {
        return getNextCardinalPoint(direction, 1);
    }

    private static Type getNextCardinalPoint(Type direction, int steps) {
        int index = cardinalPoints.indexOf(direction);
        index = (index + steps) % 4;
        return cardinalPoints.get(index);
    }
}
