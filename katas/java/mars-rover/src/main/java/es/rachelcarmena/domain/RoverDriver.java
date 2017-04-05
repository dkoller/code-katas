package es.rachelcarmena.domain;

public class RoverDriver {

    public void send(MarsRover marsRover, ObstacleManager obstacleManager, char... commands) {
        for(char command: commands) {
            switch (command) {
                case 'f':
                    marsRover.moveForward(obstacleManager);
                    break;
                case 'b':
                    marsRover.moveBackward(obstacleManager);
                    break;
                case 'r':
                    marsRover.turnOnTheRight();
                    break;
                case 'l':
                    marsRover.turnOnTheLeft();
                    break;
            }
        }
    }
}
