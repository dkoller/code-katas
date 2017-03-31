package es.rachelcarmena;

import es.rachelcarmena.domain.MarsRover;
import es.rachelcarmena.domain.RoverDriver;
import es.rachelcarmena.utils.Direction;
import es.rachelcarmena.utils.Position;
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
    @Parameters(method = "pairs_of_direction_and_new_position_when_forward")
    public void move_forward_when_f_command(Direction.Type currentDirection, Position newPosition) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'f');

        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(currentDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_when_forward() {
        return new Object[]{new Object[]{Direction.Type.NORTH, new Position(ANY_X, ANY_Y + 1)},
                new Object[]{Direction.Type.SOUTH, new Position(ANY_X, ANY_Y - 1)},
                new Object[]{Direction.Type.EAST, new Position(ANY_X + 1, ANY_Y)},
                new Object[]{Direction.Type.WEST, new Position(ANY_X - 1, ANY_Y)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_when_backward")
    public void move_backward_when_b_command(Direction.Type currentDirection, Position newPosition) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'b');

        assertThat(marsRover.getPosition(), is(newPosition));
        assertThat(marsRover.getDirection(), is(currentDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_when_backward() {
        return new Object[]{new Object[]{Direction.Type.NORTH, new Position(ANY_X, ANY_Y - 1)},
                new Object[]{Direction.Type.SOUTH, new Position(ANY_X, ANY_Y + 1)},
                new Object[]{Direction.Type.EAST, new Position(ANY_X - 1, ANY_Y)},
                new Object[]{Direction.Type.WEST, new Position(ANY_X + 1, ANY_Y)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction_when_right")
    public void turn_to_right_when_right_command(Direction.Type currentDirection, Direction.Type newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'r');

        assertThat(marsRover.getPosition(), is(INITIAL_POSITION));
        assertThat(marsRover.getDirection(), is(newDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction_when_right() {
        return new Object[]{new Object[]{Direction.Type.NORTH, Direction.Type.EAST},
                new Object[]{Direction.Type.SOUTH, Direction.Type.WEST},
                new Object[]{Direction.Type.EAST, Direction.Type.SOUTH},
                new Object[]{Direction.Type.WEST, Direction.Type.NORTH}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction_when_left")
    public void turn_to_right_when_left_command(Direction.Type currentDirection, Direction.Type newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        roverDriver.send(marsRover, 'l');

        assertThat(marsRover.getPosition(), is(INITIAL_POSITION));
        assertThat(marsRover.getDirection(), is(newDirection));
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction_when_left() {
        return new Object[]{new Object[]{Direction.Type.NORTH, Direction.Type.WEST},
                new Object[]{Direction.Type.SOUTH, Direction.Type.EAST},
                new Object[]{Direction.Type.EAST, Direction.Type.NORTH},
                new Object[]{Direction.Type.WEST, Direction.Type.SOUTH}
        };
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
