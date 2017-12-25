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

    private static final String AUTHOR = "rachel";
    private static final LocalDateTime ANY_DATE = LocalDateTime.now();

    @Test
    public void save_and_get_posts_by_author() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("message1", ANY_DATE));
        posts.add(new Post("message2", ANY_DATE));

        Repository repository = new Repository();
        for (Post post: posts)
            repository.savePost(AUTHOR, post);

        assertThat(repository.getPostsFrom(AUTHOR), is(posts));
    }

    @Test
    public void return_an_empty_list_when_no_posts_by_author() {
        Repository repository = new Repository();

        assertThat(repository.getPostsFrom(AUTHOR), is(new ArrayList<>()));
    }

    @Test
    public void save_and_get_followed_users_by_author() {
        List<String> followedByAuthor = new ArrayList<>();
        followedByAuthor.add("user1");
        followedByAuthor.add("user2");
        followedByAuthor.add("user3");

        Repository repository = new Repository();
        for (String user: followedByAuthor)
            repository.saveFollowing(AUTHOR, user);

        assertThat(repository.getFollowedBy(AUTHOR), is(followedByAuthor));
    }

    @Test
    public void return_an_empty_list_when_no_followed_users_by_author() {
        Repository repository = new Repository();

        assertThat(repository.getFollowedBy(AUTHOR), is(new ArrayList<>()));
    }
}
