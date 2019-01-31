package inf112.project.RoboRally.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicalUserInterface extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture floor;
    private Texture arrow;
    private Texture player;

    String level1 = "....." +
            "....." +
            "..r.." +
            ".....";

    // GameBoard board = new GameBoard(5, 5, level1);

    @Override
    public void create () {
        batch = new SpriteBatch();
        floor = new Texture("metalFloor.jpg");
        arrow = new Texture("arrow.png");
        player = new Texture("robotBrawlBot.png");
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int gridHeight = 100;
        int gridWidth = 100;

        batch.begin();

        for (int i = 0, y = 0; i < height; i += gridHeight, y++) {
            for (int j = 0, x = 0; j < width; j += gridWidth, x++) {
                batch.draw(floor,j+2,i+2,96,96);
                // batch.draw(board.getObject(x,y).getTexture(),j + 2, i + 2, 96, 96);
            }
        }


        batch.end();

    }

    @Override
    public void dispose () {
        batch.dispose();
        floor.dispose();
        arrow.dispose();
        player.dispose();
    }

}
