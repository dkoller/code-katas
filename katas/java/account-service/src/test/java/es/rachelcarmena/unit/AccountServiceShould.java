package es.rachelcarmena.unit;

import es.rachelcarmena.AccountService;
import es.rachelcarmena.delivery.StatementPrinter;
import es.rachelcarmena.domain.Clock;
import es.rachelcarmena.domain.Deposit;
import es.rachelcarmena.domain.Transaction;
import es.rachelcarmena.domain.Withdraw;
import es.rachelcarmena.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceShould {

    private static final int ANY_AMOUNT = 200;
    private static final LocalDate ANY_DATE = LocalDate.of(2017, 1, 1);
    private static final Transaction ANY_DEPOSIT = new Deposit(ANY_AMOUNT, ANY_DATE);
    private static final Transaction ANY_WITHDRAW = new Withdraw(ANY_AMOUNT, ANY_DATE);

    public AccountService accountService;

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;
    @Mock
    Clock clock;

    @Before
    public void setUp() {
        accountService = new AccountService(transactionRepository, statementPrinter, clock);
    }

    @Test
    public void add_a_deposit_in_transaction_repository_when_deposit() {
        given(clock.now()).willReturn(ANY_DATE);
        accountService.deposit(ANY_AMOUNT);

        verify(transactionRepository).addTransaction(refEq(ANY_DEPOSIT));
    }

    @Test
    public void add_a_withdraw_in_transaction_repository_when_withdraw() {
        given(clock.now()).willReturn(ANY_DATE);
        accountService.withdraw(ANY_AMOUNT);

        verify(transactionRepository).addTransaction(refEq(ANY_WITHDRAW));
    }

    @Test
    public void not_use_statement_printer_when_no_transactions_and_print_statement() {
        given(transactionRepository.allTransactions()).willReturn(new ArrayList<>(0));

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
        transactionList.add(ANY_DEPOSIT);
        return transactionList;
    }
}