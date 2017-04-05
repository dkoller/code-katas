package es.rachelcarmena;

import es.rachelcarmena.delivery.StatusReporter;
import es.rachelcarmena.domain.MarsRover;
import es.rachelcarmena.domain.ObstacleManager;
import es.rachelcarmena.utils.Location;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnitParamsRunner.class)
public class MarsRoverShould {

    private static final int ANY_X = 0;
    private static final int ANY_Y = 0;
    private static final Location INITIAL_LOCATION = new Location(ANY_X, ANY_Y);

    @Mock
    ObstacleManager obstacleManager;
    @Mock
    StatusReporter statusReporter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_when_forward")
    public void change_position_when_move_forward(Location.Direction currentDirection, Location newLocation) {
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, currentDirection, statusReporter);
        given(obstacleManager.detectObstacleIn(any(Location.class))).willReturn(true);

        marsRover.moveForward(obstacleManager);

        assertThat(marsRover.getLocation(), is(newLocation));
        assertThat(marsRover.getDirection(), is(currentDirection));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_when_forward() {
        return new Object[]{new Object[]{Location.Direction.NORTH, new Location(ANY_X, ANY_Y + 1)},
                new Object[]{Location.Direction.SOUTH, new Location(ANY_X, ANY_Y - 1)},
                new Object[]{Location.Direction.EAST, new Location(ANY_X + 1, ANY_Y)},
                new Object[]{Location.Direction.WEST, new Location(ANY_X - 1, ANY_Y)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_when_backward")
    public void change_position_when_move_backward(Location.Direction currentDirection, Location newLocation) {
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, currentDirection, statusReporter);
        given(obstacleManager.detectObstacleIn(any(Location.class))).willReturn(true);

        marsRover.moveBackward(obstacleManager);

        assertThat(marsRover.getLocation(), is(newLocation));
        assertThat(marsRover.getDirection(), is(currentDirection));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_when_backward() {
        return new Object[]{new Object[]{Location.Direction.NORTH, new Location(ANY_X, ANY_Y - 1)},
                new Object[]{Location.Direction.SOUTH, new Location(ANY_X, ANY_Y + 1)},
                new Object[]{Location.Direction.EAST, new Location(ANY_X - 1, ANY_Y)},
                new Object[]{Location.Direction.WEST, new Location(ANY_X + 1, ANY_Y)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction_when_right")
    public void change_direction_when_turn_on_the_right(Location.Direction currentDirection, Location.Direction newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, currentDirection, statusReporter);

        marsRover.turnOnTheRight();

        assertThat(marsRover.getLocation(), is(INITIAL_LOCATION));
        assertThat(marsRover.getDirection(), is(newDirection));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction_when_right() {
        return new Object[]{new Object[]{Location.Direction.NORTH, Location.Direction.EAST},
                new Object[]{Location.Direction.SOUTH, Location.Direction.WEST},
                new Object[]{Location.Direction.EAST, Location.Direction.SOUTH},
                new Object[]{Location.Direction.WEST, Location.Direction.NORTH}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction_when_left")
    public void change_direction_when_turn_on_the_left(Location.Direction currentDirection, Location.Direction newDirection) {
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, currentDirection, statusReporter);

        marsRover.turnOnTheLeft();

        assertThat(marsRover.getLocation(), is(INITIAL_LOCATION));
        assertThat(marsRover.getDirection(), is(newDirection));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction_when_left() {
        return new Object[]{new Object[]{Location.Direction.NORTH, Location.Direction.WEST},
                new Object[]{Location.Direction.SOUTH, Location.Direction.EAST},
                new Object[]{Location.Direction.EAST, Location.Direction.NORTH},
                new Object[]{Location.Direction.WEST, Location.Direction.SOUTH}
        };
    }

    @Test
    public void not_move_forward_if_obstacle_in_new_position() {
        Location obstaclePosition = new Location(ANY_X, ANY_Y + 1);
        ObstacleManager obstacleManager = new ObstacleManager();
        obstacleManager.addObstacleIn(obstaclePosition);
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, Location.Direction.NORTH, statusReporter);

        marsRover.moveForward(obstacleManager);

        assertThat(marsRover.getLocation(), is(INITIAL_LOCATION));
        verify(statusReporter).reportObstacleIn(obstaclePosition);
    }

    @Test
    public void not_move_backward_if_obstacle_in_new_position() {
        Location obstaclePosition = new Location(ANY_X, ANY_Y - 1);
        ObstacleManager obstacleManager = new ObstacleManager();
        obstacleManager.addObstacleIn(obstaclePosition);
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, Location.Direction.NORTH, statusReporter);

        marsRover.moveBackward(obstacleManager);

        verify(statusReporter).reportObstacleIn(obstaclePosition);
    }
}