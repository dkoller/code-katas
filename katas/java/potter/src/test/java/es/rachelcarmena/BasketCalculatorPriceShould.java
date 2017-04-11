package es.rachelcarmena;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class BasketCalculatorPriceShould {

    @Test
    @Parameters({"0", "1", "2", "3", "4"})
    public void not_apply_discount_when_individuals_books(int bookCode) {
        assertThat(BasketCalculatorPrice.priceOf(bookCode), is(8.0));
    }

    @Test
    public void not_apply_discount_when_equal_books() {
        assertThat(BasketCalculatorPrice.priceOf(0, 0), is(16.0));
    }
}
