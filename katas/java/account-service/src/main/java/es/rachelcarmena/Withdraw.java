package es.rachelcarmena;

import java.time.LocalDate;

public class Withdraw extends Transaction {

    public Withdraw(int amount, LocalDate date) {
        super(amount, date);
    }

    @Override
    public int getAmountAccordingToTypeOfTransaction() {
        return - super.getAmount();
    }
}
