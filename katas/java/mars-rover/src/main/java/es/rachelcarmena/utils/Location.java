package es.rachelcarmena.utils;

public class Location {

    protected final int x;
    protected final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location calculateNewPosition(Direction currentDirection, boolean forward) {
        int newX = x;
        int newY = y;
        switch (currentDirection) {
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
        return new Location(newX, newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        return y == location.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public enum Direction {
        NORTH {
            @Override
            public Direction turnRight() {
                return EAST;
            }

            @Override
            public Direction turnLeft() {
                return WEST;
            }
        }, EAST {
            @Override
            public Direction turnRight() {
                return SOUTH;
            }

            @Override
            public Direction turnLeft() {
                return NORTH;
            }
        }, WEST {
            @Override
            public Direction turnRight() {
                return NORTH;
            }

            @Override
            public Direction turnLeft() {
                return SOUTH;
            }
        }, SOUTH {
            @Override
            public Direction turnRight() {
                return WEST;
            }

            @Override
            public Direction turnLeft() {
                return EAST;
            }
        };

        public abstract Direction turnRight();

        public abstract Direction turnLeft();
    }
}
