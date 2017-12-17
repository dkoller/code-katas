package es.rachelcarmena;

import es.rachelcarmena.infraestructure.Clock;
import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.infraestructure.Repository;
import es.rachelcarmena.utils.Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterFeature {

	@Mock
	Console console;

	@Mock
	Clock clock;

	@Test
	public void implement_a_console_based_social_networking_application() {
		InOrder inOrder = Mockito.inOrder(console);
		Repository repository = new Repository();
		Parser parser = new Parser();
		Twitter twitter = new Twitter(console, clock, repository, parser);

		when(clock.now()).thenReturn(LocalDateTime.of(2018, 2, 3, 10, 0, 0));
		when(console.read()).thenReturn("Alice -> I love the weather today");
		twitter.execute();

		when(clock.now()).thenReturn(LocalDateTime.of(2018, 2, 3, 10, 3, 0));
		when(console.read()).thenReturn("Bob -> Damn! We lost!");
		twitter.execute();

		when(clock.now()).thenReturn(LocalDateTime.of(2018, 2, 3, 10, 4, 0));
		when(console.read()).thenReturn("Bob -> Good game though.");
		twitter.execute();

		when(clock.now()).thenReturn(LocalDateTime.of(2018, 2, 3, 10, 4, 58));
		when(console.read()).thenReturn("Charlie -> I'm in New York today! Anyone want to have a coffee?");
		twitter.execute();

		when(clock.now()).thenReturn(LocalDateTime.of(2018, 2, 3, 10, 5, 0));

		when(console.read()).thenReturn("Alice");
		twitter.execute();
		inOrder.verify(console).print("I love the weather today (5 minutes ago)");

		when(console.read()).thenReturn("Bob");
		twitter.execute();
		inOrder.verify(console).print("Good game though. (1 minute ago)");
		inOrder.verify(console).print("Damn! We lost! (2 minutes ago)");

		when(console.read()).thenReturn("Charlie");
		twitter.execute();
		inOrder.verify(console).print("I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");

		when(console.read()).thenReturn("Charlie wall");
		twitter.execute();
		inOrder.verify(console).print("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");

		when(console.read()).thenReturn("Charlie follows Alice");
		twitter.execute();

		when(console.read()).thenReturn("Charlie wall");
		twitter.execute();
		inOrder.verify(console).print("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
		inOrder.verify(console).print("Alice - I love the weather today (5 minutes ago)");

		when(console.read()).thenReturn("Charlie follows Bob");
		twitter.execute();

		when(console.read()).thenReturn("Charlie wall");
		twitter.execute();
		inOrder.verify(console).print("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)");
		inOrder.verify(console).print("Bob - Good game though. (1 minute ago)");
		inOrder.verify(console).print("Bob - Damn! We lost! (2 minutes ago)");
		inOrder.verify(console).print("Alice - I love the weather today (5 minutes ago)");
	}
}
