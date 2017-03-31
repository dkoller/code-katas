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

    public Position getPosition() {
        return position;
    }

    public void moveForward() {
        position.calculateNewPosition(direction, true);
    }

    public void moveBackward() {
        position.calculateNewPosition(direction, false);
    }

    public void turnRight() {
        direction = Direction.turnRight(direction);
    }

    public void turnLeft() {
        direction = Direction.turnLeft(direction);
    }
}