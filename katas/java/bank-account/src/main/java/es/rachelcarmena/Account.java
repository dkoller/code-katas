package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Account {

	private static final String STATEMENT_HEADER = "date || credit || debit || balance";

	private List<Operation> operations = new ArrayList<>();
	private Quantity lastBalance = new Quantity("0.00");

	public void makeDeposit(Deposit deposit) {
		operations.add(deposit);

		Quantity depositQuantity = deposit.quantity;
		lastBalance = lastBalance.add(depositQuantity);
	}

	public void makeWithdrawal(Withdrawal withdrawal) {
		operations.add(withdrawal);

		Quantity withdrawalQuantity = withdrawal.quantity;
		lastBalance = lastBalance.subtract(withdrawalQuantity);
	}

	public String printStatement() {
		Quantity balance = lastBalance;
		int numberOfOperations = operations.size();
		StringBuilder result = new StringBuilder(STATEMENT_HEADER);

		for (ListIterator<Operation> iterator = operations.listIterator(numberOfOperations); iterator.hasPrevious();) {
			Operation operation = iterator.previous();
			String operationString = operation.toString(balance);

			result.append("%n");
			result.append(operationString);
			balance = operation.getPreviousBalance(balance);
		}
		return result.toString();
	}
}
