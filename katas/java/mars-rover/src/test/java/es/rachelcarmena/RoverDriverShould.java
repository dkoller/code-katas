package es.rachelcarmena;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
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
    @Parameters(method = "pairs_of_command_direction_and_new_position")
    public void move_forward_when_f_command_and_north_direction(char command, Direction.Type direction, Position newPosition) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, direction);

        roverDriver.send(marsRover, command);

        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(direction));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_command_direction_and_new_position() {
        return new Object[]{new Object[]{'f', Direction.Type.NORTH, new Position(ANY_X, ANY_Y + 1)},
                new Object[]{'f', Direction.Type.SOUTH, new Position(ANY_X, ANY_Y - 1)},
                new Object[]{'f', Direction.Type.EAST, new Position(ANY_X + 1, ANY_Y)},
                new Object[]{'f', Direction.Type.WEST, new Position(ANY_X - 1, ANY_Y)}
        };
    }
}
