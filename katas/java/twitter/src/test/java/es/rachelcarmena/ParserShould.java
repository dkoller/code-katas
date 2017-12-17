package es.rachelcarmena;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParserShould {

    @Test
    public void create_post_command_when_publishing_a_message() {
        String input = "Alice -> I love the weather today";
        Parser parser = new Parser();

        Command command = parser.parse(input);

        assertThat(command, is(new PostCommand("Alice", "I love the weather today")));
    }
}
