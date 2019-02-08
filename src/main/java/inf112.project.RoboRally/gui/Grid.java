package inf112.project.RoboRally.gui;

public class Grid {
    private Tile screen;
    private Tile[][] grid;
    private int width;
    private int height;
    private int tileSizeX;
    private int tileSizeY;

    Grid(Tile mainTile, int width, int height) {
        this.width = width;
        this.height = height;
        this.screen = mainTile;
        this.grid = new Tile[width][height];
        calculateTileSize();
        generateTiles();
    }

    private void calculateTileSize() {
        tileSizeX = screen.numberOfPixelsX()/width;
        tileSizeY = screen.numberOfPixelsY()/height;
    }

    private void generateTiles() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[j][i] = new Tile(
                        j*tileSizeX+screen.getStartX(),
                        j*tileSizeX+screen.getStartX()+tileSizeX,
                        i*tileSizeY+screen.getStartY(),
                        i*tileSizeY+screen.getStartY()+tileSizeY);
            }
        }
    }

    public void setNumberOtTiles(int width, int height) {
        if (this.width == width && this.height == height) { return; }
        if (width == 0 || height == 0) { return; }
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        calculateTileSize();
        generateTiles();
    }

    public int getStartX(int x, int y) {
        return grid[x][y].getStartX();
    }

    public int getStartY(int x, int y) {
        return grid[x][y].getStartY();
    }

    public int getEndX(int x, int y) {
        return grid[x][y].getEndX();
    }

    public int getEndY(int x, int y) {
        return grid[x][y].getEndY();
    }

    public int getTileWidth() {
        return tileSizeX;
    }

    public int getTileHeight() {
        return tileSizeY;
    }

    public boolean PositionIsInsideScreen(int x, int y) {
        return screen.positionInsideTile(x, y);
    }

    public int getTileIndex(int x, int y) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[j][i].positionInsideTile(x,y)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
