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

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class RoverDriverShould {

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
        roverDriver.send(marsRover, 'l', 'r', 'f', 'f', 'b', 'l');

        inOrder.verify(marsRover).turnOnTheLeft();
        inOrder.verify(marsRover).turnOnTheRight();
        inOrder.verify(marsRover, times(2)).moveForward();
        inOrder.verify(marsRover).moveBackward();
        inOrder.verify(marsRover).turnOnTheLeft();
    }
}
