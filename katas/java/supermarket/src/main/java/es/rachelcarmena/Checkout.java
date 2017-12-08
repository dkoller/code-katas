package es.rachelcarmena;

import es.rachelcarmena.calculators.OfferCalculator;
import es.rachelcarmena.calculators.PriceCalculator;
import es.rachelcarmena.catalog.Offers;
import es.rachelcarmena.catalog.PricePerProduct;

public class Checkout {

    public static Integer checkout(String skus) {
        if (!isValid(skus)) return -1;

        Basket basket = new Basket(skus);

        Offers offers = new Offers();
        OfferCalculator offerCalculator = new OfferCalculator(offers);
        int priceWithDiscount = offerCalculator.calculatePriceFor(basket);

        PricePerProduct pricePerProduct = new PricePerProduct();
        PriceCalculator priceCalculator = new PriceCalculator(pricePerProduct);
        int priceOfTheRestItems = priceCalculator.calculatePriceFor(basket);

        return priceWithDiscount + priceOfTheRestItems;
    }

    private static boolean isValid(String skus) {
        return skus.matches("[A-Z]*");
    }
}