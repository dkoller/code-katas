package es.rachelcarmena.util;

import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.product.Discount;

public class Calculator {

    public Amount getPrice(int quantity, Amount pricePerUnit) {
        return pricePerUnit.multiply(quantity);
    }

    public Amount getDiscount(int quantity, Amount pricePerUnit, Discount discount) {
        if (discount.isEmpty()) return Amount.valueOf(0);

        int numberOfDiscounts = quantity / discount.getNumberOfItems();
        Amount pricePerPack = getPrice(discount.getNumberOfItems(), pricePerUnit);
        Amount discountPerPack = pricePerPack.calculatePercentage(discount.getPercentage());
        return discountPerPack.multiply(numberOfDiscounts);
    }
}
