package es.rachelcarmena;

import es.rachelcarmena.domain.MarsRover;
import es.rachelcarmena.domain.ObstacleManager;
import es.rachelcarmena.utils.Direction;
import es.rachelcarmena.utils.Position;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnitParamsRunner.class)
public class MarsRoverShould {

    private static final int ANY_X = 0;
    private static final int ANY_Y = 0;
    private static final Direction.Type ANY_DIRECTION = Direction.Type.EAST;
    private static final Position INITIAL_POSITION = new Position(ANY_X, ANY_Y);

    @Mock
    ObstacleManager obstacleManager;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_when_forward")
    public void change_position_when_move_forward(Direction.Type currentDirection, Position newPosition) {
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);
        given(obstacleManager.detectObstacleIn(any(Position.class))).willReturn(true);

        marsRover.moveForward(obstacleManager);

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
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, currentDirection);
        given(obstacleManager.detectObstacleIn(any(Position.class))).willReturn(true);

        marsRover.moveBackward(obstacleManager);

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

    @Test
    public void not_move_forward_if_obstacle_in_new_position() {
        ObstacleManager obstacleManager = new ObstacleManager();
        obstacleManager.addObstacleIn(new Position(ANY_X, ANY_Y + 1));
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, Direction.Type.NORTH);

        boolean moveDone = marsRover.moveForward(obstacleManager);

        assertFalse(moveDone);
        assertThat(marsRover.getPosition(), is(INITIAL_POSITION));
    }

    @Test
    public void not_move_backward_if_obstacle_in_new_position() {
        ObstacleManager obstacleManager = new ObstacleManager();
        obstacleManager.addObstacleIn(new Position(ANY_X, ANY_Y - 1));
        MarsRover marsRover = new MarsRover(INITIAL_POSITION, Direction.Type.NORTH);

        boolean moveDone = marsRover.moveBackward(obstacleManager);

        assertFalse(moveDone);
        assertThat(marsRover.getPosition(), is(INITIAL_POSITION));
    }
}