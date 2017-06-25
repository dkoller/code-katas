package es.rachelcarmena;

public class Interpreter {

    private static final String STARTING_MESSAGE_INPUT = "ohce";
    private static final String GOOD_MORNING_STRING_FORMAT = "¡Buenos días %s!";
    private static final String GOOD_EVENING_STRING_FORMAT = "¡Buenas tardes %s!";
    private static final String GOOD_NIGHT_STRING_FORMAT = "¡Buenas noches %s!";
    public static final String CLOSING_MESSAGE_INPUT = "Stop!";
    public static final String MESSAGE_WHEN_PALINDROME = "¡Bonita palabra!";

    private String username;

    public InterpreterResult interpret(String message, int hour) {
        InterpreterResult result = new InterpreterResult();

        if (!message.startsWith(STARTING_MESSAGE_INPUT)) {
            result.addMessage(String.format("It should start with %s", STARTING_MESSAGE_INPUT));
            result.action(Action.ABORT);
            return result;
        }

        username = message.substring(STARTING_MESSAGE_INPUT.length() + 1);
        if (hour >= 6 && hour < 12) {
            result.addMessage(String.format(GOOD_MORNING_STRING_FORMAT, username));
            return result;
        }
        if (hour >= 12 && hour < 20) {
            result.addMessage(String.format(GOOD_EVENING_STRING_FORMAT, username));
            return result;
        }
        result.addMessage(String.format(GOOD_NIGHT_STRING_FORMAT, username));
        return result;
    }

    public InterpreterResult interpret(String message) {
        InterpreterResult result = new InterpreterResult();

        if (message.equals(CLOSING_MESSAGE_INPUT)) {
            result.addMessage(String.format("Adios %s", username));
            result.action(Action.ABORT);
            return result;
        }
        String reverse = new StringBuilder(message).reverse().toString();
        result.addMessage(reverse);
        if (message.equals(reverse))
            result.addMessage(MESSAGE_WHEN_PALINDROME);
        return result;
    }
}
