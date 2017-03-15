package es.rachelcarmena;

import es.rachelcarmena.delivery.StatementPrinter;
import es.rachelcarmena.domain.Clock;
import es.rachelcarmena.domain.Deposit;
import es.rachelcarmena.domain.Transaction;
import es.rachelcarmena.domain.Withdraw;
import es.rachelcarmena.repository.TransactionRepository;

import java.util.List;

public class AccountService {
    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;
    private Clock clock;

    public AccountService(TransactionRepository transactionRepository, StatementPrinter statementPrinter, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
        this.clock = clock;
    }

    public void deposit(int amount) {
        Transaction deposit = new Deposit(amount, clock.now());
        transactionRepository.addTransaction(deposit);
    }

    public void withdraw(int amount) {
        Transaction withdraw = new Withdraw(amount, clock.now());
        transactionRepository.addTransaction(withdraw);
    }

    public void printStatement() {
        List<Transaction> transactionList = transactionRepository.allTransactions();
        if (transactionList.isEmpty())
            return;
        statementPrinter.printTransactions(transactionList);
    }
}