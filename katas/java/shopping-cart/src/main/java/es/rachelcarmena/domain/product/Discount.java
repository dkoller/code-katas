package es.rachelcarmena.domain.product;

public class Discount {
    private int numberOfItems;
    private int percentage;

    public Discount() {
        this.numberOfItems = 0;
        this.percentage = 0;
    }

    private Discount(int quantity, int percentage) {
        this.numberOfItems = quantity;
        this.percentage = percentage;
    }

    public static Discount of(int quantity, int percentage) {
        return new Discount(quantity, percentage);
    }

    public static Discount empty() {
        return new Discount();
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getPercentage() {
        return percentage;
    }

    public boolean isEmpty() {
        return percentage <= 0;
    }
}