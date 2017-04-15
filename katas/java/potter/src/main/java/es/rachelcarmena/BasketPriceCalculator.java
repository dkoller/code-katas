package es.rachelcarmena;

public class BasketPriceCalculator {

    public Amount priceOf(int... books) {
        NumberOfBooksPerTitle numberOfBooksPerTitle = new NumberOfBooksPerTitle(books, true);
        PerGroupCalculator perGroupCalculator = new PerGroupCalculator(numberOfBooksPerTitle);
        Amount priceStrategyA = perGroupCalculator.calculatePrice();

        numberOfBooksPerTitle = new NumberOfBooksPerTitle(books, false);
        perGroupCalculator = new PerGroupCalculator(numberOfBooksPerTitle);
        Amount priceStrategyB = perGroupCalculator.calculatePrice();

        return priceStrategyA.min(priceStrategyB);
    }
}