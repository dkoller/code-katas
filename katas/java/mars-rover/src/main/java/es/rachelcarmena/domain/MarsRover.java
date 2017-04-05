package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Location;
import es.rachelcarmena.utils.Location.Direction;

public class MarsRover {
    private Location location;
    private Direction direction;

    public MarsRover(Location location, Direction direction) {
        this.location = location;
        this.direction = direction;
    }

    public Object getDirection() {
        return direction;
    }

    public Location getLocation() {
        return location;
    }

    public boolean moveForward(ObstacleManager obstacleManager) {
        Location newLocation = location.calculateNewPosition(direction, true);
        if (!obstacleManager.detectObstacleIn(newLocation)) return false;
        location = newLocation;
        return true;
    }

    public boolean moveBackward(ObstacleManager obstacleManager) {
        Location newLocation = location.calculateNewPosition(direction, false);
        if (!obstacleManager.detectObstacleIn(newLocation)) return false;
        location = newLocation;
        return true;
    }

    public void turnOnTheRight() {
        direction = direction.turnRight();
    }

    public void turnOnTheLeft() {
        direction = direction.turnLeft();
    }
}