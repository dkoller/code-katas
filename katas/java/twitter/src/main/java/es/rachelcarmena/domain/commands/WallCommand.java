package es.rachelcarmena.domain.commands;

import es.rachelcarmena.infraestructure.Console;
import es.rachelcarmena.domain.Post;
import es.rachelcarmena.infraestructure.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WallCommand extends Command {
    private String user;

    public WallCommand(String user) {
        this.user = user;
    }

    @Override
    public void execute(LocalDateTime now, Repository repository, Console console) {
        List<Post> newPosts = new ArrayList<>();

        List<Post> posts = repository.getPostsFrom(user);
        for (Post post: posts) {
            newPosts.add(post.getNewPostBy(user));
        }

        List<String> users = repository.getFollowedBy(user);
        for(String anUser: users) {
            List<Post> postsByUser = repository.getPostsFrom(anUser);
            for (Post post: postsByUser) {
                newPosts.add(post.getNewPostBy(anUser));
            }
        }
        newPosts = newPosts.stream().sorted(Comparator.comparing(Post::getDateTime).reversed()).collect(Collectors.toList());
        for(Post post: newPosts) {
            String message = post.getMessageAt(now);
            console.print(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WallCommand)) return false;

        WallCommand that = (WallCommand) o;

        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }
}
