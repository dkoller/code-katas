package es.rachelcarmena.element;

import es.rachelcarmena.operation.Visitor;

public class SZipFile extends SContainer {

	private int size;

	public SZipFile(String name, int size) {
		super(name);
		this.size = size;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
