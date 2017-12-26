package es.rachelcarmena;

import es.rachelcarmena.domain.command.Command;
import es.rachelcarmena.infraestructure.Clock;
import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.infraestructure.repository.Repository;
import es.rachelcarmena.utils.Parser;

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

    public static void main(String... args) {
        Console console = new Console();
        Clock clock = new Clock();
        Repository repository = new Repository();
        Parser parser = new Parser();
        Twitter twitter = new Twitter(console, clock, repository, parser);
        while (true)
            twitter.execute();
    }
}