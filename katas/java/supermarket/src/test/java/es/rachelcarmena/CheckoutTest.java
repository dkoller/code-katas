package es.rachelcarmena;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckoutTest {
    @Test
    public void not_calculate_when_illegal_input() {
        assertThat(Checkout.checkout("123"), is(-1));
    }

    @Test
    public void calculate_the_price_when_no_products_in_the_basket() {
        assertThat(Checkout.checkout(""), is(0));
    }

    @Test
    public void calculate_the_price_when_only_one_product_in_the_basket() {
        assertThat(Checkout.checkout("A"), is(50));
    }

    @Test
    public void calculate_the_price_when_two_products_in_the_basket() {
        assertThat(Checkout.checkout("AB"), is(80));
        assertThat(Checkout.checkout("AD"), is(65));
    }

    @Test
    public void calculate_the_price_when_repeated_products_in_the_basket() {
        assertThat(Checkout.checkout("AABCCC"), is(190));
    }

    @Test
    public void calculate_the_price_when_repeated_products_and_offers_in_the_basket() {
        assertThat(Checkout.checkout("AAA"), is(130));
        assertThat(Checkout.checkout("AAAA"), is(180));
        assertThat(Checkout.checkout("AAAAA"), is(200));
        assertThat(Checkout.checkout("AAAAAAAAAA"), is(400));
        assertThat(Checkout.checkout("BB"), is(45));
        assertThat(Checkout.checkout("AABAB"), is(175));
    }

    @Test
    public void calculate_the_price_when_more_than_one_offer_for_the_same_product() {
        assertThat(Checkout.checkout("AAAAA"), is(200));
        assertThat(Checkout.checkout("AAAAAAAA"), is(330));
    }

    @Test
    public void calculate_the_price_when_offer_affects_to_another_product() {
        assertThat(Checkout.checkout("BEE"), is(80));
        assertThat(Checkout.checkout("BEEEE"), is(160));
        assertThat(Checkout.checkout("BBEEEE"), is(160));
        assertThat(Checkout.checkout("BBBEEEE"), is(190));
        assertThat(Checkout.checkout("FF"), is(20));
        assertThat(Checkout.checkout("FFF"), is(20));
        assertThat(Checkout.checkout("FFFF"), is(30));
        assertThat(Checkout.checkout("FFFFF"), is(40));
        assertThat(Checkout.checkout("FFFFFF"), is(40));
        assertThat(Checkout.checkout("FFFFFFF"), is(50));
        assertThat(Checkout.checkout("G"), is(20));
        assertThat(Checkout.checkout("H"), is(10));
        assertThat(Checkout.checkout("HHHHH"), is(45));
        assertThat(Checkout.checkout("HHHHHHHHHH"), is(80));
        assertThat(Checkout.checkout("I"), is(35));
        assertThat(Checkout.checkout("J"), is(60));
        assertThat(Checkout.checkout("K"), is(70));
        assertThat(Checkout.checkout("KK"), is(120));
        assertThat(Checkout.checkout("L"), is(90));
        assertThat(Checkout.checkout("M"), is(15));
        assertThat(Checkout.checkout("N"), is(40));
        assertThat(Checkout.checkout("NNNM"), is(120));
        assertThat(Checkout.checkout("NNNMM"), is(135));
        assertThat(Checkout.checkout("O"), is(10));
        assertThat(Checkout.checkout("P"), is(50));
        assertThat(Checkout.checkout("PPPPP"), is(200));
        assertThat(Checkout.checkout("PPPPPP"), is(250));
        assertThat(Checkout.checkout("Q"), is(30));
        assertThat(Checkout.checkout("QQQ"), is(80));
        assertThat(Checkout.checkout("QQQQ"), is(110));
        assertThat(Checkout.checkout("R"), is(50));
        assertThat(Checkout.checkout("RRRQ"), is(150));
        assertThat(Checkout.checkout("RRRQQ"), is(180));
        assertThat(Checkout.checkout("S"), is(20));
        assertThat(Checkout.checkout("T"), is(20));
        assertThat(Checkout.checkout("U"), is(40));
        assertThat(Checkout.checkout("UU"), is(80));
        assertThat(Checkout.checkout("UUU"), is(120));
        assertThat(Checkout.checkout("UUUU"), is(120));
        assertThat(Checkout.checkout("V"), is(50));
        assertThat(Checkout.checkout("VV"), is(90));
        assertThat(Checkout.checkout("VVV"), is(130));
        assertThat(Checkout.checkout("VVVV"), is(180));
        assertThat(Checkout.checkout("W"), is(20));
        assertThat(Checkout.checkout("X"), is(17));
        assertThat(Checkout.checkout("Y"), is(20));
        assertThat(Checkout.checkout("Z"), is(21));
    }

    @Test
    public void calculate_the_price_for_a_pack() {
        assertThat(Checkout.checkout("ZST"), is(45));
        assertThat(Checkout.checkout("ZTY"), is(45));
        assertThat(Checkout.checkout("SSS"), is(45));
        assertThat(Checkout.checkout("ZZZ"), is(45));
        assertThat(Checkout.checkout("SSSZ"), is(65));
        assertThat(Checkout.checkout("ZZZS"), is(65));
        assertThat(Checkout.checkout("TTTX"), is(62));
        assertThat(Checkout.checkout("ZZZZZZ"), is(90));
        assertThat(Checkout.checkout("ZTYZTY"), is(90));
        assertThat(Checkout.checkout("ZTYZTYZTY"), is(135));
        assertThat(Checkout.checkout("ZTYZTYXTY"), is(135));
        assertThat(Checkout.checkout("ZTYA"), is(95));
        assertThat(Checkout.checkout("ZTYBB"), is(90));
        assertThat(Checkout.checkout("ZXSYTS"), is(90));
        assertThat(Checkout.checkout("CXYZYZC"), is(122));
    }
}
