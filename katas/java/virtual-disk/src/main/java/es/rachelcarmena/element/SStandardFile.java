package es.rachelcarmena.element;

import es.rachelcarmena.operation.Visitor;

public class SStandardFile extends SResource {

	private int size;

	public SStandardFile(String name, int size) {
		super(name);
		this.size = size;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public boolean isMP3() {
		return getName().endsWith("mp3");
	}

	public int getSize() {
		return size;
	}
}
