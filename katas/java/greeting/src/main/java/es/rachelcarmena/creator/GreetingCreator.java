package es.rachelcarmena.creator;

import static es.rachelcarmena.creator.CommonNames.COMMON_GREETING;

public class GreetingCreator {

    private static final String ANONYMOUS_NAME = "my friend";
    private static final String EMPTY_GREETING = "";

    private CommonNames commonNames;
    private UpperCaseName upperCaseName;

    public GreetingCreator(NamesSplitter namesSplitter) {
        commonNames = new CommonNames(namesSplitter.commonNames());
        upperCaseName = new UpperCaseName(namesSplitter.upperCaseName());
    }

    public static String anonymous() {
        return String.format(COMMON_GREETING, ANONYMOUS_NAME);
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