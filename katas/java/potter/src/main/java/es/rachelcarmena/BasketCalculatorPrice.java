package es.rachelcarmena;

public class BasketCalculatorPrice {

    public static final double PRICE_PER_BOOK = 8.0;
    public static final int TWO_DIFFERENT_BOOKS_DISCOUNT = 5;

    public static double priceOf(int... books) {
        if (books.length == 2 && books[0] != books[1])
            return calculateDiscount(books.length, TWO_DIFFERENT_BOOKS_DISCOUNT);
        return PRICE_PER_BOOK * books.length;
    }

    private static double calculateDiscount(int numberOfBooks, int discount) {
        return numberOfBooks * PRICE_PER_BOOK * (1.0 - discount / 100.0);
    }
}
