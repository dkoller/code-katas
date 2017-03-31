package es.rachelcarmena;

import es.rachelcarmena.domain.MarsRover;
import es.rachelcarmena.domain.RoverDriver;
import es.rachelcarmena.utils.Direction;
import es.rachelcarmena.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoverDriverShould {

    private static final int ANY_X = 0;
    private static final int ANY_Y = 0;

    private Position INITIAL_POSITION;
    private RoverDriver roverDriver;

    @Before
    public void setUp() throws Exception {
        INITIAL_POSITION = new Position(ANY_X, ANY_Y);
        roverDriver = new RoverDriver();
    }

    @Test
    public void send_several_commands() {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, Direction.Type.NORTH);

        roverDriver.send(marsRover, 'l', 'r', 'f', 'f', 'b', 'l');

        Position newPosition = new Position(ANY_X, ANY_Y + 1);
        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(Direction.Type.WEST));
    }
}
