package es.rachelcarmena;

import es.rachelcarmena.Direction.Type;

public class MarsRover {
    private final Position position;
    private Type direction;

    public MarsRover(Position position, Type direction) {
        this.position = position;
        this.direction = direction;
    }

    public Object getDirection() {
        return direction;
    }

    public int getCoordinateX() {
        return position.x;
    }

    public int getCoordinateY() {
        return position.y;
    }

    public void moveForward() {
        switch (direction) {
            case NORTH:
                position.y++;
                break;
            default:
                position.y--;
        }
    }
}