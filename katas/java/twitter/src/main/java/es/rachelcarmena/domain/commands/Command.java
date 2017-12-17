package es.rachelcarmena.domain.commands;

import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.infraestructure.Repository;

import java.time.LocalDateTime;

public abstract class Command {
    public abstract void execute(LocalDateTime now, Repository repository, Console console);
}
