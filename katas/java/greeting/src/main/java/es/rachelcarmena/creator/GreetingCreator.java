package es.rachelcarmena.creator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> splittedNames = splitNamesWithCommas(names);
        commonNames = new CommonNames(splittedNames);
        upperCaseName = new UpperCaseName(splittedNames);
    }

    private List<String> splitNamesWithCommas(String[] names) {
        return Arrays.stream(names).map(name -> name.split("\\s*,\\s*")).flatMap(Arrays::stream).collect(Collectors.toList());
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