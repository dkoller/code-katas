package es.rachelcarmena.domain;

public class RoverDriver {

    private MarsRover marsRover;

    public RoverDriver(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    public void sendCommands(char... commands) {
        for(char command: commands) {
            switch (command) {
                case 'f':
                    marsRover.moveForward();
                    break;
                case 'b':
                    marsRover.moveBackward();
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
