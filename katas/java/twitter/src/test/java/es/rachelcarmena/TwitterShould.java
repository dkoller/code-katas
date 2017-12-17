package es.rachelcarmena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterShould {

    private static final String POST_SEPARATOR = " -> ";
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final String CHARLIE = "Charlie";

    private static final String ALICE_POST_MESSAGE = "I love the weather today";
    private static final String BOB_POST_MESSAGE_1 = "Damn! We lost!";
    private static final String BOB_POST_MESSAGE_2 = "Good game though.";
    private static final String CHARLIE_POST_MESSAGE = "I'm in New York today! Anyone want to have a coffee?";

    private static final String ALICE_COMMAND_LINE = String.format("%s%s%s", ALICE, POST_SEPARATOR, ALICE_POST_MESSAGE);
    private static final String BOB_COMMAND_LINE_1 = String.format("%s%s%s", BOB, POST_SEPARATOR, BOB_POST_MESSAGE_1);
    private static final String BOB_COMMAND_LINE_2 = String.format("%s%s%s", BOB, POST_SEPARATOR, BOB_POST_MESSAGE_2);
    private static final String CHARLIE_COMMAND_LINE = String.format("%s%s%s", CHARLIE, POST_SEPARATOR, CHARLIE_POST_MESSAGE);

    private static final LocalDateTime ALICE_S_POST_DATETIME = LocalDateTime.of(2018, 2, 3, 10, 0, 0);
    private static final LocalDateTime BOB_S_POST_DATETIME_1 = LocalDateTime.of(2018, 2, 3, 10, 3, 0);
    private static final LocalDateTime BOB_S_POST_DATETIME_2 = LocalDateTime.of(2018, 2, 3, 10, 4, 0);
    private static final LocalDateTime CHARLIE_S_POST_DATETIME = LocalDateTime.of(2018, 2, 3, 10, 4, 58);

    private static final Post ALICE_S_POST = new Post(ALICE_POST_MESSAGE, ALICE_S_POST_DATETIME);
    private static final Post BOB_S_POST_1 = new Post(BOB_POST_MESSAGE_1, BOB_S_POST_DATETIME_1);
    private static final Post BOB_S_POST_2 = new Post(BOB_POST_MESSAGE_2, BOB_S_POST_DATETIME_2);
    private static final Post CHARLIE_S_POST = new Post(CHARLIE_POST_MESSAGE, CHARLIE_S_POST_DATETIME);

    @Mock
    Console console;
    @Mock
    Clock clock;
    @Mock
    Repository repository;
    @Mock
    Parser parser;

    @Test
    public void parse_and_save_post_when_publishing() {
        InOrder inOrder = Mockito.inOrder(repository);
        Twitter twitter = new Twitter(console, clock, repository, parser);

        when(clock.now()).thenReturn(ALICE_S_POST_DATETIME);
        when(console.read()).thenReturn(ALICE_COMMAND_LINE);
        when(parser.parse(ALICE_COMMAND_LINE)).thenReturn(new PostCommand(ALICE, ALICE_POST_MESSAGE));
        twitter.execute();
        inOrder.verify(repository).savePost(ALICE, ALICE_S_POST);

        when(clock.now()).thenReturn(BOB_S_POST_DATETIME_1);
        when(console.read()).thenReturn(BOB_COMMAND_LINE_1);
        when(parser.parse(BOB_COMMAND_LINE_1)).thenReturn(new PostCommand(BOB, BOB_POST_MESSAGE_1));
        twitter.execute();
        inOrder.verify(repository).savePost(BOB, BOB_S_POST_1);

        when(clock.now()).thenReturn(BOB_S_POST_DATETIME_2);
        when(console.read()).thenReturn(BOB_COMMAND_LINE_2);
        when(parser.parse(BOB_COMMAND_LINE_2)).thenReturn(new PostCommand(BOB, BOB_POST_MESSAGE_2));
        twitter.execute();
        inOrder.verify(repository).savePost(BOB, BOB_S_POST_2);

        when(clock.now()).thenReturn(CHARLIE_S_POST_DATETIME);
        when(console.read()).thenReturn(CHARLIE_COMMAND_LINE);
        when(parser.parse(CHARLIE_COMMAND_LINE)).thenReturn(new PostCommand(CHARLIE, CHARLIE_POST_MESSAGE));
        twitter.execute();
        inOrder.verify(repository).savePost(CHARLIE, CHARLIE_S_POST);
    }
}
