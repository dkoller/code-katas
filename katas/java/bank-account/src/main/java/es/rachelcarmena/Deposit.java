package es.rachelcarmena;

import java.time.LocalDate;

public class Deposit extends Operation {

	public Deposit(Quantity quantity, LocalDate date) {
		super(quantity, date);
	}

	@Override
	public Quantity previousBalance(Quantity balance) {
		return balance.subtract(quantity);
	}

	@Override
	public Quantity nextBalance(Quantity balance) {
		return balance.add(quantity);
	}

	@Override
	public void fillOperationData(String[] statementLineData) {
		statementLineData[1] = quantity.toString();
		statementLineData[2] = "";
	}
}
