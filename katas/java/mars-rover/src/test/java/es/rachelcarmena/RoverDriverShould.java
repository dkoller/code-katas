package es.rachelcarmena;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoverDriverShould {

    @Test
    public void move_forward_when_f_command_and_north_direction() {
        RoverDriver roverDriver = new RoverDriver();
        MarsRover marsRover = new MarsRover(new Position(0, 0), Direction.Type.NORTH);

        roverDriver.send(marsRover, 'f');

        assertThat(marsRover.getDirection(), is(Direction.Type.NORTH));
        assertThat(marsRover.getCoordinateX(), is(0));
        assertThat(marsRover.getCoordinateY(), is(1));
    }
}
