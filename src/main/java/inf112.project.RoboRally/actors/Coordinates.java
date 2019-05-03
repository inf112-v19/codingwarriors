package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.objects.GridDirection;

public class Coordinates {
    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates otherCord = (Coordinates) obj;
        return (otherCord.x == x && otherCord.y == y);
    }

    public GridDirection getDirection(Coordinates otherCoordinates) {
        int x = this.x - otherCoordinates.getX();
        int y = this.y - otherCoordinates.getY();

        if (x > 0) return GridDirection.WEST;
        else if (x < 0) return GridDirection.EAST;
        else if (y > 0) return GridDirection.SOUTH;
        else return GridDirection.NORTH;
    }
}

