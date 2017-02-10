package es.rachelcarmena.operation;

import es.rachelcarmena.element.SContainer;
import es.rachelcarmena.element.SStandardFile;

public interface Visitor {

	public void visit(SStandardFile file);

	public void visit(SContainer container);
}
