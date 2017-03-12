package es.rachelcarmena.unit;

import es.rachelcarmena.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private static final int ANY_AMOUNT = 300;
    private static final String ANY_DATE = "01/01/2017";

    @Mock Clock clock;

    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        given(clock.now()).willReturn(ANY_DATE);
        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    public void create_and_store_a_deposit_when_add_a_deposit() {
        transactionRepository.addDeposit(ANY_AMOUNT);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Deposit(ANY_AMOUNT, ANY_DATE));
        assertThat(transactionRepository.allTransactions(), is(transactionList));
    }

    @Test
    public void create_and_store_a_withdraw_when_add_a_withdraw() {
        transactionRepository.addWithdraw(ANY_AMOUNT);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Withdraw(ANY_AMOUNT, ANY_DATE));
        assertThat(transactionRepository.allTransactions(), is(transactionList));
    }
}