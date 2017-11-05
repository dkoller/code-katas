package es.rachelcarmena;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GreetingShould {

	private Greeting greeting;

	@Before
	public void setUp() throws Exception {
		greeting = new Greeting();
	}

	@Test
	public void create_a_simple_greeting() {
		assertThat(greeting.greet("Bob"), is("Hello, Bob."));
	}

	@Test
	public void create_an_anonymous_greeting_when_no_name() {
		assertThat(greeting.greet(null), is("Hello, my friend."));
	}

	@Test
	public void shout_when_name_is_all_uppercase() {
		assertThat(greeting.greet("JERRY"), is("HELLO JERRY!"));
	}

	@Test
	public void create_a_multiple_greeting_when_two_names() {
		assertThat(greeting.greet("Jill", "Jane"), is("Hello, Jill and Jane."));
	}

	@Test
	public void create_a_multiple_greeting_when_more_than_two_names() {
		assertThat(greeting.greet("Amy", "Brian", "Charlotte"), is("Hello, Amy, Brian, and Charlotte."));
		assertThat(greeting.greet("Amy", "Brian", "Charlotte", "Rachel"), is("Hello, Amy, Brian, Charlotte, and Rachel."));
	}

	@Test
	public void create_separated_greetings_when_mixing_names() {
		assertThat(greeting.greet("Amy", "BRIAN", "Charlotte"), is("Hello, Amy and Charlotte. AND HELLO BRIAN!"));
	}
}
