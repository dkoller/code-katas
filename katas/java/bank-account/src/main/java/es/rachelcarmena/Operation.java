package es.rachelcarmena;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Operation {

	protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("DD/MM/YYYY");

	protected Quantity quantity;
	protected LocalDate date;

	protected Operation(Quantity quantity, LocalDate date) {
		this.quantity = quantity;
		this.date = date;
	}

	public abstract String toString(Quantity balance);

	public abstract Quantity getPreviousBalance(Quantity balance);
}
