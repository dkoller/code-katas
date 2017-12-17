package es.rachelcarmena.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class Post {
    private final String message;
    private final LocalDateTime dateTime;

    public Post(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessageAt(LocalDateTime now) {
        Duration difference = Duration.between(dateTime, now);
        long minutes = difference.toMinutes();
        if (minutes > 0) {
            return String.format("%s (%s minute%s ago)", message, minutes, minutes > 1 ? "s" : "");
        }
        long seconds = difference.getSeconds();
        return String.format("%s (%s second%s ago)", message, seconds, seconds > 1 ? "s" : "");
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Post getNewPostBy(String user) {
        return new Post(user + " - " + message, dateTime);
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
}
