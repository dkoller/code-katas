package es.rachelcarmena.catalog;

import java.util.HashMap;

public class PricePerProduct {

    HashMap<Character, Integer> values;

    public PricePerProduct() {
        values = new HashMap<>();
        values.put('A', 50);
        values.put('B', 30);
        values.put('C', 20);
        values.put('D', 15);
        values.put('E', 40);
        values.put('F', 10);
        values.put('G', 20);
        values.put('H', 10);
        values.put('I', 35);
        values.put('J', 60);
        values.put('K', 70);
        values.put('L', 90);
        values.put('M', 15);
        values.put('N', 40);
        values.put('O', 10);
        values.put('P', 50);
        values.put('Q', 30);
        values.put('R', 50);
        values.put('S', 20);
        values.put('T', 20);
        values.put('U', 40);
        values.put('V', 50);
        values.put('W', 20);
        values.put('X', 17);
        values.put('Y', 20);
        values.put('Z', 21);
    }

    public Integer getPriceFor(char item) {
        return values.get(item);
    }
}