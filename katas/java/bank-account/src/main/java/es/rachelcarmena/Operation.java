package es.rachelcarmena;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Operation {

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("DD/MM/YYYY");

	protected Quantity quantity;
	protected LocalDate date;

	protected Operation(Quantity quantity, LocalDate date) {
		this.quantity = quantity;
		this.date = date;
	}

	public abstract Quantity previousBalance(Quantity balance);

	public abstract Quantity nextBalance(Quantity balance);

	public abstract void fillOperationData(String[] statementLineData);

	public String createStatementLine(Quantity balance) {
		String[] statementLineData = new String[4];
		statementLineData[0] = date.format(DATE_TIME_FORMAT);
		fillOperationData(statementLineData);
		statementLineData[3] = balance.toString();

		String statementLine = String.join(" || ", statementLineData);
		return statementLine.replaceAll("\\s+", " ");
	}
}
