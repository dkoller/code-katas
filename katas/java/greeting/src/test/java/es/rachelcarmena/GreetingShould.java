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
}
