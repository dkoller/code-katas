package es.rachelcarmena.operation;

@FunctionalInterface
public interface Visitable {

	void accept(Visitor visitor);
}
