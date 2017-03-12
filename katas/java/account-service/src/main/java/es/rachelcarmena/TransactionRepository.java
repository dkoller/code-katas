package es.rachelcarmena;

import java.util.List;

public class TransactionRepository {
    private Clock clock;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addDeposit(int amount) {
    }

    public void addWithdraw(int amount) {
    }

    public List<Transaction> allTransactions() {
        return null;
    }
}
