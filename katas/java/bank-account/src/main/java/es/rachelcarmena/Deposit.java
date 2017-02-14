package es.rachelcarmena;

import java.time.LocalDate;

public class Deposit extends Operation {

	public Deposit(Quantity quantity, LocalDate date) {
		super(quantity, date);
	}

	@Override
	public String toString(Quantity balance) {
		String[] values = new String[4];
		values[0] = date.format(DATE_TIME_FORMAT);
		values[1] = quantity.toString();
		values[2] = "";
		values[3] = balance.toString();

		String result = String.join(" || ", values);
		return result.replaceAll("\\s+", " ");
	}

	@Override
	public Quantity getPreviousBalance(Quantity balance) {
		return balance.subtract(quantity);
	}
}
