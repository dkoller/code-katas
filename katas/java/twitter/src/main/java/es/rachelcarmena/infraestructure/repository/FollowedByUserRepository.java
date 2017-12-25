package es.rachelcarmena.infraestructure.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class FollowedByUserRepository {
    HashMap<String, List<String>> followedByUser;

    FollowedByUserRepository() {
        this.followedByUser = new HashMap<>();
    }

    void saveFollowing(String from, String to) {
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

    List<String> getFollowedBy(String user) {
        if (followedByUser.containsKey(user))
            return followedByUser.get(user);
        return new ArrayList<>();
    }
}