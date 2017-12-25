package es.rachelcarmena.unit;

import es.rachelcarmena.domain.Post;
import es.rachelcarmena.infraestructure.Repository;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RepositoryShould {
    @Test
    public void save_and_get_posts_by_author() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("message1", LocalDateTime.now()));
        posts.add(new Post("message2", LocalDateTime.now()));

        Repository repository = new Repository();
        for (Post post: posts)
            repository.savePost("rachel", post);

        assertThat(repository.getPostsFrom("rachel"), is(posts));
    }

    @Test
    public void save_and_get_followers() {
        List<String> followedByMainUser = new ArrayList<>();
        followedByMainUser.add("user1");
        followedByMainUser.add("user2");
        followedByMainUser.add("user3");

        Repository repository = new Repository();
        for (String user: followedByMainUser)
            repository.saveFollowing("rachel", user);

        assertThat(repository.getFollowedBy("rachel"), is(followedByMainUser));
    }
}
