package es.rachelcarmena;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BasketCalculatorPrice {

    private static final double PRICE_PER_BOOK = 8.0;
    private static final int TWO_DIFFERENT_BOOKS_DISCOUNT = 5;
    private static final int THREE_DIFFERENT_BOOKS_DISCOUNT = 10;
    private static final int FOUR_DIFFERENT_BOOKS_DISCOUNT = 20;

    private static final Map<Integer, Integer> discountPerBooks = new HashMap<Integer, Integer>() {{
        put(2, TWO_DIFFERENT_BOOKS_DISCOUNT);
        put(3, THREE_DIFFERENT_BOOKS_DISCOUNT);
        put(4, FOUR_DIFFERENT_BOOKS_DISCOUNT);
    }};

    public double priceOf(int... books) {
        if (books.length == 1) return PRICE_PER_BOOK;
        if (areDifferentBooks(books))
            return calculateDiscount(books.length, discountPerBooks.get(books.length));
        return PRICE_PER_BOOK * books.length;
    }

    private boolean areDifferentBooks(int[] books) {
        return IntStream.of(books).distinct().count() == books.length;
    }

    private double calculateDiscount(int numberOfBooks, int discount) {
        return numberOfBooks * PRICE_PER_BOOK * (1.0 - discount / 100.0);
    }
}
