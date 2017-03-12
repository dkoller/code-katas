package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StatementPrinter {

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void printTransactions(List<Transaction> transactionList) {
        final String STATEMENT_HEADER = "DATE       | AMOUNT  | BALANCE";

        console.print(STATEMENT_HEADER);
        List<String> statementLines = new ArrayList<>();
        int balance = 0;
        for (Transaction transaction : transactionList) {
            balance += transaction.getAmountAccordingToTypeOfTransaction();
            String statementLine = String.format("%s | %-7.2f | %-7.2f", transaction.getDate(), Double.valueOf(transaction.getAmountAccordingToTypeOfTransaction()), Double.valueOf(balance));
            statementLines.add(statementLine);
        }

        for (ListIterator<String> iterator = statementLines.listIterator(statementLines.size()); iterator.hasPrevious(); )
            console.print(iterator.previous());
    }
}
