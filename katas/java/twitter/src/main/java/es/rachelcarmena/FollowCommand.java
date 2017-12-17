package es.rachelcarmena;

import java.time.LocalDateTime;

public class FollowCommand extends Command {
    private final String from;
    private final String to;

    public FollowCommand(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(LocalDateTime now, Repository repository, Console console) {
        repository.saveFollowing(from, to);
    }
}
