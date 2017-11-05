package es.rachelcarmena.domain;

import java.time.LocalDate;

public abstract class Transaction {
    private final Amount amount;
    private final LocalDate date;

    public Transaction(int amount, LocalDate date) {
        this.amount = new Amount(amount);
        this.date = date;
    }

    protected Amount getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public abstract Amount getAmountAsPerTransactionType();
}