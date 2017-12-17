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
}
