package es.rachelcarmena.domain;

import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.Transaction;

import java.time.LocalDate;

public class Deposit extends Transaction {

    public Deposit(int amount, LocalDate date) {
        super(amount, date);
    }

    @Override
    public Amount getAmountAsPerTransactionType() {
        return getAmount();
    }
}
