package es.rachelcarmena.element;

import es.rachelcarmena.operation.Visitor;

public class SZipFile extends SContainer {

	private int size;

	public SZipFile(String name, int size) {
		super(name);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
