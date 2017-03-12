package es.rachelcarmena.unit;

import es.rachelcarmena.delivery.Console;
import es.rachelcarmena.delivery.StatementPrinter;
import es.rachelcarmena.domain.Deposit;
import es.rachelcarmena.domain.Transaction;
import es.rachelcarmena.domain.Withdraw;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    @Mock
    Console console;

    @Test
    public void print_transactions_in_reversed_chronological_order() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Deposit(1000, LocalDate.of(2014, 4, 1)));
        transactionList.add(new Withdraw(100, LocalDate.of(2014, 4, 2)));
        transactionList.add(new Deposit(500, LocalDate.of(2014, 4, 10)));
        StatementPrinter statementPrinter = new StatementPrinter(console);

        statementPrinter.printTransactions(transactionList);

        verify(console).print("DATE       | AMOUNT  | BALANCE");
        verify(console).print("10/04/2014 | 500.00  | 1400.00");
        verify(console).print("02/04/2014 | -100.00 | 900.00 ");
        verify(console).print("01/04/2014 | 1000.00 | 1000.00");
    }
}
