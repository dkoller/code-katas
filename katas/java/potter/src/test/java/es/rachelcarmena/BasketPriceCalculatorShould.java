package es.rachelcarmena;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class BasketPriceCalculatorShould {
    private BasketPriceCalculator basketPriceCalculator;

    @Before
    public void setUp() throws Exception {
        basketPriceCalculator = new BasketPriceCalculator();
    }

    @Test
    @Parameters({"0", "1", "2", "3", "4"})
    public void not_apply_discount_when_individuals_books(int book) {
        assertThat(basketPriceCalculator.priceOf(book), is(Amount.valueOf(8.0)));
    }

    @Test
    public void not_apply_discount_when_equal_books() {
        assertThat(basketPriceCalculator.priceOf(0, 0), is(Amount.valueOf(16.0)));
    }

    @Test
    public void apply_specific_discount_when_two_different_books() {
        assertThat(basketPriceCalculator.priceOf(0, 1), is(Amount.valueOf(15.2)));
    }

    @Test
    public void apply_specific_discount_when_three_different_books() {
        assertThat(basketPriceCalculator.priceOf(0, 1, 2), is(Amount.valueOf(21.6)));
    }

    @Test
    public void apply_specific_discount_when_four_different_books() {
        assertThat(basketPriceCalculator.priceOf(0, 1, 2, 3), is(Amount.valueOf(25.6)));
    }

    @Test
    public void apply_specific_discount_when_five_different_books() {
        assertThat(basketPriceCalculator.priceOf(0, 1, 2, 3, 4), is(Amount.valueOf(30.0)));
    }

    @Test
    public void apply_several_discounts_when_groups_of_different_books() {
        assertThat(basketPriceCalculator.priceOf(0, 0, 1), is(Amount.valueOf(23.2)));
        assertThat(basketPriceCalculator.priceOf(0, 0, 1, 1), is(Amount.valueOf(30.4)));
        assertThat(basketPriceCalculator.priceOf(0, 0, 1, 1, 2, 2), is(Amount.valueOf(43.2)));
        assertThat(basketPriceCalculator.priceOf(0, 0, 1, 2, 2, 3), is(Amount.valueOf(40.8)));
        assertThat(basketPriceCalculator.priceOf(0, 1, 1, 2, 3, 4), is(Amount.valueOf(38.0)));
        assertThat(basketPriceCalculator.priceOf(0, 0, 1, 1, 2, 2, 3, 3), is(Amount.valueOf(51.2)));
        assertThat(basketPriceCalculator.priceOf(0, 0, 1, 1, 2, 2, 3, 3, 4, 4), is(Amount.valueOf(60.0)));
    }

    @Test
    public void apply_several_discounts_when_edge_cases_of_different_books() {
        assertThat(basketPriceCalculator.priceOf(0, 0, 1, 1, 2, 2, 3, 4), is(Amount.valueOf(51.2)));
        assertThat(basketPriceCalculator.priceOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4), is(Amount.valueOf(141.2)));
    }
}