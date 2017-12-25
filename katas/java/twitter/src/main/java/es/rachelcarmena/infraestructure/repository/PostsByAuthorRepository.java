package es.rachelcarmena.infraestructure.repository;

import es.rachelcarmena.domain.Post;
import es.rachelcarmena.domain.Post.Posts;

import java.util.HashMap;

class PostsByAuthorRepository {
    HashMap<String, Post.Posts> postsByAuthor;

    PostsByAuthorRepository() {
        this.postsByAuthor = new HashMap<>();
    }

    void savePost(String author, Post post) {
        if (postsByAuthor.containsKey(author)) {
            Post.Posts posts = postsByAuthor.get(author);
            posts.add(post);
            postsByAuthor.replace(author, posts);
            return;
        }
        Posts posts = new Posts();
        posts.add(post);
        postsByAuthor.put(author, posts);
    }

    Posts getPostsFrom(String author) {
        if (postsByAuthor.containsKey(author))
            return postsByAuthor.get(author);
        return new Posts();
    }
}