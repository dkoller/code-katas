package es.rachelcarmena.utils;

public class Location {

    protected final Position position;
    protected final Direction direction;

    public Location(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position calculatePositionWhenForward() {
        return calculatePosition(true);
    }

    public Position calculatePositionWhenBackward() {
        return calculatePosition(false);
    }

    private Position calculatePosition(boolean forward) {
        int newX = position.x;
        int newY = position.y;
        switch (direction) {
            case NORTH:
                newY = forward ? newY + 1 : newY - 1;
                break;
            case SOUTH:
                newY = forward ? newY - 1 : newY + 1;
                break;
            case EAST:
                newX = forward ? newX + 1 : newX - 1;
                break;
            case WEST:
                newX = forward ? newX - 1 : newX + 1;
        }
        return new Position(newX, newY);
    }

    public Location calculateLocationWhenTurnRight() {
        Direction newDirection = direction.turnRight();
        return new Location(position, newDirection);
    }

    public Location calculateLocationWhenTurnLeft() {
        Direction newDirection = direction.turnLeft();
        return new Location(position, newDirection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!position.equals(location.position)) return false;
        return direction == location.direction;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }
}