package es.rachelcarmena.repository;

import es.rachelcarmena.domain.Clock;
import es.rachelcarmena.domain.Deposit;
import es.rachelcarmena.domain.Transaction;
import es.rachelcarmena.domain.Withdraw;

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
        Transaction withdraw = new Withdraw(amount, clock.now());
        transactionList.add(withdraw);
    }

    public List<Transaction> allTransactions() {
        return transactionList;
    }
}
