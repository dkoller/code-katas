package es.rachelcarmena.unit;

import es.rachelcarmena.InterpreterResult;
import es.rachelcarmena.Interpreter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static es.rachelcarmena.Interpreter.MESSAGE_WHEN_PALINDROME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnitParamsRunner.class)
public class InterpreterShould {

    private Interpreter interpreter;

    @Before
    public void setUp() throws Exception {
        interpreter = new Interpreter();
    }

    @Test
    public void return_welcome_message_if_supplying_night_hour() {
        InterpreterResult result = interpreter.interpret("ohce Ra", 20);

        assertThat(result.messages().size(), is(1));
        assertThat(result.messages().get(0), is("¡Buenas noches Ra!"));
    }

    @Test
    public void return_welcome_message_if_supplying_morning_hour() {
        InterpreterResult result = interpreter.interpret("ohce Ra", 6);

        assertThat(result.messages().size(), is(1));
        assertThat(result.messages().get(0), is("¡Buenos días Ra!"));
    }

    @Test
    public void return_welcome_message_if_supplying_evening_hour() {
        InterpreterResult result = interpreter.interpret("ohce Ra", 12);

        assertThat(result.messages().size(), is(1));
        assertThat(result.messages().get(0), is("¡Buenas tardes Ra!"));
    }

    @Test
    public void return_welcome_message_if_supplying_evening_hour_and_composed_name() {
        InterpreterResult result = interpreter.interpret("ohce Girl Smiling", 12);

        assertThat(result.messages().size(), is(1));
        assertThat(result.messages().get(0), is("¡Buenas tardes Girl Smiling!"));
    }

    @Test
    public void return_closing_message_if_supplying_stop() {
        interpreter.interpret("ohce Rachel", 20);
        InterpreterResult result = interpreter.interpret("Stop!");

        assertThat(result.messages().get(0), is("Adios Rachel"));
    }

    @Test
    @Parameters({"stop, pots", "hola, aloh"})
    public void return_reverse(String input, String output) {
        InterpreterResult result = interpreter.interpret(input);

        assertThat(result.messages().size(), is(1));
        assertThat(result.messages().get(0), is(output));
    }

    @Test
    @Parameters({"oto, oto", "ala, ala"})
    public void detect_palindrome(String input, String output) {
        InterpreterResult result = interpreter.interpret(input);

        assertThat(result.messages().size(), is(2));
        assertThat(result.messages().get(0), is(output));
        assertThat(result.messages().get(1), is(MESSAGE_WHEN_PALINDROME));
    }
}
