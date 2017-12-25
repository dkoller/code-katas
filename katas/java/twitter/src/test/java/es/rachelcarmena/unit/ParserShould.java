package es.rachelcarmena.unit;

import es.rachelcarmena.domain.command.Command;
import es.rachelcarmena.domain.command.FollowCommand;
import es.rachelcarmena.domain.command.PostCommand;
import es.rachelcarmena.domain.command.ReadCommand;
import es.rachelcarmena.domain.command.WallCommand;
import es.rachelcarmena.utils.Parser;
import org.junit.Test;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class ParserShould {

    @Test
    public void create_post_command_when_post_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Alice -> I love the weather today");

        assertTrue(reflectionEquals(command, new PostCommand("Alice", "I love the weather today")));
    }

    @Test
    public void create_read_command_when_username_as_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Alice");

        assertTrue(reflectionEquals(command, new ReadCommand("Alice")));
    }

    @Test
    public void create_follow_command_when_follows_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Charlie follows Alice");

        assertTrue(reflectionEquals(command, new FollowCommand("Charlie", "Alice")));
    }

    @Test
    public void create_wall_command_when_wall_input() {
        Parser parser = new Parser();

        Command command = parser.parse("Charlie wall");

        assertTrue(reflectionEquals(command, new WallCommand("Charlie")));
    }
}
