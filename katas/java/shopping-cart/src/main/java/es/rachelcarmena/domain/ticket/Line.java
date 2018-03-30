package es.rachelcarmena.domain.ticket;

import es.rachelcarmena.domain.Amount;

public class Line {
    private int numberOfItems;
    private String productName;
    private Amount totalPrice;
    private Amount pricePerUnit;

    public Line(String productName, Amount totalPrice, int quantity, Amount pricePerUnit) {
        this.numberOfItems = quantity;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductName() {
        return productName;
    }

    public Amount getTotalPrice() {
        return totalPrice;
    }

    public Amount getPricePerUnit() {
        return pricePerUnit;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }
}
