package es.rachelcarmena.element;

import java.util.stream.Collectors;

import es.rachelcarmena.operation.MP3Counter;
import es.rachelcarmena.operation.Visitor;

public class SDirectory extends SContainer {

	public SDirectory(String name) {
		super(name);
	}

	public int totalMP3() {
		MP3Counter counter = new MP3Counter();
		for (SResource resource : getResources()) {
			resource.accept(counter);
		}
		return counter.getCounter();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public int getSize() {
		return getResources().stream().collect(Collectors.summingInt(SResource::getSize));
	}
}
