package es.rachelcarmena.domain.commands;

import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.domain.Post.Posts;
import es.rachelcarmena.infraestructure.Repository;

import java.time.LocalDateTime;
import java.util.List;

public class WallCommand extends Command {
    private String author;

    public WallCommand(String author) {
        this.author = author;
    }

    @Override
    public void execute(LocalDateTime now, Repository repository, Console console) {
        Posts allPosts = new Posts();
        allPosts.addNewPostsBy(author, repository.getPostsFrom(author));

        List<String> users = repository.getFollowedBy(author);
        for(String user: users) {
            allPosts.addNewPostsBy(user, repository.getPostsFrom(user));
        }

        allPosts.orderByDate();
        console.print(allPosts.createMessagesAt(now));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WallCommand)) return false;

        WallCommand that = (WallCommand) o;

        return author != null ? author.equals(that.author) : that.author == null;
    }

    @Override
    public int hashCode() {
        return author != null ? author.hashCode() : 0;
    }
}
