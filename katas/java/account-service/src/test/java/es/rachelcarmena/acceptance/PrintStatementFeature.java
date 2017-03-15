package es.rachelcarmena.acceptance;

import es.rachelcarmena.AccountService;
import es.rachelcarmena.delivery.Console;
import es.rachelcarmena.delivery.StatementPrinter;
import es.rachelcarmena.domain.Clock;
import es.rachelcarmena.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock
    Console console;
    @Mock
    Clock clock;
    private AccountService accountService;

    @Before
    public void setUp() {
        StatementPrinter statementPrinter = new StatementPrinter(console);
        TransactionRepository transactionRepository = new TransactionRepository();
        accountService = new AccountService(transactionRepository, statementPrinter, clock);
    }

    @Test
    public void should_print_a_bank_statement_in_reversed_chronological_order() {
        given(clock.now()).willReturn(LocalDate.of(2014, 4, 1));
        accountService.deposit(1000);
        given(clock.now()).willReturn(LocalDate.of(2014, 4, 2));
        accountService.withdraw(100);
        given(clock.now()).willReturn(LocalDate.of(2014, 4, 10));
        accountService.deposit(500);

        accountService.printStatement();

        verify(console).print("DATE       | AMOUNT  | BALANCE");
        verify(console).print("10/04/2014 | 500.00  | 1400.00");
        verify(console).print("02/04/2014 | -100.00 | 900.00 ");
        verify(console).print("01/04/2014 | 1000.00 | 1000.00");
    }
}
