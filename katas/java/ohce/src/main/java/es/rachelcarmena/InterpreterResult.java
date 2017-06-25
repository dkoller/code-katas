package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;

public class InterpreterResult {

    private List<String> messages;
    private Action action;

    public InterpreterResult() {
        messages = new ArrayList();
        action = Action.CONTINUE;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public Action action() {
        return action;
    }

    public void action(Action action) {
        this.action = action;
    }

    public List<String> messages() {
        return messages;
    }
}
