package es.rachelcarmena;

public class GreetingCreator {

    private static final String EMPTY_GREETING = "";

    private CommonNames commonNames;
    private UpperCaseName upperCaseName;

    public GreetingCreator(String... names) {
        commonNames = new CommonNames(names);
        upperCaseName = new UpperCaseName(names);
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