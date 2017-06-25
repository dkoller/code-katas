package es.rachelcarmena.acceptance;

import es.rachelcarmena.Clock;
import es.rachelcarmena.Console;
import es.rachelcarmena.Interpreter;
import es.rachelcarmena.OhceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class OhceServiceFeature {

    public static final int HOUR = 6;
    public static final String PALINDROME = "oto";
    @Mock
    Clock clock;

    @Mock
    Console console;

    private OhceService ohceService;
    private InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        Interpreter interpreter = new Interpreter();
        ohceService = new OhceService(interpreter, clock, console);
        inOrder = inOrder(console);
    }

    @Test
    public void should_echoes_the_reverse_of_console_inputs() {
        given(clock.hour()).willReturn(HOUR);
        given(console.read()).willReturn("ohce Ra", "hola", PALINDROME, "stop", "Stop!");

        ohceService.start();

        inOrder.verify(console).read();
        inOrder.verify(console).write("¡Buenos días Ra!");

        inOrder.verify(console).read();
        inOrder.verify(console).write("aloh");

        inOrder.verify(console).read();
        inOrder.verify(console).write(PALINDROME);
        inOrder.verify(console).write("¡Bonita palabra!");

        inOrder.verify(console).read();
        inOrder.verify(console).write("pots");

        inOrder.verify(console).read();
        inOrder.verify(console).write("Adios Ra");

        inOrder.verifyNoMoreInteractions();
    }
}