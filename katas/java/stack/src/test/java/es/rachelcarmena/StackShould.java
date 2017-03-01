package es.rachelcarmena;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StackShould {

	private static final String OBJECT_1 = "first";
	private static final String OBJECT_2 = "second";
	private Stack stack;

	@Before
	public void initialise() {
		stack = new Stack();
	}

	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_if_popped_when_empty() {
		stack = new Stack();

		stack.pop();
	}

	@Test
	public void pop_the_last_object_pushed() throws Exception {
		stack.push(OBJECT_1);

		assertThat(stack.pop(), is(OBJECT_1));
	}

	@Test
	public void pop_objects_in_the_reverse_order_they_were_pushed() throws Exception {
		stack.push(OBJECT_1);
		stack.push(OBJECT_2);

		assertThat(stack.pop(), is(OBJECT_2));
		assertThat(stack.pop(), is(OBJECT_1));
	}

}
