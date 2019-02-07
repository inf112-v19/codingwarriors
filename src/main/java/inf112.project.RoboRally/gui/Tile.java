package inf112.project.RoboRally.gui;

public class Tile {
    private int startX;
    private int endX;
    private int startY;
    private int endY;

    Tile(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getStartY() {
        return startY;
    }

    public int numberOfPixelsY() {
        return endY-startY;
    }

    public int numberOfPixelsX() {
        return endX-startY;
    }

    public boolean positionInsideTile(int x, int y) {
        return x >= this.getStartX() && x <= this.getEndX() && y >= this.getStartY() && y <= this.getEndY();
    }
}
