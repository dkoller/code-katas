package es.rachelcarmena;

public class BasketCalculatorPrice {

    public static final double PRICE_PER_BOOK = 8.0;

    public static double priceOf(int... books) {
        return PRICE_PER_BOOK * books.length;
    }
}
