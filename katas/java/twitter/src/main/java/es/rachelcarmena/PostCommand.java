package es.rachelcarmena;

import java.time.LocalDateTime;

public class PostCommand extends Command {
    private final String author;
    private final String message;

    public PostCommand(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public void execute(LocalDateTime datetime, Repository repository, Console console) {
        Post post = new Post(message, datetime);
        repository.savePost(author, post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostCommand)) return false;

        PostCommand that = (PostCommand) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
