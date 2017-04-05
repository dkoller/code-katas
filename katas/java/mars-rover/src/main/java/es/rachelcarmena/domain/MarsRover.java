package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Position.Direction;
import es.rachelcarmena.utils.Position;

public class MarsRover {
    private Position position;
    private Direction direction;

    public MarsRover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Object getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

    public boolean moveForward(ObstacleManager obstacleManager) {
        Position newPosition = position.calculateNewPosition(direction, true);
        if (!obstacleManager.detectObstacleIn(newPosition)) return false;
        position = newPosition;
        return true;
    }

    public boolean moveBackward(ObstacleManager obstacleManager) {
        Position newPosition = position.calculateNewPosition(direction, false);
        if (!obstacleManager.detectObstacleIn(newPosition)) return false;
        position = newPosition;
        return true;
    }

    public void turnOnTheRight() {
        direction = direction.turnRight();
    }

    public void turnOnTheLeft() {
        direction = direction.turnLeft();
    }
}