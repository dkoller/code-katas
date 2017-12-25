package es.rachelcarmena.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Post {
    private static final String MESSAGE_WITH_AUTHOR_FORMAT = "%s - %s";
    private static final String SECONDS_FORMAT = "%s (%s second%s ago)";
    private static final String MINUTES_FORMAT = "%s (%s minute%s ago)";

    protected final String message;
    protected final LocalDateTime dateTime;

    public Post(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    LocalDateTime getDateTime() {
        return dateTime;
    }

    Post createNewPostBy(String user) {
        String newMessage = String.format(MESSAGE_WITH_AUTHOR_FORMAT, user, message);
        return new Post(newMessage, dateTime);
    }

    String getMessageAt(LocalDateTime now) {
        Duration difference = Duration.between(dateTime, now);
        long minutes = difference.toMinutes();
        if (minutes > 0) {
            return String.format(MINUTES_FORMAT, message, minutes, minutes > 1 ? "s" : "");
        }
        long seconds = difference.getSeconds();
        return String.format(SECONDS_FORMAT, message, seconds, seconds > 1 ? "s" : "");
    }

    public static class Posts {

        List<Post> posts;

        public Posts() {
            this.posts = new ArrayList<>();
        }

        public void add(Post post) {
            posts.add(post);
        }

        public void orderByDate() {
            posts = posts.stream().sorted(Comparator.comparing(Post::getDateTime).reversed()).collect(Collectors.toList());
        }

        public void addNewPostsBy(String author, Posts postsByAuthor) {
            for (Post post : postsByAuthor.posts) {
                Post newPost = post.createNewPostBy(author);
                posts.add(newPost);
            }
        }

        public String[] createMessagesAt(LocalDateTime now) {
            int index = 0;
            String[] messages = new String[posts.size()];
            for (Post post : posts) {
                messages[index] = post.getMessageAt(now);
                index++;
            }
            return messages;
        }
    }
}