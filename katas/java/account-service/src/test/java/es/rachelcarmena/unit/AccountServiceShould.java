package es.rachelcarmena.unit;

import es.rachelcarmena.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
        accountService = new AccountService(transactionRepository, statementPrinter);
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
        given(transactionRepository.allTransactions()).willReturn(new ArrayList<Transaction>(0));

        accountService.printStatement();

        verify(transactionRepository).allTransactions();
        verifyZeroInteractions(statementPrinter);
    }

    @Test
    public void order_print_transactions_when_transactions_and_print_statement() {
        List<Transaction> ANY_NOT_EMPTY_TRANSACTION_LIST = createNotEmptyTransactionList();
        given(transactionRepository.allTransactions()).willReturn(ANY_NOT_EMPTY_TRANSACTION_LIST);

        accountService.printStatement();

        verify(transactionRepository).allTransactions();
        verify(statementPrinter).printTransactions(ANY_NOT_EMPTY_TRANSACTION_LIST);
    }

    private List<Transaction> createNotEmptyTransactionList() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        return transactionList;
    }
}