package es.rachelcarmena.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SStandardFileTest {

	@Test
	public void createFile() {
		String name = "name.txt";
		int size = 100;
		SStandardFile file = new SStandardFile(name, size);
		assertEquals(name, file.getName());
		assertEquals(size, file.getSize());
	}

	@Test
	public void isMP3() {
		SStandardFile file = new SStandardFile("name.mp3", 200);
		assertTrue(file.isMP3());
	}

	@Test
	public void notIsMP3() {
		SStandardFile file = new SStandardFile("name.txt", 200);
		assertFalse(file.isMP3());
	}
}
