package es.rachelcarmena;

import es.rachelcarmena.domain.MarsRover;
import es.rachelcarmena.domain.ObstacleManager;
import es.rachelcarmena.domain.RoverDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class RoverDriverShould {

    private final ObstacleManager obstacleManager = new ObstacleManager();
    private final RoverDriver roverDriver = new RoverDriver();
    @Mock
    MarsRover marsRover;
    InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        inOrder = inOrder(marsRover);
    }

    @Test
    public void send_commands_to_mars_rover() {
        given(marsRover.moveBackward(obstacleManager)).willReturn(true);
        given(marsRover.moveForward(obstacleManager)).willReturn(true);

        roverDriver.send(marsRover, obstacleManager, 'l', 'r', 'f', 'f', 'b', 'l');

        inOrder.verify(marsRover).turnOnTheLeft();
        inOrder.verify(marsRover).turnOnTheRight();
        inOrder.verify(marsRover, times(2)).moveForward(obstacleManager);
        inOrder.verify(marsRover).moveBackward(obstacleManager);
        inOrder.verify(marsRover).turnOnTheLeft();
    }

    @Test
    public void inform_if_one_command_not_successful() {
        given(marsRover.moveForward(obstacleManager)).willReturn(false);

        boolean successAllCommands = roverDriver.send(marsRover, obstacleManager, 'l', 'r', 'f', 'f', 'b', 'l');

        assertFalse(successAllCommands);
    }
}
