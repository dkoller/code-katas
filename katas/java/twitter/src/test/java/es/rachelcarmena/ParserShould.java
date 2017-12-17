package es.rachelcarmena;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParserShould {

    @Test
    public void create_post_command_when_publishing_a_message() {
        Parser parser = new Parser();

        Command command = parser.parse("Alice -> I love the weather today");

        assertThat(command, is(new PostCommand("Alice", "I love the weather today")));
    }

    @Test
    public void create_read_command_when_providing_a_user() {
        Parser parser = new Parser();

        Command command = parser.parse("Alice");

        assertThat(command, is(new ReadCommand("Alice")));
    }

    @Test
    public void create_follow_command_when_user_subscription() {
        Parser parser = new Parser();

        Command command = parser.parse("Charlie follows Alice");

        assertThat(command, is(new FollowCommand("Charlie", "Alice")));
    }
}
