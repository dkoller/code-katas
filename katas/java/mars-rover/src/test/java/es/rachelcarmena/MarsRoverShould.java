package es.rachelcarmena;

import es.rachelcarmena.delivery.StatusReporter;
import es.rachelcarmena.domain.MarsRover;
import es.rachelcarmena.domain.ObstacleManager;
import es.rachelcarmena.utils.Location;
import es.rachelcarmena.utils.Position;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import es.rachelcarmena.utils.Direction;
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
    private static final Position ANY_POSITION = new Position(ANY_X, ANY_Y);

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
    public void change_position_when_move_forward(Location currentLocation, Location newLocation) {
        MarsRover marsRover = new MarsRover(currentLocation, obstacleManager, statusReporter);
        given(obstacleManager.detectObstacleIn(any(Position.class))).willReturn(false);

        marsRover.moveForward();

        assertThat(marsRover.getLocation(), is(newLocation));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_when_forward() {
        return new Object[]{
                new Object[]{new Location(ANY_POSITION, Direction.NORTH), new Location(new Position(ANY_X, ANY_Y + 1), Direction.NORTH)},
                new Object[]{new Location(ANY_POSITION, Direction.SOUTH), new Location(new Position(ANY_X, ANY_Y - 1), Direction.SOUTH)},
                new Object[]{new Location(ANY_POSITION, Direction.EAST), new Location(new Position(ANY_X + 1, ANY_Y), Direction.EAST)},
                new Object[]{new Location(ANY_POSITION, Direction.WEST), new Location(new Position(ANY_X - 1, ANY_Y), Direction.WEST)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_position_when_backward")
    public void change_position_when_move_backward(Location currentLocation, Location newLocation) {
        MarsRover marsRover = new MarsRover(currentLocation, obstacleManager, statusReporter);
        given(obstacleManager.detectObstacleIn(any(Position.class))).willReturn(false);

        marsRover.moveBackward();

        assertThat(marsRover.getLocation(), is(newLocation));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_position_when_backward() {
        return new Object[]{
                new Object[]{new Location(ANY_POSITION, Direction.NORTH), new Location(new Position(ANY_X, ANY_Y - 1), Direction.NORTH)},
                new Object[]{new Location(ANY_POSITION, Direction.SOUTH), new Location(new Position(ANY_X, ANY_Y + 1), Direction.SOUTH)},
                new Object[]{new Location(ANY_POSITION, Direction.EAST), new Location(new Position(ANY_X - 1, ANY_Y), Direction.EAST)},
                new Object[]{new Location(ANY_POSITION, Direction.WEST), new Location(new Position(ANY_X + 1, ANY_Y), Direction.WEST)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction_when_right")
    public void change_direction_when_turn_on_the_right(Location currentLocation, Location newLocation) {
        MarsRover marsRover = new MarsRover(currentLocation, obstacleManager, statusReporter);

        marsRover.turnOnTheRight();

        assertThat(marsRover.getLocation(), is(newLocation));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction_when_right() {
        return new Object[]{
                new Object[]{new Location(ANY_POSITION, Direction.NORTH), new Location(ANY_POSITION, Direction.EAST)},
                new Object[]{new Location(ANY_POSITION, Direction.SOUTH), new Location(ANY_POSITION, Direction.WEST)},
                new Object[]{new Location(ANY_POSITION, Direction.EAST), new Location(ANY_POSITION, Direction.SOUTH)},
                new Object[]{new Location(ANY_POSITION, Direction.WEST), new Location(ANY_POSITION, Direction.NORTH)}
        };
    }

    @Test
    @Parameters(method = "pairs_of_direction_and_new_direction_when_left")
    public void change_direction_when_turn_on_the_left(Location currentLocation, Location newLocation) {
        MarsRover marsRover = new MarsRover(currentLocation, obstacleManager, statusReporter);

        marsRover.turnOnTheLeft();

        assertThat(marsRover.getLocation(), is(newLocation));
        verifyZeroInteractions(statusReporter);
    }

    @SuppressWarnings("unused")
    private Object pairs_of_direction_and_new_direction_when_left() {
        return new Object[]{
                new Object[]{new Location(ANY_POSITION, Direction.NORTH), new Location(ANY_POSITION, Direction.WEST)},
                new Object[]{new Location(ANY_POSITION, Direction.SOUTH), new Location(ANY_POSITION, Direction.EAST)},
                new Object[]{new Location(ANY_POSITION, Direction.EAST), new Location(ANY_POSITION, Direction.NORTH)},
                new Object[]{new Location(ANY_POSITION, Direction.WEST), new Location(ANY_POSITION, Direction.SOUTH)}
        };
    }

    @Test
    public void not_change_location_and_report_obstacle_if_obstacle_in_expected_position_after_forward() {
        final Location INITIAL_LOCATION = new Location(ANY_POSITION, Direction.NORTH);
        final Position EXPECTED_POSITION_IF_FORWARD = new Position(ANY_X, ANY_Y + 1);
        ObstacleManager obstacleManager = getObstacleManagerWithObstacleIn(EXPECTED_POSITION_IF_FORWARD);
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, obstacleManager, statusReporter);

        marsRover.moveForward();

        assertThat(marsRover.getLocation(), is(INITIAL_LOCATION));
        verify(statusReporter).reportObstacleIn(EXPECTED_POSITION_IF_FORWARD);
    }

    @Test
    public void not_change_location_and_report_obstacle_if_obstacle_in_expected_position_after_backward() {
        final Location INITIAL_LOCATION = new Location(ANY_POSITION, Direction.NORTH);
        final Position EXPECTED_POSITION_IF_BACKWARD = new Position(ANY_X, ANY_Y - 1);
        ObstacleManager obstacleManager = getObstacleManagerWithObstacleIn(EXPECTED_POSITION_IF_BACKWARD);
        MarsRover marsRover = new MarsRover(INITIAL_LOCATION, obstacleManager, statusReporter);

        marsRover.moveBackward();

        assertThat(marsRover.getLocation(), is(INITIAL_LOCATION));
        verify(statusReporter).reportObstacleIn(EXPECTED_POSITION_IF_BACKWARD);
    }

    @NotNull
    private ObstacleManager getObstacleManagerWithObstacleIn(Position position) {
        ObstacleManager obstacleManager = new ObstacleManager();
        obstacleManager.addObstacleIn(position);
        return obstacleManager;
    }
}