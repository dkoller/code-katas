package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Position;
import es.rachelcarmena.delivery.StatusReporter;
import es.rachelcarmena.utils.Location;

public class MarsRover {
    private Location location;
    private ObstacleManager obstacleManager;
    private StatusReporter statusReporter;

    public MarsRover(Location location, ObstacleManager obstacleManager, StatusReporter statusReporter) {
        this.location = location;
        this.obstacleManager = obstacleManager;
        this.statusReporter = statusReporter;
    }

    public Location getLocation() {
        return location;
    }

    public void moveForward() {
        Position newPosition = location.calculatePositionWhenForward();
        if (obstacleManager.detectObstacleIn(newPosition)) {
            statusReporter.reportObstacleIn(newPosition);
            return;
        }
        location = new Location(newPosition, location.getDirection());
    }

    public void moveBackward() {
        Position newPosition = location.calculatePositionWhenBackward();
        if (obstacleManager.detectObstacleIn(newPosition)) {
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