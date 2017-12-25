package es.rachelcarmena.infraestructure;

import es.rachelcarmena.domain.Post;
import es.rachelcarmena.domain.Post.Posts;

import java.util.List;

public class Repository {

    PostsByAuthorRepository postsByAuthorRepository;
    FollowedByUserRepository followedByUserRepository;

    public Repository() {
        this.postsByAuthorRepository = new PostsByAuthorRepository();
        this.followedByUserRepository = new FollowedByUserRepository();
    }

    public void savePost(String author, Post post) {
        postsByAuthorRepository.savePost(author, post);
    }

    public Posts getPostsFrom(String author) {
        return postsByAuthorRepository.getPostsFrom(author);
    }

    public void saveFollowing(String from, String to) {
        followedByUserRepository.saveFollowing(from, to);
    }

    public List<String> getFollowedBy(String author) {
        return followedByUserRepository.getFollowedBy(author);
    }
}