package es.rachelcarmena;

import java.time.LocalDateTime;

public class Twitter {
    private final Console console;
    private final Clock clock;
    private Repository repository;
    private Parser parser;

    public Twitter(Console console, Clock clock, Repository repository, Parser parser) {
        this.console = console;
        this.clock = clock;
        this.repository = repository;
        this.parser = parser;
    }

    public void execute() {
        String input = console.read();
        LocalDateTime datetime = clock.now();

        Command command = parser.parse(input);
        command.execute(datetime, repository, console);
    }
}
