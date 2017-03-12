package es.rachelcarmena;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Amount balance = new Amount(0);
        for (Transaction transaction : transactionList) {
            LocalDate date = transaction.getDate();
            Amount amount = transaction.getAmountAccordingToTypeOfTransaction();
            balance = balance.add(amount);
            String statementLine = String.format("%s | %-7s | %-7s", getFormattedDate(date), amount.toPrintedString(), balance.toPrintedString());
            statementLines.add(statementLine);
        }

        for (ListIterator<String> iterator = statementLines.listIterator(statementLines.size()); iterator.hasPrevious(); )
            console.print(iterator.previous());
    }

    private String getFormattedDate(LocalDate date) {
        final String DATE_FORMAT = "dd/MM/yyyy";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(dateTimeFormatter);
    }
}
