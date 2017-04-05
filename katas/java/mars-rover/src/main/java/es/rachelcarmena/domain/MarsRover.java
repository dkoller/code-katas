package es.rachelcarmena.domain;

import es.rachelcarmena.delivery.StatusReporter;
import es.rachelcarmena.utils.Location;
import es.rachelcarmena.utils.Location.Direction;

public class MarsRover {
    private Location location;
    private Direction direction;
    private StatusReporter statusReporter;

    public MarsRover(Location location, Direction direction, StatusReporter statusReporter) {
        this.location = location;
        this.direction = direction;
        this.statusReporter = statusReporter;
    }

    public Object getDirection() {
        return direction;
    }

    public Location getLocation() {
        return location;
    }

    public void moveForward(ObstacleManager obstacleManager) {
        Location newLocation = location.calculateNewPosition(direction, true);
        if (!obstacleManager.detectObstacleIn(newLocation)) {
            statusReporter.reportObstacleIn(newLocation);
            return;
        }
        location = newLocation;
    }

    public void moveBackward(ObstacleManager obstacleManager) {
        Location newLocation = location.calculateNewPosition(direction, false);
        if (!obstacleManager.detectObstacleIn(newLocation)) {
            statusReporter.reportObstacleIn(newLocation);
            return;
        }
        location = newLocation;
    }

    public void turnOnTheRight() {
        direction = direction.turnRight();
    }

    public void turnOnTheLeft() {
        direction = direction.turnLeft();
    }
}