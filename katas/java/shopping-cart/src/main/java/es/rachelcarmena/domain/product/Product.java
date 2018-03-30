package es.rachelcarmena.domain.product;

import es.rachelcarmena.domain.Amount;

public class Product {
    private String name;
    private Amount price;
    private Discount discount;

    private Product(String name, Amount price, Discount discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public static Product of(String name, Amount price, Discount discount) {
        return new Product(name, price, discount);
    }

    public String getName() {
        return name;
    }

    public boolean hasDiscount() {
        return !discount.isEmpty();
    }

    public Amount getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }
}
