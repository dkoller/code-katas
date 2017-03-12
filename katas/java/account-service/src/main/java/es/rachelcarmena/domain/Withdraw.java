package es.rachelcarmena.domain;

import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.Transaction;

import java.time.LocalDate;

public class Withdraw extends Transaction {

    public Withdraw(int amount, LocalDate date) {
        super(amount, date);
    }

    @Override
    public Amount getAmountAsPerTransactionType() {
        Amount amount = super.getAmount();
        return amount.toNegative();
    }
}
