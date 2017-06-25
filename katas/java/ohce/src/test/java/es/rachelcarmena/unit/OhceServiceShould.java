package es.rachelcarmena.unit;

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

import static es.rachelcarmena.Interpreter.CLOSING_MESSAGE_INPUT;
import static es.rachelcarmena.Interpreter.MESSAGE_WHEN_PALINDROME;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class OhceServiceShould {

    private static final String STARTING_MESSAGE_INPUT = "ohce Ra";
    private static final String WELCOME_MESSAGE_OUTPUT = "¡Buenas noches Ra!";
    private static final String CLOSING_MESSAGE_OUTPUT = "Adios Ra";

    @Mock Clock clock ;
    @Mock Console console;
    OhceService ohceService;
    Interpreter interpreter;
    InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        interpreter = new Interpreter();
        ohceService = new OhceService(interpreter, clock, console);
        inOrder = inOrder(console);
    }

    @Test
    public void abort_if_not_receiving_opening_message() {
        given(clock.hour()).willReturn(1);
        given(console.read()).willReturn("invalidOpening Ra");

        ohceService.start();

        inOrder.verify(console).write("It should start with ohce");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void answer_if_receiving_opening_and_closing_messages() {
        given(clock.hour()).willReturn(1);
        given(console.read()).willReturn(STARTING_MESSAGE_INPUT, CLOSING_MESSAGE_INPUT);

        ohceService.start();

        inOrder.verify(console).write(WELCOME_MESSAGE_OUTPUT);
        inOrder.verify(console).write(CLOSING_MESSAGE_OUTPUT);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void answer_reverse_if_receiving_opening_message_and_another_string() {
        given(clock.hour()).willReturn(1);
        given(console.read()).willReturn(STARTING_MESSAGE_INPUT, STARTING_MESSAGE_INPUT, CLOSING_MESSAGE_INPUT);

        ohceService.start();

        inOrder.verify(console).write(WELCOME_MESSAGE_OUTPUT);
        inOrder.verify(console).write("aR echo");
        inOrder.verify(console).write(CLOSING_MESSAGE_OUTPUT);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void answer_reverses_if_receiving_opening_message_and_other_strings() {
        given(clock.hour()).willReturn(1);
        given(console.read()).willReturn(STARTING_MESSAGE_INPUT, "stop", "hola", CLOSING_MESSAGE_INPUT);

        ohceService.start();

        inOrder.verify(console).write(WELCOME_MESSAGE_OUTPUT);
        inOrder.verify(console).write("pots");
        inOrder.verify(console).write("aloh");
        inOrder.verify(console).write(CLOSING_MESSAGE_OUTPUT);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void detect_palindrome() {
        given(clock.hour()).willReturn(1);
        given(console.read()).willReturn(STARTING_MESSAGE_INPUT, "otos", "oto", CLOSING_MESSAGE_INPUT);

        ohceService.start();

        inOrder.verify(console).write(WELCOME_MESSAGE_OUTPUT);
        inOrder.verify(console).write("soto");
        inOrder.verify(console).write("oto");
        inOrder.verify(console).write(MESSAGE_WHEN_PALINDROME);
        inOrder.verify(console).write(CLOSING_MESSAGE_OUTPUT);
        inOrder.verifyNoMoreInteractions();
    }
}
