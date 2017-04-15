package es.rachelcarmena;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class NumberOfBooksPerTitle {

    private static final int NUMBER_OF_TITLES = 5;
    private Integer[] values;

    public NumberOfBooksPerTitle(int[] titles) {
        calculateValuesInReverseOrder(titles);
    }

    private void calculateValuesInReverseOrder(int[] titles) {
        values = new Integer[NUMBER_OF_TITLES];
        Arrays.fill(values, 0);
        for (Integer title : titles) {
            values[title]++;
        }
        Arrays.sort(values, Comparator.reverseOrder());
    }

    public boolean existIndividualBooks() {
        return numbersGreaterThanZero() > 0;
    }

    public boolean existMoreThanOneTitle() {
        return numbersGreaterThanZero() > 1;
    }

    public int getNumberOfDifferentBooksAndUpdateList() {
        if (existOnlyOnes()) {
            int numberOfOnes = numberOfOnes();
            Arrays.fill(values, 0);
            return numberOfOnes;
        }
        int numberOfDifferentBooks = 0;
        for (int i = 0; i < NUMBER_OF_TITLES; i++) {
            if (values[i] > 0) {
                values[i]--;
                numberOfDifferentBooks++;
            }
        }
        return numberOfDifferentBooks;
    }

    public int getNumberOfIndividualBooks() {
        return Stream.of(values).filter(n -> n > 0).findFirst().get().intValue();
    }

    private int numberOfOnes() {
        return (int) Stream.of(values).filter(n -> n == 1).count();
    }

    private boolean existOnlyOnes() {
        return Stream.of(values).filter(n -> n > 1).count() == 0;
    }

    private int numbersGreaterThanZero() {
        return (int) Stream.of(values).filter(n -> n > 0).count();
    }
}