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
        assertThat(BasketCalculatorPrice.priceOf(1, 1, 1), is(24.0));
    }

    @Test
    public void apply_specific_discount_when_two_different_books() {
        assertThat(BasketCalculatorPrice.priceOf(0, 1), is(15.2));
    }
}
