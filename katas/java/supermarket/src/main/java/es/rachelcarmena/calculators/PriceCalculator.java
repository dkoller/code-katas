package es.rachelcarmena.calculators;

import es.rachelcarmena.Basket;
import es.rachelcarmena.catalog.PricePerProduct;

import java.util.stream.Collectors;

public class PriceCalculator {
    private PricePerProduct pricePerProduct;

    public PriceCalculator(PricePerProduct pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public int calculatePriceFor(Basket basket) {
        return basket.getItems().stream().collect(Collectors.summingInt(item -> pricePerProduct.getPriceFor(item)));
    }
}
