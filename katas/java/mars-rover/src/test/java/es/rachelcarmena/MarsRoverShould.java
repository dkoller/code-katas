package es.rachelcarmena;

import es.rachelcarmena.domain.MarsRover;
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
public class MarsRoverShould {

    private static final int ANY_X = 0;
    private static final int ANY_Y = 0;

    private Position INITIAL_POSITION;

    @Before
    public void setUp() throws Exception {
        INITIAL_POSITION = new Position(ANY_X, ANY_Y);
    }


    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_when_forward")
    public void change_position_when_move_forward(Direction.Type currentDirection, Position newPosition) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        marsRover.moveForward();

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
    public void change_position_when_move_backward(Direction.Type currentDirection, Position newPosition) {
        es.rachelcarmena.domain.MarsRover marsRover = new es.rachelcarmena.domain.MarsRover(INITIAL_POSITION, currentDirection);

        marsRover.moveBackward();

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
    public void change_direction_when_turn_on_the_right(Direction.Type currentDirection, Direction.Type newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        marsRover.turnOnTheRight();

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
    public void change_direction_when_turn_on_the_left(Direction.Type currentDirection, Direction.Type newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);

        marsRover.turnOnTheLeft();

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
}