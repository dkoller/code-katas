package es.rachelcarmena;

import es.rachelcarmena.utils.Direction;
import es.rachelcarmena.utils.Location;
import es.rachelcarmena.utils.Position;

public class LocationBuilder {

    private Direction direction;
    private Position position;

    public static LocationBuilder aLocation() {
        return new LocationBuilder();
    }

    public LocationBuilder in(Position position) {
        this.position = position;
        return this;
    }

    public LocationBuilder in(int x, int y) {
        this.position = new Position(x, y);
        return this;
    }

    public LocationBuilder withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public Location build() {
        return new Location(position, direction);
    }
}
