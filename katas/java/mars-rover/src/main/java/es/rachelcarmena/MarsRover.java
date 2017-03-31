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
        calculateNewPosition(true);
    }

    public void moveBackward() {
        calculateNewPosition(false);
    }

    private void calculateNewPosition(boolean forward) {
        switch (direction) {
            case NORTH:
                position.y = forward ? position.y + 1 : position.y - 1;
                break;
            case SOUTH:
                position.y = forward ? position.y - 1 : position.y + 1;
                break;
            case EAST:
                position.x = forward ? position.x + 1 : position.x - 1;
                break;
            default:
                position.x = forward ? position.x - 1 : position.x + 1;
        }
    }

    public Position getPosition() {
        return position;
    }

    public void turnRight() {
        direction = Direction.turnRight(direction);
    }

    public void turnLeft() {
        direction = Direction.turnLeft(direction);
    }
}