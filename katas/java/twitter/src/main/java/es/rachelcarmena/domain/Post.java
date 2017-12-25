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

    Post getNewPostBy(String user) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (message != null ? !message.equals(post.message) : post.message != null) return false;
        return dateTime != null ? dateTime.equals(post.dateTime) : post.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
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

        public String[] createMessagesAt(LocalDateTime now) {
            List<String> messages = new ArrayList<>();
            for (Post post : posts) {
                messages.add(post.getMessageAt(now));
            }
            String[] a = new String[messages.size()];
            return messages.toArray(a);
        }

        public void addNewPostsBy(String author, Posts postsByAuthor) {
            for (Post post : postsByAuthor.posts) {
                posts.add(post.getNewPostBy(author));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Posts)) return false;

            Posts posts1 = (Posts) o;

            return posts != null ? posts.equals(posts1.posts) : posts1.posts == null;
        }

        @Override
        public int hashCode() {
            return posts != null ? posts.hashCode() : 0;
        }
    }
}
