package es.rachelcarmena.domain;

import es.rachelcarmena.ObstacleManager;

public class RoverDriver {

    public boolean send(MarsRover marsRover, ObstacleManager obstacleManager, char... commands) {
        boolean movementDone;

        for(char command: commands) {
            switch (command) {
                case 'f':
                    movementDone = marsRover.moveForward(obstacleManager);
                    if (!movementDone) return false;
                    break;
                case 'b':
                    movementDone = marsRover.moveBackward(obstacleManager);
                    if (!movementDone) return false;
                    break;
                case 'r':
                    marsRover.turnOnTheRight();
                    break;
                case 'l':
                    marsRover.turnOnTheLeft();
                    break;
            }
        }
        return true;
    }
}
