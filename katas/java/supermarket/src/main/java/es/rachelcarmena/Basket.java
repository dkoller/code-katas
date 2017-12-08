package es.rachelcarmena;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Character> items;

    public Basket(String skus) {
        char[] elements = skus.toCharArray();
        items = new ArrayList<>();
        for (char element : elements)
            items.add(element);
    }

    public ArrayList<Character> getItems() {
        return items;
    }

    public int getNumberOf(char itemToFind) {
        return (int) items.stream().filter(item -> item == itemToFind).count();
    }

    public int getNumberOfItemsIn(char[] packItems) {
        return (int) items.stream().filter(item -> existsIn(item, packItems)).count();
    }

    private boolean existsIn(Character item, char[] packItems) {
        for (char packItem : packItems)
            if (item.equals(packItem))
                return true;
        return false;
    }

    public void remove(int numberOfItems, char itemToDiscount) {
        while (numberOfItems > 0) {
            remove(itemToDiscount);
            numberOfItems--;
        }
    }

    public void remove(int numberOfItems, char[] packTtems) {
        for (char packItem : packTtems)
            while (numberOfItems > 0 && items.contains(packItem)) {
                remove(packItem);
                numberOfItems--;
            }
    }

    private void remove(char item) {
        items.remove(Character.valueOf(item));
    }
}