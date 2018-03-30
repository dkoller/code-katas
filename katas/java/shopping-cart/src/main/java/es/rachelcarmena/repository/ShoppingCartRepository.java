package es.rachelcarmena.repository;

import es.rachelcarmena.domain.shoppingCart.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository {
    private List<Item> items = new ArrayList<>();
    private LocalDateTime localDateTime;

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void initShoppingCart(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public LocalDateTime getDate() {
        return localDateTime;
    }

    public List<Item> getItems() {
        return items;
    }
}
