package es.rachelcarmena.utils;

public class Direction {

    public enum Type {
        NORTH {
            @Override
            public Type turnRight() {
                return EAST;
            }

            @Override
            public Type turnLeft() {
                return WEST;
            }
        }, EAST {
            @Override
            public Type turnRight() {
                return SOUTH;
            }

            @Override
            public Type turnLeft() {
                return NORTH;
            }
        }, WEST {
            @Override
            public Type turnRight() {
                return NORTH;
            }

            @Override
            public Type turnLeft() {
                return SOUTH;
            }
        }, SOUTH {
            @Override
            public Type turnRight() {
                return WEST;
            }

            @Override
            public Type turnLeft() {
                return EAST;
            }
        };

        public abstract Type turnRight();

        public abstract Type turnLeft();
    }
}
