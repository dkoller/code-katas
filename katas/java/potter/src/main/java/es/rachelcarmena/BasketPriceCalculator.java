package es.rachelcarmena;

public class BasketPriceCalculator {

    public Amount priceOf(int... books) {
        NumberOfBooksPerTitle numberOfBooksPerTitle = new NumberOfBooksPerTitle(books);
        PerGroupCalculator perGroupCalculator = new PerGroupCalculator(numberOfBooksPerTitle);
        return perGroupCalculator.calculatePrice();
    }
}