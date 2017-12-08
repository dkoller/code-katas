package es.rachelcarmena.offers;

import es.rachelcarmena.Basket;

public class ProductOffer extends Offer {
    private final char involvedItem;
    private final int numberOfItems;
    private final char itemToDiscount;

    public ProductOffer(char involvedItem, int numberOfItems, char itemToDiscount) {
        this.involvedItem = involvedItem;
        this.numberOfItems = (involvedItem == itemToDiscount)? numberOfItems + 1: numberOfItems;
        this.itemToDiscount = itemToDiscount;
    }

    @Override
    public int numberOfOcurrencesFrom(Basket basket) {
        int numberOfInvolvedItemsInBasket = basket.getNumberOf(involvedItem);
        if (numberOfInvolvedItemsInBasket == 0)
            return 0;

        int numberOfItemsToDiscount = basket.getNumberOf(itemToDiscount);
        if (numberOfItemsToDiscount == 0)
            return 0;

        int numberOfPossibleOffers = numberOfInvolvedItemsInBasket / numberOfItems;
        return Math.min(numberOfPossibleOffers, numberOfItemsToDiscount);
    }

    @Override
    public int applyOfferAndGetPrice(Basket items, int numberOfOccurrences) {
        items.remove(numberOfOccurrences, itemToDiscount);
        return 0;
    }
}
