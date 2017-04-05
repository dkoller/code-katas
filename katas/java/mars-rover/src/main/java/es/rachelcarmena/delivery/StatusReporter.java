package es.rachelcarmena.delivery;

import es.rachelcarmena.utils.Location;

public class StatusReporter {
    private Console console;

    public StatusReporter(Console console) {
        this.console = console;
    }

    public void reportObstacleIn(Location obstaclePosition) {
        String message = String.format("Obstacle found in: %s", obstaclePosition.toString());
        console.print(message);
    }
}
