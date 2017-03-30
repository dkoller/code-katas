package es.rachelcarmena;

public class MarsRover {
    private final Position position;
    private Direction.Type direction;

    public MarsRover(Position position, Direction.Type direction) {
        this.position = position;
        this.direction = direction;
    }

    public Object getDirection() {
        return direction;
    }

    public int getCoordinateX() {
        return 0;
    }

    public int getCoordinateY() {
        return 1;
    }
}
