package es.rachelcarmena.offers;

import es.rachelcarmena.Basket;

public class PackOffer extends Offer {
    private final int numberOfItemsPerPack;
    private final int pricePerPack;
    private final char[] packTtems;

    public PackOffer(int numberOfItemsPerPack, int pricePerPack, char... packItems) {
        this.numberOfItemsPerPack = numberOfItemsPerPack;
        this.pricePerPack = pricePerPack;
        this.packTtems = packItems;
    }

    @Override
    public int numberOfOcurrencesFrom(Basket basket) {
        int numberOfInvolvedItemsInBasket = basket.getNumberOfItemsIn(packTtems);
        return numberOfInvolvedItemsInBasket / numberOfItemsPerPack;
    }

    @Override
    public int applyOfferAndGetPrice(Basket items, int numberOfOccurrences) {
        items.remove(numberOfOccurrences * numberOfItemsPerPack, packTtems);
        return numberOfOccurrences * pricePerPack;
    }
}
