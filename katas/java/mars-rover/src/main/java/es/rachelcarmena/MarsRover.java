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

    public void moveForward() {
        switch (direction) {
            case NORTH:
                position.y++;
                break;
            case SOUTH:
                position.y--;
                break;
            case EAST:
                position.x++;
                break;
            default:
                position.x--;
        }
    }

    public Position getPosition() {
        return position;
    }
}