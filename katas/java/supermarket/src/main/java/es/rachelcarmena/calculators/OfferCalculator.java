package es.rachelcarmena.calculators;

import es.rachelcarmena.Basket;
import es.rachelcarmena.catalog.Offers;
import es.rachelcarmena.offers.Offer;

public class OfferCalculator {
    private Offers offers;

    public OfferCalculator(Offers offers) {
        this.offers = offers;
    }

    public int calculatePriceFor(Basket items) {
        int price = 0;
        for(Offer offer: offers.getList()) {
            int numberOfOccurrences = offer.numberOfOcurrencesFrom(items);
            if (numberOfOccurrences > 0)
                price += offer.applyOfferAndGetPrice(items, numberOfOccurrences);
        }
        return price;
    }
}