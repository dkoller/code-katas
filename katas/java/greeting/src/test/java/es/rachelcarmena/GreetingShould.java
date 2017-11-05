package es.rachelcarmena;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GreetingShould {

	@Test
	public void create_a_simple_greeting() {
		Greeting greeting = new Greeting();
		assertThat(greeting.greet("Bob"), is("Hello, Bob."));
	}

}
