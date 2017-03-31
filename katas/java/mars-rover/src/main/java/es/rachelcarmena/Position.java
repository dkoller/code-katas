package es.rachelcarmena;

public class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void calculateNewPosition(Direction.Type currentDirection, boolean forward) {
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
        x = newX;
        y = newY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
