package es.rachelcarmena;

import java.time.LocalDate;

public class Withdrawal extends Operation {

	public Withdrawal(Quantity quantity, LocalDate date) {
		super(quantity, date);
	}

	@Override
	public String toString(Quantity balance) {
		String[] values = new String[4];
		values[0] = date.format(DATE_TIME_FORMAT);
		values[1] = "";
		values[2] = quantity.toString();
		values[3] = balance.toString();

		String result = String.join(" || ", values);
		return result.replaceAll("\\s+", " ");
	}

	@Override
	public Quantity getPreviousBalance(Quantity balance) {
		return balance.add(quantity);
	}
}
