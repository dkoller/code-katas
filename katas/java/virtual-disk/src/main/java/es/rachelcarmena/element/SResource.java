package es.rachelcarmena.element;

import es.rachelcarmena.operation.Visitable;

public abstract class SResource implements Visitable {

	private String name;

	public SResource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract int getSize();

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof SResource)
			return name.equals(((SResource) obj).getName());
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
