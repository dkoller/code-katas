package es.rachelcarmena.creator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NamesSplitter {

    private final List<String> collection;

    public NamesSplitter(String[] names) {
        Map<Boolean, List<String>> partitionByEscapedStrings = partitionByEscapedStrings(names);

        Stream<String> splittedNames = getSplittedNames(partitionByEscapedStrings);
        Stream<String> escapedStrings = getEscapedNames(partitionByEscapedStrings);

        collection = Stream.concat(splittedNames, escapedStrings).collect(Collectors.toList());
    }

    public List<String> commonNames() {
        return collection.stream().filter(name -> !isUpperCase(name)).collect(Collectors.toList());
    }

    public Optional<String> upperCaseName() {
        return collection.stream().filter(name -> isUpperCase(name)).findFirst();
    }

    private Stream<String> getEscapedNames(Map<Boolean, List<String>> partitionByEscapedStrings) {
        Function<String, String> removeEscapingCharacters = name -> name.substring(1, name.length() - 1);
        return partitionByEscapedStrings.get(true).stream().map(removeEscapingCharacters);
    }

    private Stream<String> getSplittedNames(Map<Boolean, List<String>> partitionByEscapedStrings) {
        Function<String, String[]> splitByCommas = name -> name.split("\\s*,\\s*");
        return partitionByEscapedStrings.get(false).stream().map(splitByCommas).flatMap(Arrays::stream).collect(Collectors.toList()).stream();
    }

    private Map<Boolean, List<String>> partitionByEscapedStrings(String[] names) {
        return Arrays.stream(names).collect(Collectors.partitioningBy(name -> name.matches("'.+'")));
    }

    private boolean isUpperCase(String name) {
        return name.toUpperCase().equals(name);
    }
}