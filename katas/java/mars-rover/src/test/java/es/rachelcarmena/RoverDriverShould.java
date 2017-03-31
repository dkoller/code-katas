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
    @Parameters(method = "pairs_of_direction_and_new_position")
    public void move_in_same_direction_when_forward_command(Direction.Type currentDirection, Position newPosition) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'f');

        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(currentDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position() {
        return new Object[]{new Object[]{Direction.Type.NORTH, new Position(ANY_X, ANY_Y + 1)},
                new Object[]{Direction.Type.SOUTH, new Position(ANY_X, ANY_Y - 1)},
                new Object[]{Direction.Type.EAST, new Position(ANY_X + 1, ANY_Y)},
                new Object[]{Direction.Type.WEST, new Position(ANY_X - 1, ANY_Y)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_and_direction")
    public void move_in_opposite_direction_when_backward_command(Direction.Type currentDirection, Position newPosition, Direction.Type newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'b');

        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(newDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_and_direction() {
        return new Object[]{new Object[]{Direction.Type.NORTH, new Position(ANY_X, ANY_Y - 1), Direction.Type.SOUTH},
                new Object[]{Direction.Type.SOUTH, new Position(ANY_X, ANY_Y + 1), Direction.Type.NORTH},
                new Object[]{Direction.Type.EAST, new Position(ANY_X - 1, ANY_Y), Direction.Type.WEST},
                new Object[]{Direction.Type.WEST, new Position(ANY_X + 1, ANY_Y), Direction.Type.EAST}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction")
    public void turn_to_right_when_right_command(Direction.Type currentDirection, Direction.Type newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'r');

        assertThat(marsRover.getPosition(), is(INITIAL_POSITION));
        assertThat(marsRover.getDirection(), is(newDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction() {
        return new Object[]{new Object[]{Direction.Type.NORTH, Direction.Type.EAST},
                new Object[]{Direction.Type.SOUTH, Direction.Type.WEST},
                new Object[]{Direction.Type.EAST, Direction.Type.SOUTH},
                new Object[]{Direction.Type.WEST, Direction.Type.NORTH}
        };
    }
}
