package es.rachelcarmena.creator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NamesSplitter {

    private final List<String> names;

    public NamesSplitter(String... names) {
        this.names = getRealNamesFrom(names);
    }

    public List<String> commonNames() {
        return names.stream().filter(name -> !isUpperCase(name)).collect(Collectors.toList());
    }

    public Optional<String> upperCaseName() {
        return names.stream().filter(name -> isUpperCase(name)).findFirst();
    }

    private List<String> getRealNamesFrom(String... names) {
        Map<Boolean, List<String>> partitionByEscapedStrings = partitionByEscapedStrings(names);

        Stream<String> splittedNames = getSplittedNames(partitionByEscapedStrings.get(false));
        Stream<String> escapedStrings = getEscapedNames(partitionByEscapedStrings.get(true));

        return Stream.concat(splittedNames, escapedStrings).collect(Collectors.toList());
    }

    private Map<Boolean, List<String>> partitionByEscapedStrings(String[] names) {
        return Arrays.stream(names).collect(Collectors.partitioningBy(name -> name.matches("'.+'")));
    }

    private Stream<String> getEscapedNames(List<String> partitionByEscapedStrings) {
        Function<String, String> removeEscapingCharacters = name -> name.substring(1, name.length() - 1);
        return partitionByEscapedStrings.stream().map(removeEscapingCharacters);
    }

    private Stream<String> getSplittedNames(List<String> partitionByEscapedStrings) {
        Function<String, String[]> splitByCommas = name -> name.split("\\s*,\\s*");
        return partitionByEscapedStrings.stream().map(splitByCommas).flatMap(Arrays::stream).collect(Collectors.toList()).stream();
    }

    private boolean isUpperCase(String name) {
        return name.toUpperCase().equals(name);
    }
}