package es.rachelcarmena;

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
    }

    public void printStatement() {
    }
}
