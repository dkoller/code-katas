package es.rachelcarmena.operation;

import es.rachelcarmena.element.SContainer;
import es.rachelcarmena.element.SDirectory;
import es.rachelcarmena.element.SResource;
import es.rachelcarmena.element.SStandardFile;
import es.rachelcarmena.element.SZipFile;

public class MP3Counter implements Visitor {

	private int counter;

	public MP3Counter() {
		counter = 0;
	}

	public int getCounter() {
		return counter;
	}

	public void visit(SStandardFile file) {
		if (file.isMP3())
			counter++;
	}

	public void visit(SContainer container) {
		for (SResource resource : container.getResources()) {
			if (resource instanceof SDirectory)
				visit((SDirectory) resource);
			else if (resource instanceof SStandardFile)
				visit((SStandardFile) resource);
			else if (resource instanceof SZipFile)
				visit((SZipFile) resource);
		}
	}
}
