package es.rachelcarmena;

import java.time.LocalDate;

public class Deposit extends Transaction {

    public Deposit(int amount, LocalDate date) {
        super(amount, date);
    }

    @Override
    public int getAmountAccordingToTypeOfTransaction() {
        return getAmount();
    }
}
