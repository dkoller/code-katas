package es.rachelcarmena.domain;

public class RoverDriver {

    private MarsRover marsRover;

    public RoverDriver(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    public void sendCommands(Command... commands) {
        for(Command command: commands) {
            switch (command) {
                case FORWARD:
                    marsRover.moveForward();
                    break;
                case BACKWARD:
                    marsRover.moveBackward();
                    break;
                case RIGHT:
                    marsRover.turnOnTheRight();
                    break;
                case LEFT:
                    marsRover.turnOnTheLeft();
                    break;
            }
        }
    }
}
