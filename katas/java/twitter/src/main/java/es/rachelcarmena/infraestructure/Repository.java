package es.rachelcarmena.infraestructure;

import es.rachelcarmena.domain.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    HashMap<String, List<Post>> postsByAuthor;
    HashMap<String, List<String>> followedByUser;

    public Repository() {
        this.postsByAuthor = new HashMap<>();
        this.followedByUser = new HashMap<>();
    }

    public void savePost(String author, Post post) {
        if (postsByAuthor.containsKey(author)) {
            List<Post> posts = postsByAuthor.get(author);
            posts.add(post);
            postsByAuthor.replace(author, posts);
            return;
        }
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        postsByAuthor.put(author, posts);
    }

    public List<Post> getPostsFrom(String author) {
        if (postsByAuthor.containsKey(author))
            return postsByAuthor.get(author);
        return new ArrayList<>();
    }

    public void saveFollowing(String from, String to) {
        if (followedByUser.containsKey(from)) {
            List<String> followedBy = followedByUser.get(from);
            followedBy.add(to);
            followedByUser.replace(from, followedBy);
            return;
        }
        List<String> followedBy = new ArrayList<>();
        followedBy.add(to);
        followedByUser.put(from, followedBy);
    }

    public List<String> getFollowedBy(String user) {
        if (followedByUser.containsKey(user))
            return followedByUser.get(user);
        return new ArrayList<>();
    }
}