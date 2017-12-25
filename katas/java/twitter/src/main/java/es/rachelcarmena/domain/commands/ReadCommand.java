package es.rachelcarmena.domain.commands;

import es.rachelcarmena.domain.Post.Posts;
import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.infraestructure.Repository;

import java.time.LocalDateTime;

public class ReadCommand extends Command {
    private String author;

    public ReadCommand(String author) {
        this.author = author;
    }

    @Override
    public void execute(LocalDateTime now, Repository repository, Console console) {
        Posts posts = repository.getPostsFrom(author);
        posts.orderByDate();
        console.print(posts.createMessagesAt(now));
    }
}
