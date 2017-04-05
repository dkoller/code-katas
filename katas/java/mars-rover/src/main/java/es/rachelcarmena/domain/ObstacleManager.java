package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Location;

import java.util.HashSet;
import java.util.Set;

public class ObstacleManager {

    Set<Location> obstacles = new HashSet<>();

    public boolean detectObstacleIn(Location location) {
        return !obstacles.contains(location);
    }

    public void addObstacleIn(Location location) {
        obstacles.add(location);
    }
}
