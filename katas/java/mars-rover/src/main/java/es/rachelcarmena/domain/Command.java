package es.rachelcarmena.domain;

public enum Command {
    FORWARD('f'),
    BACKWARD('b'),
    RIGHT('r'),
    LEFT('l');

    final public char name;

    Command(char name) {
        this.name = name;
    }
}
