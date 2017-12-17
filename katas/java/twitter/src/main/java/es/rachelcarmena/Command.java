package es.rachelcarmena;

import java.time.LocalDateTime;

public abstract class Command {
    public abstract void execute(LocalDateTime now, Repository repository, Console console);
}
