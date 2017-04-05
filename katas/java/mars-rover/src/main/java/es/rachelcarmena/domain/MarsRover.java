package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Position;
import es.rachelcarmena.delivery.StatusReporter;
import es.rachelcarmena.utils.Location;

public class MarsRover {
    private Location location;
    private StatusReporter statusReporter;

    public MarsRover(Location location, StatusReporter statusReporter) {
        this.location = location;
        this.statusReporter = statusReporter;
    }

    public Location getLocation() {
        return location;
    }

    public void moveForward(ObstacleManager obstacleManager) {
        Position newPosition = location.calculatePositionWhenForward();
        if (!obstacleManager.detectObstacleIn(newPosition)) {
            statusReporter.reportObstacleIn(newPosition);
            return;
        }
        location = new Location(newPosition, location.getDirection());
    }

    public void moveBackward(ObstacleManager obstacleManager) {
        Position newPosition = location.calculatePositionWhenBackward();
        if (!obstacleManager.detectObstacleIn(newPosition)) {
            statusReporter.reportObstacleIn(newPosition);
            return;
        }
        location = new Location(newPosition, location.getDirection());
    }

    public void turnOnTheRight() {
        location = location.calculateLocationWhenTurnRight();
    }

    public void turnOnTheLeft() {
        location = location.calculateLocationWhenTurnLeft();
    }
}