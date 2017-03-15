package es.rachelcarmena.repository;

import es.rachelcarmena.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactionList;

    public TransactionRepository() {
        this.transactionList = new ArrayList<>();
    }

    public List<Transaction> allTransactions() {
        return transactionList;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }
}
