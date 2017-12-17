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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowCommand)) return false;

        FollowCommand that = (FollowCommand) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return to != null ? to.equals(that.to) : that.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
