package es.rachelcarmena;

import java.time.LocalDate;

public abstract class Transaction {
    private final int amount;
    private final LocalDate date;

    public Transaction(int amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    protected int getAmount() {
        return amount;
    }

    protected LocalDate getDate() {
        return date;
    }

    public abstract int getAmountAccordingToTypeOfTransaction();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (amount != that.amount) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}