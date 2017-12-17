package es.rachelcarmena.domain.commands;

import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.domain.Post;
import es.rachelcarmena.infraestructure.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadCommand extends Command {
    private String user;

    public ReadCommand(String user) {
        this.user = user;
    }

    @Override
    public void execute(LocalDateTime now, Repository repository, Console console) {
        List<Post> posts = repository.getPostsFrom(user);
        posts = posts.stream().sorted(Comparator.comparing(Post::getDateTime).reversed()).collect(Collectors.toList());
        for(Post post: posts) {
            String message = post.getMessageAt(now);
            console.print(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadCommand)) return false;

        ReadCommand that = (ReadCommand) o;

        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }
}
