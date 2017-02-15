package es.rachelcarmena;

import java.time.LocalDate;

public class Withdrawal extends Operation {

	public Withdrawal(Quantity quantity, LocalDate date) {
		super(quantity, date);
	}

	@Override
	public Quantity getPreviousBalance(Quantity balance) {
		return balance.add(quantity);
	}

	@Override
	public void fillOperationData(String[] statementLineData) {
		statementLineData[1] = "";
		statementLineData[2] = quantity.toString();
	}
}
