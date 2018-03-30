package es.rachelcarmena.domain.ticket;

import es.rachelcarmena.domain.Amount;

public class DiscountLine {
    private String productName;
    private Amount amount;

    public DiscountLine(String productName, Amount amount) {
        this.productName = productName;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public Amount getAmount() {
        return amount;
    }
}
