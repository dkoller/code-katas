package es.rachelcarmena;

import java.time.LocalDateTime;

public class Post {
    private final String message;
    private final LocalDateTime localDateTime;

    public Post(String message, LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (message != null ? !message.equals(post.message) : post.message != null) return false;
        return localDateTime != null ? localDateTime.equals(post.localDateTime) : post.localDateTime == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }
}
