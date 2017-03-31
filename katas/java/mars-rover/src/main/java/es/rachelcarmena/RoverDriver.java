package es.rachelcarmena;

public class RoverDriver {

    public void send(MarsRover marsRover, char... commands) {
        for(char command: commands) {
            switch (command) {
                case 'f':
                    marsRover.moveForward();
                    break;
                case 'b':
                    marsRover.moveBackward();
                    break;
                case 'r':
                    marsRover.turnRight();
                    break;
                case 'l':
                    marsRover.turnLeft();
                    break;
            }
        }
    }
}
