package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Account {

	private static final String STATEMENT_HEADER = "date || credit || debit || balance";

	private List<Operation> operations = new ArrayList<>();
	private Quantity lastBalance = new Quantity("0.00");

	public void makeOperation(Operation operation) {
		operations.add(operation);
		lastBalance = operation.nextBalance(lastBalance);
	}

	public String createStatement() {
		Quantity balance = lastBalance;
		int numberOfOperations = operations.size();
		StringBuilder statement = new StringBuilder(STATEMENT_HEADER);

		for (ListIterator<Operation> iterator = operations.listIterator(numberOfOperations); iterator.hasPrevious();) {
			Operation operation = iterator.previous();
			String statementLine = operation.createStatementLine(balance);

			statement.append("%n");
			statement.append(statementLine);
			balance = operation.previousBalance(balance);
		}
		return statement.toString();
	}
}
