package es.rachelcarmena.unit;

import es.rachelcarmena.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceShould {

    private static final int ANY_AMOUNT = 200;

    public AccountService accountService;

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        Clock clock = new Clock();
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

    @Test
    public void not_use_statement_printer_when_no_transactions_and_print_statement() {
        accountService.printStatement();

        verifyZeroInteractions(statementPrinter);
    }

    @Test
    public void order_print_transactions_when_transactions_and_print_statement() {
        List<Transaction> transactionList = new ArrayList<>();
        given(transactionRepository.allTransactions()).willReturn(transactionList);

        accountService.deposit(ANY_AMOUNT);
        accountService.withdraw(ANY_AMOUNT);
        accountService.printStatement();

        verify(transactionRepository).allTransactions();
        verify(statementPrinter).printTransactions(transactionList);
    }
}