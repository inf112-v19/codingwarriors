package inf112.project.RoboRally.actors;

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
        if (otherCord.x == x && otherCord.y == y)
            return true;
        return false;
    }
}

