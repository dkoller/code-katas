package es.rachelcarmena.offers;

import es.rachelcarmena.Basket;

public class AmountOffer extends Offer {
    private final char involvedItem;
    private final int numberOfItems;
    private final int price;

    public AmountOffer(char involvedItem, int numberOfItems, int price) {
        this.involvedItem = involvedItem;
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

    public int numberOfOcurrencesFrom(Basket basket) {
        int numberOfInvolvedItemsInBasket = basket.getNumberOf(involvedItem);
        return numberOfInvolvedItemsInBasket / numberOfItems;
    }

    public int applyOfferAndGetPrice(Basket basket, int numberOfOccurrences) {
        basket.remove(numberOfItems * numberOfOccurrences, involvedItem);
        return numberOfOccurrences * price;
    }
}
