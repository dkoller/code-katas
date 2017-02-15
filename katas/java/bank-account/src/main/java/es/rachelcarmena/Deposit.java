package es.rachelcarmena;

import java.time.LocalDate;

public class Deposit extends Operation {

	public Deposit(Quantity quantity, LocalDate date) {
		super(quantity, date);
	}

	@Override
	public Quantity getPreviousBalance(Quantity balance) {
		return balance.subtract(quantity);
	}

	@Override
	public void fillOperationData(String[] statementLineData) {
		statementLineData[1] = quantity.toString();
		statementLineData[2] = "";
	}
}
