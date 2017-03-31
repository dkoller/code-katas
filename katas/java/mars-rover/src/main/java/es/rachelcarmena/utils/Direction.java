package es.rachelcarmena.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direction {

    public enum Type {
        NORTH, EAST, WEST, SOUTH;

        private static final List<Type> cardinalPoints = new ArrayList<>(Arrays.asList(Type.NORTH, Type.EAST, Type.SOUTH, Type.WEST));

        public Type turnRight() {
            return getNextCardinalPoint(1);
        }

        public Type turnLeft() {
            return getNextCardinalPoint(3);
        }

        private Type getNextCardinalPoint(int steps) {
            int index = cardinalPoints.indexOf(this);
            index = (index + steps) % 4;
            return cardinalPoints.get(index);
        }
    }
}
