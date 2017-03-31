package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Direction.Type;
import es.rachelcarmena.utils.Position;

public class MarsRover {
    private Position position;
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
        position = position.calculateNewPosition(direction, true);
    }

    public void moveBackward() {
        position = position.calculateNewPosition(direction, false);
    }

    public void turnOnTheRight() {
        direction = direction.turnRight();
    }

    public void turnOnTheLeft() {
        direction = direction.turnLeft();
    }
}