package es.rachelcarmena.unit;

import es.rachelcarmena.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceShould {

    private static final int ANY_AMOUNT = 200;

    public AccountService accountService;

    @Mock
    TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        Clock clock = new Clock();
        Console console = new Console();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        accountService = new AccountService(transactionRepository, clock, statementPrinter);
    }

    @Test
    public void add_a_deposit_in_transaction_repository_when_deposit() {
        accountService.deposit(ANY_AMOUNT);

        verify(transactionRepository).addDeposit(ANY_AMOUNT);
    }

    @Test
    public void add_a_withdraw_in_transaction_repository_when_withdraw() {
        accountService.withdraw(ANY_AMOUNT);

        verify(transactionRepository).addWithdraw(ANY_AMOUNT);
    }
}
