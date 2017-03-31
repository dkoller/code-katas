package es.rachelcarmena;

public class Direction {

    public enum Type {NORTH, EAST, WEST, SOUTH;}

    public static Type getOppositeOf(Type direction) {
        switch (direction) {
            case NORTH:
                return Type.SOUTH;
            case SOUTH:
                return Type.NORTH;
            case EAST:
                return Type.WEST;
            case WEST:
                return Type.EAST;
        }
        return null;
    }
}
