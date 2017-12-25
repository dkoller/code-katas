package es.rachelcarmena.unit;

import es.rachelcarmena.Twitter;
import es.rachelcarmena.domain.commands.FollowCommand;
import es.rachelcarmena.domain.commands.PostCommand;
import es.rachelcarmena.domain.commands.ReadCommand;
import es.rachelcarmena.domain.commands.WallCommand;
import es.rachelcarmena.domain.Post;
import es.rachelcarmena.infraestructure.Clock;
import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.infraestructure.Repository;
import es.rachelcarmena.utils.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterShould {

    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final String CHARLIE = "Charlie";

    private static final String ALICE_POST_MESSAGE = "I love the weather today";
    private static final String BOB_POST_MESSAGE_1 = "Damn! We lost!";
    private static final String BOB_POST_MESSAGE_2 = "Good game though.";
    private static final String CHARLIE_POST_MESSAGE = "I'm in New York today! Anyone want to have a coffee?";

    private static final String ALICE_COMMAND_LINE = String.format("%s%s%s", ALICE, Parser.POST_SEPARATOR, ALICE_POST_MESSAGE);
    private static final String BOB_COMMAND_LINE_1 = String.format("%s%s%s", BOB, Parser.POST_SEPARATOR, BOB_POST_MESSAGE_1);
    private static final String BOB_COMMAND_LINE_2 = String.format("%s%s%s", BOB, Parser.POST_SEPARATOR, BOB_POST_MESSAGE_2);
    private static final String CHARLIE_COMMAND_LINE = String.format("%s%s%s", CHARLIE, Parser.POST_SEPARATOR, CHARLIE_POST_MESSAGE);

    private static final LocalDateTime ALICE_POST_DATETIME = LocalDateTime.of(2018, 2, 3, 10, 0, 0);
    private static final LocalDateTime BOB_POST_DATETIME_1 = LocalDateTime.of(2018, 2, 3, 10, 3, 0);
    private static final LocalDateTime BOB_POST_DATETIME_2 = LocalDateTime.of(2018, 2, 3, 10, 4, 0);
    private static final LocalDateTime CHARLIE_POST_DATETIME = LocalDateTime.of(2018, 2, 3, 10, 4, 58);

    private static final Post ALICE_POST = new Post(ALICE_POST_MESSAGE, ALICE_POST_DATETIME);
    private static final Post BOB_POST_1 = new Post(BOB_POST_MESSAGE_1, BOB_POST_DATETIME_1);
    private static final Post BOB_POST_2 = new Post(BOB_POST_MESSAGE_2, BOB_POST_DATETIME_2);
    private static final Post CHARLIE_POST = new Post(CHARLIE_POST_MESSAGE, CHARLIE_POST_DATETIME);
    public static final LocalDateTime NOW = LocalDateTime.of(2018, 2, 3, 10, 5, 0);

    @Mock
    Console console;
    @Mock
    Clock clock;
    @Mock
    Repository repository;
    @Mock
    Parser parser;
    private InOrder inOrder;
    private Twitter twitter;

    @Before
    public void setUp() throws Exception {
        inOrder = Mockito.inOrder(repository, console);
        twitter = new Twitter(console, clock, repository, parser);
    }

    @Test
    public void save_post_when_publishing() {
        whenPostCommand(ALICE_POST_DATETIME, ALICE_COMMAND_LINE, ALICE, ALICE_POST_MESSAGE);
        twitter.execute();
        inOrder.verify(repository).savePost(ALICE, ALICE_POST);

        whenPostCommand(BOB_POST_DATETIME_1, BOB_COMMAND_LINE_1, BOB, BOB_POST_MESSAGE_1);
        twitter.execute();
        inOrder.verify(repository).savePost(BOB, BOB_POST_1);

        whenPostCommand(BOB_POST_DATETIME_2, BOB_COMMAND_LINE_2, BOB, BOB_POST_MESSAGE_2);
        twitter.execute();
        inOrder.verify(repository).savePost(BOB, BOB_POST_2);

        whenPostCommand(CHARLIE_POST_DATETIME, CHARLIE_COMMAND_LINE, CHARLIE, CHARLIE_POST_MESSAGE);
        twitter.execute();
        inOrder.verify(repository).savePost(CHARLIE, CHARLIE_POST);
    }

    @Test
    public void show_timeline_when_receiving_a_name() {
        whenReadCommand(Arrays.asList(ALICE_POST), ALICE);
        twitter.execute();
        inOrder.verify(repository).getPostsFrom(ALICE);
        inOrder.verify(console).print("I love the weather today (5 minutes ago)");

        whenReadCommand(Arrays.asList(BOB_POST_1, BOB_POST_2), BOB);
        twitter.execute();
        inOrder.verify(repository).getPostsFrom(BOB);
        inOrder.verify(console).print("Good game though. (1 minute ago)");
        inOrder.verify(console).print("Damn! We lost! (2 minutes ago)");

        whenReadCommand(Arrays.asList(CHARLIE_POST), CHARLIE);
        twitter.execute();
        inOrder.verify(repository).getPostsFrom(CHARLIE);
        inOrder.verify(console).print("I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
    }

    @Test
    public void save_following_when_subscribing() {
        when(console.read()).thenReturn("Charlie follows Alice");
        when(parser.parse("Charlie follows Alice")).thenReturn(new FollowCommand("Charlie", "Alice"));

        twitter.execute();

        verify(repository).saveFollowing("Charlie", "Alice");
    }

    @Test
    public void show_aggregated_list_of_all_subscriptions_when_wall_command() {
        when(clock.now()).thenReturn(NOW);
        when(console.read()).thenReturn("Charlie wall");
        when(parser.parse("Charlie wall")).thenReturn(new WallCommand(CHARLIE));
        when(repository.getFollowedBy(CHARLIE)).thenReturn(Arrays.asList(ALICE));
        when(repository.getPostsFrom(CHARLIE)).thenReturn(Arrays.asList(CHARLIE_POST));
        when(repository.getPostsFrom(ALICE)).thenReturn(Arrays.asList(ALICE_POST));

        twitter.execute();

        inOrder.verify(repository).getPostsFrom("Charlie");
        inOrder.verify(repository).getPostsFrom("Alice");
        inOrder.verify(console).print("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
        inOrder.verify(console).print("Alice - I love the weather today (5 minutes ago)");
    }

    private void whenReadCommand(List<Post> posts, String user) {
        when(clock.now()).thenReturn(NOW);
        when(console.read()).thenReturn(user);
        when(parser.parse(user)).thenReturn(new ReadCommand(user));
        when(repository.getPostsFrom(user)).thenReturn(posts);
    }

    private void whenPostCommand(LocalDateTime datetime, String commandLine, String user, String message) {
        when(clock.now()).thenReturn(datetime);
        when(console.read()).thenReturn(commandLine);
        when(parser.parse(commandLine)).thenReturn(new PostCommand(user, message));
    }
}
