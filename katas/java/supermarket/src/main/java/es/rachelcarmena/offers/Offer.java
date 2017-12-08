package es.rachelcarmena.offers;

import es.rachelcarmena.Basket;

public abstract class Offer {

    public abstract int numberOfOcurrencesFrom(Basket items);
    public abstract int applyOfferAndGetPrice(Basket items, int numberOfOccurrences);
}
