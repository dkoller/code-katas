package es.rachelcarmena;

import java.util.List;

public class AccountService {
    private final TransactionRepository transactionRepository;
    private final Clock clock;
    private final StatementPrinter statementPrinter;

    public AccountService(TransactionRepository transactionRepository, Clock clock, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        transactionRepository.addDeposit(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.addWithdraw(amount);
    }

    public void printStatement() {
        List<Transaction> transactionList = transactionRepository.allTransactions();
        if (transactionList.isEmpty())
            return;
        statementPrinter.printTransactions(transactionList);
    }
}
