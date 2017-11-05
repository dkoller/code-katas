package es.rachelcarmena;

public class GreetingCreator {

    private static final String EMPTY_GREETING = "";

    private UsualNames usualNames;
    private UpperCaseName upperCaseName;

    public GreetingCreator(String... names) {
        usualNames = new UsualNames(names);
        upperCaseName = new UpperCaseName(names);
    }

    public String withUsualNames() {
        if (!usualNames.exists())
            return EMPTY_GREETING;

        return usualNames.getGreeting();
    }

    public String withUpperCaseName() {
        if (!upperCaseName.exists())
            return EMPTY_GREETING;

        String greeting = upperCaseName.getGreeting();
        if (!usualNames.exists())
            return greeting;

        return String.format(" AND %s", greeting);
    }
}