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

    public abstract Amount getAmountAccordingToTypeOfTransaction();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}