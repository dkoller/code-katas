package es.rachelcarmena.domain;

import es.rachelcarmena.utils.Position;

import java.util.HashSet;
import java.util.Set;

public class ObstacleManager {

    Set<Position> obstacles = new HashSet<>();

    public boolean detectObstacleIn(Position position) {
        return obstacles.contains(position);
    }

    public void addObstacleIn(Position position) {
        obstacles.add(position);
    }
}
