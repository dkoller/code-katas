package es.rachelcarmena;

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
    public void move_forward_when_f_command_and_north_direction() {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, Direction.Type.NORTH);

        roverDriver.send(marsRover, 'f');

        Position newPosition = new Position(ANY_X, ANY_Y + 1);
        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(Direction.Type.NORTH));
    }

    @Test
    public void move_forward_when_f_command_and_south_direction() {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, Direction.Type.SOUTH);

        roverDriver.send(marsRover, 'f');

        Position newPosition = new Position(ANY_X, ANY_Y - 1);
        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(Direction.Type.SOUTH));
    }

    @Test
    public void move_forward_when_f_command_and_east_direction() {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, Direction.Type.EAST);

        roverDriver.send(marsRover, 'f');

        Position newPosition = new Position(ANY_X + 1, ANY_Y);
        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(Direction.Type.EAST));
    }
}
