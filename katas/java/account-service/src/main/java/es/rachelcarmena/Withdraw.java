package es.rachelcarmena;

public class Withdraw extends Transaction {

    public Withdraw(int amount, String date) {
        super(amount, date);
    }

    @Override
    public int getAmountAccordingToTypeOfTransaction() {
        return - super.getAmount();
    }
}
