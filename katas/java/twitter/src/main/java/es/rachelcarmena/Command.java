package es.rachelcarmena;

import java.time.LocalDateTime;

public abstract class Command {
    public abstract void execute(LocalDateTime datetime, Repository repository, Console console);
}
