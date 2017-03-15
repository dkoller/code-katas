package es.rachelcarmena.unit;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private static final int ANY_MOUNT = 200;
    private static final LocalDate ANY_DATE = LocalDate.of(2017, 3, 1);
    private static final Transaction ANY_DEPOSIT = new Deposit(ANY_MOUNT, ANY_DATE);
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    public void store_a_deposit_when_add_a_deposit() {
        transactionRepository.addTransaction(ANY_DEPOSIT);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(ANY_DEPOSIT);
        assertThat(transactionRepository.allTransactions(), is(transactionList));
    }

    @Test
    public void store_a_withdraw_when_add_a_withdraw() {
        transactionRepository.addTransaction(ANY_DEPOSIT);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(ANY_DEPOSIT);
        assertThat(transactionRepository.allTransactions(), is(transactionList));
    }
}