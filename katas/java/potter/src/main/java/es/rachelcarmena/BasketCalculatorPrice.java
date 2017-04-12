package es.rachelcarmena;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BasketCalculatorPrice {

    private static final Amount PRICE_PER_BOOK = Amount.valueOf(8);

    private static final int TWO_DIFFERENT_BOOKS_DISCOUNT = 5;
    private static final int THREE_DIFFERENT_BOOKS_DISCOUNT = 10;
    private static final int FOUR_DIFFERENT_BOOKS_DISCOUNT = 20;
    private static final int FIVE_DIFFERENT_BOOKS_DISCOUNT = 25;
    private static final int NUMBER_OF_TITLES = 5;

    private static final Map<Integer, Integer> discountPerBooks = new HashMap<Integer, Integer>() {{
        put(2, TWO_DIFFERENT_BOOKS_DISCOUNT);
        put(3, THREE_DIFFERENT_BOOKS_DISCOUNT);
        put(4, FOUR_DIFFERENT_BOOKS_DISCOUNT);
        put(5, FIVE_DIFFERENT_BOOKS_DISCOUNT);
    }};

    public Amount priceOf(int... books) {
        Amount total = Amount.valueOf(0);
        int[] numberOfBooksPerTitle = calculateNumberOfBooksPerTitle(books);

        while (existMoreThanOneTitleIn(numberOfBooksPerTitle)) {
            Amount groupOfDifferentBooksPrice = calculatePriceOfOneGroupAndUpdateList(numberOfBooksPerTitle);
            total = total.add(groupOfDifferentBooksPrice);
        }
        if (existIndividualBooksIn(numberOfBooksPerTitle)) {
            Amount individualBooksPrice = calculatePriceOfIndividualBooks(numberOfBooksPerTitle);
            total = total.add(individualBooksPrice);
        }
        return total;
    }

    private Amount calculatePriceOfIndividualBooks(int[] numberOfBooksPerTitle) {
        int individualBooks = getNumberOfIndividualBooks(numberOfBooksPerTitle);
        return PRICE_PER_BOOK.multiply(individualBooks);
    }

    private Amount calculatePriceOfOneGroupAndUpdateList(int[] numberOfBooksPerTitle) {
        int numberOfDifferentBooks = getNumberOfDifferentBooksAndUpdateList(numberOfBooksPerTitle);
        Amount groupOfDifferentBooksPrice = PRICE_PER_BOOK.multiply(numberOfDifferentBooks);
        int percentOf = discountPerBooks.get(numberOfDifferentBooks);
        return groupOfDifferentBooksPrice.applyDiscount(percentOf);
    }

    private int getNumberOfDifferentBooksAndUpdateList(int[] numberOfBooksPerTitle) {
        int numberOfDifferentBooks = 0;
        for (int i = 0; i < NUMBER_OF_TITLES; i++) {
            if (numberOfBooksPerTitle[i] > 0) {
                numberOfBooksPerTitle[i]--;
                numberOfDifferentBooks++;
            }
        }
        return numberOfDifferentBooks;
    }

    private int getNumberOfIndividualBooks(int[] numberOfBooks) {
        for (int number : numberOfBooks) {
            if (number > 0) return number;
        }
        return 0;
    }

    private boolean existIndividualBooksIn(int[] numberOfBooks) {
        for (int number : numberOfBooks) {
            if (number > 0) return true;
        }
        return false;
    }

    private boolean existMoreThanOneTitleIn(int[] numberOfBooks) {
        return IntStream.of(numberOfBooks).filter(n -> n > 0).count() > 1;
    }

    private int[] calculateNumberOfBooksPerTitle(int[] books) {
        int[] numberOfBooksPerTitle = new int[NUMBER_OF_TITLES];
        Arrays.fill(numberOfBooksPerTitle, 0);
        for (int book : books) {
            numberOfBooksPerTitle[book]++;
        }
        return numberOfBooksPerTitle;
    }
}
