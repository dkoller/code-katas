package es.rachelcarmena.creator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.rachelcarmena.creator.CommonNames.COMMON_GREETING;

public class GreetingCreator {

    private static final String ANONYMOUS_NAME = "my friend";
    private static final String EMPTY_GREETING = "";

    private CommonNames commonNames;
    private UpperCaseName upperCaseName;

    public static String anonymous() {
        return String.format(COMMON_GREETING, ANONYMOUS_NAME);
    }

    public GreetingCreator(String... names) {
        List<String> splittedNames = splitNames(names);
        commonNames = new CommonNames(splittedNames);
        upperCaseName = new UpperCaseName(splittedNames);
    }

    private List<String> splitNames(String[] names) {
        Map<Boolean, List<String>> partitionByEscapedStrings = Arrays.stream(names).collect(Collectors.partitioningBy(name -> name.matches("'.+'")));

        Function<String, String[]> splitByCommas = name -> name.split("\\s*,\\s*");
        List<String> splittedNames = partitionByEscapedStrings.get(false).stream().map(splitByCommas).flatMap(Arrays::stream).collect(Collectors.toList());

        Function<String, String> removeEscapingCharacters = name -> name.substring(1, name.length() - 1);
        Stream<String> escapedStrings = partitionByEscapedStrings.get(true).stream().map(removeEscapingCharacters);

        return Stream.concat(splittedNames.stream(), escapedStrings).collect(Collectors.toList());
    }

    public String withCommonNames() {
        if (!commonNames.exists())
            return EMPTY_GREETING;

        return commonNames.getGreeting();
    }

    public String withUpperCaseName() {
        if (!upperCaseName.exists())
            return EMPTY_GREETING;

        String greeting = upperCaseName.getGreeting();
        if (!commonNames.exists())
            return greeting;

        return String.format(" AND %s", greeting);
    }
}