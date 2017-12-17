package es.rachelcarmena.infraestructure;

import es.rachelcarmena.domain.Post;

import java.util.List;

public class Repository {
    public void savePost(String author, Post post) {

    }

    public List<Post> getPostsFrom(String author) {
        return null;
    }

    public void saveFollowing(String from, String to) {

    }

    public List<String> getFollowedBy(String user) {
        return null;
    }
}