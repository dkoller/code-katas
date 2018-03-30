package es.rachelcarmena.domain.shoppingCart;

import es.rachelcarmena.domain.product.ProductId;

import java.util.Objects;

public class Item {
    private final ProductId productId;
    private final int quantity;

    private Item(ProductId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static Item of(ProductId productId, int quantity) {
        return new Item(productId, quantity);
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity &&
                Objects.equals(productId, item.productId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, quantity);
    }
}
