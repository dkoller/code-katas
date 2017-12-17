package es.rachelcarmena;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParserShould {

    @Test
    public void create_post_command_when_post_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Alice -> I love the weather today");

        assertThat(command, is(new PostCommand("Alice", "I love the weather today")));
    }

    @Test
    public void create_read_command_when_username_as_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Alice");

        assertThat(command, is(new ReadCommand("Alice")));
    }

    @Test
    public void create_follow_command_when_follows_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Charlie follows Alice");

        assertThat(command, is(new FollowCommand("Charlie", "Alice")));
    }

    @Test
    public void create_wall_command_when_wall_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Charlie wall");

        assertThat(command, is(new WallCommand("Charlie")));
    }
}
