package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private Clock clock;
    private List<Transaction> transactionList;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
        this.transactionList = new ArrayList<>();
    }

    public void addDeposit(int amount) {
        Transaction deposit = new Deposit(amount, clock.now());
        transactionList.add(deposit);
    }

    public void addWithdraw(int amount) {
    }

    public List<Transaction> allTransactions() {
        return transactionList;
    }
}
