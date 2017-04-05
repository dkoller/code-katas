package es.rachelcarmena;

import es.rachelcarmena.domain.MarsRover;
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

    @Mock
    MarsRover marsRover;
    InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        inOrder = inOrder(marsRover);
    }

    @Test
    public void send_commands_to_mars_rover() {
        RoverDriver roverDriver = new RoverDriver(marsRover);

        roverDriver.sendCommands('l', 'r', 'f', 'f', 'b', 'l');

        inOrder.verify(marsRover).turnOnTheLeft();
        inOrder.verify(marsRover).turnOnTheRight();
        inOrder.verify(marsRover, times(2)).moveForward();
        inOrder.verify(marsRover).moveBackward();
        inOrder.verify(marsRover).turnOnTheLeft();
    }
}
