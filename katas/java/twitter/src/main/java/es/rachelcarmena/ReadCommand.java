package es.rachelcarmena;

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
}
