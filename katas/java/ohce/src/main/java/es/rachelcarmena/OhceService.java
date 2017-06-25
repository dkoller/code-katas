package es.rachelcarmena;

public class OhceService {

    private final Console console;
    private Interpreter interpreter;
    private Clock clock;

    public OhceService(Interpreter interpreter, Clock clock, Console console) {
        this.interpreter = interpreter;
        this.clock = clock;
        this.console = console;
    }

    public void start() {
        String input = console.read();
        InterpreterResult result = interpreter.interpret(input, clock.hour());
        printMessages(result);
        if (result.action() == Action.ABORT) return;

        while (true) {
            input = console.read();
            result = interpreter.interpret(input);
            printMessages(result);
            if (result.action() == Action.ABORT) break;
        }
    }

    private void printMessages(InterpreterResult result) {
        for(String message: result.messages())
            console.write(message);
    }
}
