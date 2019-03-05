package inf112.project.RoboRally.gui;

public class Grid {
    private Tile screen;
    private int width;
    private int height;
    private int tileSizeX;
    private int tileSizeY;

    Grid(Tile mainTile, int width, int height) {
        this.width = width;
        this.height = height;
        this.screen = mainTile;
        calculateTileSize();
    }

    public void calculateTileSize() {
        tileSizeX = screen.numberOfPixelsX()/width;
        tileSizeY = screen.numberOfPixelsY()/height;
    }

    public void setNumberOtTiles(int width, int height) {
        if (this.width == width && this.height == height) { return; }
        if (width == 0 || height == 0) { return; }
        this.width = width;
        this.height = height;
        calculateTileSize();
    }

    public int getStartX(int x) {
        return x*tileSizeX+screen.getStartX();
    }

    public int getStartY(int y) {
        return y*tileSizeY+screen.getStartY();
    }

    public int getEndX(int x) {
        return x*tileSizeY+screen.getStartX()+tileSizeX;
    }

    public int getEndY(int y) {
        return y*tileSizeY+screen.getStartY()+tileSizeY;
    }

    public int getTileWidth() {
        return tileSizeX;
    }

    public int getTileHeight() {
        return tileSizeY;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public boolean PositionIsInsideScreen(int x, int y) {
        return x >= screen.getStartX() && x <= screen.getEndX() && y >= screen.getStartY() && y <= screen.getEndY();
    }

    public int getTileIndex(int y) {
        return y/tileSizeY;
    }

    public Tile getScreen() {
        return screen;
    }
}
