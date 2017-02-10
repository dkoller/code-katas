package es.rachelcarmena.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SZipFileTest {

	@Test
	public void createZipFile() {
		int size = 200;
		String name = "name.zip";
		SZipFile zipFile = new SZipFile(name, size);

		assertEquals(name, zipFile.getName());
		assertEquals(size, zipFile.getSize());
	}

	@Test
	public void getSize() {
		int size = 200;
		SZipFile zipFile = new SZipFile("name.zip", size);
		zipFile.addResource(new SStandardFile("f1.txt", 10));
		zipFile.addResource(new SStandardFile("f2.txt", 5));

		assertEquals(size, zipFile.getSize());
	}
}
