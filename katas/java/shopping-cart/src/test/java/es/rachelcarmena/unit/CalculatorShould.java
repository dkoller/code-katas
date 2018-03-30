package es.rachelcarmena.unit;

import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.product.Discount;
import es.rachelcarmena.domain.product.Product;
import es.rachelcarmena.domain.product.ProductId;
import es.rachelcarmena.domain.shoppingCart.Item;
import es.rachelcarmena.util.Calculator;
import org.junit.Test;

import static es.rachelcarmena.acceptance.ShoppingCartServiceFeature.CHOCOLATE_BAR;
import static es.rachelcarmena.acceptance.ShoppingCartServiceFeature.CHOCOLATE_BAR_ID;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorShould {

    @Test
    public void get_price_from_item_and_product() {
        Product product = Product.of(CHOCOLATE_BAR, Amount.valueOf(2), Discount.empty());
        Item item = Item.of(ProductId.of(CHOCOLATE_BAR_ID), 5);

        Calculator calculator = new Calculator();
        assertTrue(reflectionEquals(calculator.getPrice(item.getQuantity(), product.getPrice()), Amount.valueOf(10)));
    }

    @Test
    public void get_discount_from_item_and_product() {
        Product product = Product.of(CHOCOLATE_BAR, Amount.valueOf(2), Discount.of(2, 50));
        Item item = Item.of(ProductId.of(CHOCOLATE_BAR_ID), 5);

        Calculator calculator = new Calculator();
        assertTrue(reflectionEquals(calculator.getDiscount(item.getQuantity(), product.getPrice(), product.getDiscount()), Amount.valueOf(4)));
    }
}
