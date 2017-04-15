package es.rachelcarmena;

import java.util.HashMap;
import java.util.Map;

public class PerGroupCalculator {

    private static final Amount PRICE_PER_BOOK = Amount.valueOf(8);

    private static final int TWO_DIFFERENT_BOOKS_DISCOUNT = 5;
    private static final int THREE_DIFFERENT_BOOKS_DISCOUNT = 10;
    private static final int FOUR_DIFFERENT_BOOKS_DISCOUNT = 20;
    private static final int FIVE_DIFFERENT_BOOKS_DISCOUNT = 25;

    private static final Map<Integer, Integer> discountPerBooks = new HashMap<Integer, Integer>() {{
        put(2, TWO_DIFFERENT_BOOKS_DISCOUNT);
        put(3, THREE_DIFFERENT_BOOKS_DISCOUNT);
        put(4, FOUR_DIFFERENT_BOOKS_DISCOUNT);
        put(5, FIVE_DIFFERENT_BOOKS_DISCOUNT);
    }};

    private NumberOfBooksPerTitle numberOfBooksPerTitle;

    public PerGroupCalculator(NumberOfBooksPerTitle numberOfBooksPerTitle) {
        this.numberOfBooksPerTitle = numberOfBooksPerTitle;
    }

    public Amount calculatePrice() {
        Amount total = Amount.valueOf(0);
        while (numberOfBooksPerTitle.existMoreThanOneTitle()) {
            int numberOfDifferentBooks = numberOfBooksPerTitle.getNumberOfDifferentBooksAndUpdateList();
            Amount groupOfDifferentBooksPrice = priceOfWithDiscount(numberOfDifferentBooks);
            total = total.add(groupOfDifferentBooksPrice);
        }
        if (numberOfBooksPerTitle.existIndividualBooks()) {
            int individualBooks = numberOfBooksPerTitle.getNumberOfIndividualBooks();
            Amount individualBooksPrice = priceOf(individualBooks);
            total = total.add(individualBooksPrice);
        }
        return total;
    }

    private Amount priceOf(int numberOfBooks) {
        return PRICE_PER_BOOK.multiply(numberOfBooks);
    }

    private Amount priceOfWithDiscount(int numberOfDifferentBooks) {
        Amount groupOfDifferentBooksPrice = priceOf(numberOfDifferentBooks);
        int percentOf = discountPerBooks.get(numberOfDifferentBooks);
        return groupOfDifferentBooksPrice.applyDiscount(percentOf);
    }
}