package es.rachelcarmena;

public class Deposit extends Transaction {

    public Deposit(int amount, String date) {
        super(amount, date);
    }

    @Override
    public int getAmountAccordingToTypeOfTransaction() {
        return getAmount();
    }
}
