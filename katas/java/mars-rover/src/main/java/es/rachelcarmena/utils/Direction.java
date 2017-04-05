package es.rachelcarmena.utils;

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
