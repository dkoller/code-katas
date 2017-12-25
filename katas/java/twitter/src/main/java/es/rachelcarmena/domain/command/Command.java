package es.rachelcarmena.domain.command;

import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.infraestructure.repository.Repository;

import java.time.LocalDateTime;

public abstract class Command {
    public abstract void execute(LocalDateTime now, Repository repository, Console console);
}
