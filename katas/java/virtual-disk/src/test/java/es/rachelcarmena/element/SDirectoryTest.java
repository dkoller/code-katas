package es.rachelcarmena.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SDirectoryTest {

	private SDirectory directory;
	private String name = "myDirName";

	@Before
	public void before() {
		directory = new SDirectory(name);
	}

	@Test
	public void createDirectory() {
		assertEquals(name, directory.getName());
	}

	@Test
	public void addAndDeleteFile() {
		String fileName = "myFile";
		directory.addResource(new SStandardFile(fileName, 200));
		assertFalse(directory.deleteResource("notExistsFile"));
		assertTrue(directory.deleteResource(fileName));
	}

	@Test
	public void addAndDeleteDirectory() {
		String directoryName = "mySubDir";
		directory.addResource(new SDirectory(directoryName));
		assertFalse(directory.deleteResource("notExistsDirectory"));
		assertTrue(directory.deleteResource(directoryName));
	}

	@Test
	public void getEmpyDirectorySize() {
		assertEquals(0, directory.getSize());
	}

	@Test
	public void getDirectoryWithFilesSize() {
		directory.addResource(new SStandardFile("f1", 20));
		directory.addResource(new SStandardFile("f2", 30));
		directory.addResource(new SStandardFile("f3", 50));

		assertEquals(100, directory.getSize());
	}

	@Test
	public void getDirectoryWithDirectoryAndFilesSize() {
		directory.addResource(new SStandardFile("f1", 20));
		directory.addResource(new SStandardFile("f2", 30));
		directory.addResource(new SStandardFile("f3", 50));
		SDirectory newDirectory = new SDirectory("newDirectory");
		newDirectory.addResource(new SStandardFile("f11", 3));
		directory.addResource(newDirectory);

		assertEquals(103, directory.getSize());
	}

	@Test
	public void getMP3FilesInEmptyDirectory() throws Exception {
		assertEquals(0, directory.totalMP3());
	}

	@Test
	public void getMP3FilesInDirectoryWithFiles() throws Exception {
		directory.addResource(new SStandardFile("f1", 20));
		directory.addResource(new SStandardFile("f2.mp3", 30));
		directory.addResource(new SStandardFile("f3", 50));
		assertEquals(1, directory.totalMP3());
	}

	@Test
	public void getMP3FilesInDirectoryWithFilesAndDirectories() {
		directory.addResource(new SStandardFile("f1.mp3", 20));
		directory.addResource(new SStandardFile("f2", 30));
		directory.addResource(new SStandardFile("f3", 50));
		SDirectory newDirectory = new SDirectory("newDirectory");
		newDirectory.addResource(new SStandardFile("f11.mp3", 3));
		directory.addResource(newDirectory);

		assertEquals(2, directory.totalMP3());
	}

	@Test
	public void getMP3FilesInDirectoryWithFilesAndZipsAndDirectories() {
		directory.addResource(new SStandardFile("f1", 20));
		directory.addResource(new SStandardFile("f2.mp3", 20));

		SZipFile zipFile = new SZipFile("f2.zip", 30);
		zipFile.addResource(new SStandardFile("f21.mp3", 3));
		zipFile.addResource(new SStandardFile("f22.mp3", 4));
		directory.addResource(zipFile);

		SDirectory newDirectory = new SDirectory("newDirectory");
		newDirectory.addResource(new SStandardFile("f11.mp3", 3));
		directory.addResource(newDirectory);

		assertEquals(4, directory.totalMP3());
	}

	@Test
	public void insertingResourcesWithTheSameName() {
		SStandardFile file = new SStandardFile("file.mp3", 20);
		assertTrue(directory.addResource(file));
		assertFalse(directory.addResource(file));
	}
}
