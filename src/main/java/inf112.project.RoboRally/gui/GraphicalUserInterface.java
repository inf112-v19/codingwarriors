package inf112.project.RoboRally.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.project.RoboRally.Board.GameBoard;
import inf112.project.RoboRally.objects.ConveyorBelt;
import inf112.project.RoboRally.objects.Floor;
import inf112.project.RoboRally.objects.IObjects;

public class GraphicalUserInterface extends ApplicationAdapter {
    private GameBoard board;
    private OrthographicCamera camera;
    private Viewport viewport;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private SpriteBatch batch;
    private Texture floor;
    private Texture arrow;
    private Texture player;
    private Texture card;


    private final String level1 =
            "12C16R" +
            ".rr..r.rrr......" +
            ".rrrrrrr........" +
            ".r.............." +
            ".r.............." +
            ".r.............." +
            "rr.............." +
            "rr.............." +
            ".r.............." +
            ".r.............." +
            ".r.............." +
            ".r.............." +
            ".r..............";

    private final String level2 =
            "2C3R" +
            "r.." +
            "r..";

    @Override
    public void create () {
        board = new GameBoard(level1);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        batch = new SpriteBatch();
        floor = new Texture("metalFloor.jpg");
        arrow = new Texture("arrow.png");
        player = new Texture("robotBrawlBot.png");
        card = new Texture("card back blue.png");
    }

    @Override
    public void render () {
        int cardsSectionSize = 200;
        double width = viewport.getWorldWidth();
        double height = viewport.getWorldHeight();
        int gridSize = (int)Math.min(height/board.getColums(),(width-cardsSectionSize)/board.getRows());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBoard(gridSize, cardsSectionSize);
        drawPlayers(gridSize, cardsSectionSize);
        drawCards(cardsSectionSize,height);
        batch.end();

    }

    private void drawCards(int cardsSectionSize, double height) {
        int gridSizeX = cardsSectionSize-20;
        int gridSizeY = (int)height/6;
        int y = gridSizeY/6;
        for (int i = 0; i < 5; i++) {
            batch.draw(card,10,y,gridSizeX,gridSizeY);
            y+=gridSizeY+gridSizeY/6;
        }
    }

    private void drawPlayers(int gridSize, int startX) {
        batch.draw(player,startX,0,gridSize,gridSize);
    }

    private void drawBoard(int gridSize, int startX) {
        int offset = 1;

        for (int y = 0, i = board.getColums()-1; i >= 0; y += gridSize, i--) {
            for (int x = startX, j = 0; j < board.getRows(); x += gridSize, j++) {
                IObjects object = board.getObject(j,i);
                if (object instanceof Floor) {
                    batch.draw(floor,x+offset, y+offset, gridSize-offset*2, gridSize-offset*2);
                } else if (object instanceof ConveyorBelt) {
                    batch.draw(floor,x+offset, y+offset, gridSize-offset*2, gridSize-offset*2);
                    batch.draw(arrow,x+offset, y+offset, gridSize-offset*2, gridSize-offset*2);
                }
            }
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        floor.dispose();
        arrow.dispose();
        player.dispose();
        card.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

}
