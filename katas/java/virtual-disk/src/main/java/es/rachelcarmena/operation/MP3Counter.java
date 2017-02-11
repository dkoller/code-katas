package es.rachelcarmena.operation;

import es.rachelcarmena.element.SContainer;
import es.rachelcarmena.element.SResource;
import es.rachelcarmena.element.SStandardFile;

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
			resource.accept(this);
		}
	}
}
