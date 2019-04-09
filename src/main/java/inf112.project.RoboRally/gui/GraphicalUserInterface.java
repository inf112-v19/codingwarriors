package inf112.project.RoboRally.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.project.RoboRally.actors.AI;
import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.game.GameStatus;
import inf112.project.RoboRally.game.IGame;
import inf112.project.RoboRally.objects.*;

import java.util.List;
import java.util.Random;

public class GraphicalUserInterface extends ApplicationAdapter {
    private IGame game;
    private Grid boardScreen;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int CARD_SCREEN_WIDTH = 200;
    private static final int CARD_SCREEN_HEIGHT = HEIGHT;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SpriteBatch GameOverBatch;
    private Viewport viewport;
    private AssetsManagement assetsManager = new AssetsManagement();
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private int[] xPositionDrawer;
    private int[] yPositionDrawer;

    private CardGui cardGui;

    // to be moved
    //private IDeck[] selectedCards;

    @Override
    public void create () {
        createNewGame();
        setupScreens();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        GameOverBatch = new SpriteBatch();
        cardGui = new CardGui(game,CARD_SCREEN_WIDTH,CARD_SCREEN_HEIGHT);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FillViewport(WIDTH, HEIGHT, camera);
        viewport.apply();
        viewport.update(WIDTH, HEIGHT, true);
        Input input = new Input(camera);
        Gdx.input.setInputProcessor(input);
        loadTextures();

    }

    private void loadTextures() {
        batch = new SpriteBatch();
        assetsManager.loadTextures();
        assetsManager.finishLoading();
    }

    private void setupScreens() {
        boardScreen = new Grid(
                new Tile(CARD_SCREEN_WIDTH,WIDTH,0,HEIGHT)
                ,game.getBoard().getColumns(),game.getBoard().getRows());
    }

    private void createNewGame() {
        game = new Game();
        game.setGameStatus(GameStatus.SELECT_CARDS);
        xPositionDrawer = new int[game.getPlayers().size()];
        yPositionDrawer = new int[game.getPlayers().size()];
    }

    @Override
    public void render () {
        if (game.gameOver()) {
            GameOverBatch.begin();
            drawGameOverScreen();
            GameOverBatch.end();
            if (Gdx.input.justTouched()) {
                create();
            }
        } else {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            userInputs();
            drawBoard();
            drawLasers();
            drawPlayers();
            batch.end();

            cardGui.draw();
        }

    }

    private void drawGameOverScreen() {
        GameOverBatch.draw(assetsManager.getAssetFileName("assets/GameOver.png"),
                WIDTH/3, HEIGHT/2,
                WIDTH/2, HEIGHT/4);


    }

    private void userInputs() {
        if ((game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS) && (cardGui.getCurrentPlayer() instanceof AI)) {
            cardGui.selectCards(0);
            }
        else if (Gdx.input.justTouched()) {
            if (game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS) {
                    int x = Gdx.input.getX();
                    int y = HEIGHT - Gdx.input.getY();
                    cardGui.userInputs(x, y);
            } else {
                game.doTurn();
            }
        }
    }



    private void drawPlayers() {
        List<IPlayer> players = game.getPlayers();
        int animationSpeed = 9;
        for (int i = 0; i < players.size(); i++) {
            IPlayer player = players.get(i);
            if (!game.getActivePlayers().contains(player)) {
                continue;
            }
            if (!game.getBoard().moveValid(player.getX(),player.getY())) {
                continue;
            }
            int xPosPlayer = boardScreen.getStartX(player.getX());
            if (xPositionDrawer[i] != xPosPlayer) {
                if (xPositionDrawer[i] < xPosPlayer) {
                    xPositionDrawer[i] = xPositionDrawer[i]+animationSpeed > xPosPlayer ?
                            xPosPlayer : xPositionDrawer[i]+animationSpeed;
                } else {
                    xPositionDrawer[i] = xPositionDrawer[i]-animationSpeed < xPosPlayer ?
                            xPosPlayer : xPositionDrawer[i]-animationSpeed;
                }
            }
            int yPosPlayer = boardScreen.getStartY(player.getY());
            if (yPositionDrawer[i] != yPosPlayer) {
                if (yPositionDrawer[i] < yPosPlayer) {
                    yPositionDrawer[i] = yPositionDrawer[i]+animationSpeed > yPosPlayer ?
                            yPosPlayer : yPositionDrawer[i]+animationSpeed;
                } else {
                    yPositionDrawer[i] = yPositionDrawer[i]-animationSpeed < yPosPlayer ?
                            yPosPlayer : yPositionDrawer[i]-animationSpeed;
                }
            }
            batch.setColor(player.getColor());
            batch.draw(assetsManager.getAssetFileName("assets/player_color_" + player.getPlayerDirection().toString().toLowerCase() + ".png")
                    ,xPositionDrawer[i],yPositionDrawer[i],
                    boardScreen.getTileWidth(), boardScreen.getTileHeight());
            batch.setColor(Color.WHITE);
            batch.draw(assetsManager.getAssetFileName(player.getTexture()),
                    xPositionDrawer[i], yPositionDrawer[i],
                    boardScreen.getTileWidth(), boardScreen.getTileHeight());

        }
    }

    private void drawBoard() {
        int offset = 1;
        for (int j = 0; j < boardScreen.getHeight(); j++) {
            for (int i = 0; i < boardScreen.getWidth(); i++) {
                IObjects object = game.getBoard().getObject(i,j);
                String texture = object.getTexture();
                batch.draw(assetsManager.getAssetFileName("assets/floor_metal.jpg"),
                        boardScreen.getStartX(i)+offset, boardScreen.getStartY(j)+offset,
                        boardScreen.getTileWidth()-offset*2, boardScreen.getTileHeight()-offset*2);
                if (texture != null) {
                    batch.draw(assetsManager.getAssetFileName(object.getTexture()),
                            boardScreen.getStartX(i) + offset, boardScreen.getStartY(j) + offset,
                            boardScreen.getTileWidth() - offset * 2, boardScreen.getTileHeight() - offset * 2);
                }
                if (object.hasWalls()) {
                    batch.draw(assetsManager.getAssetFileName(object.getWallTexture()),
                            boardScreen.getStartX(i) + offset, boardScreen.getStartY(j) + offset,
                            boardScreen.getTileWidth() - offset * 2, boardScreen.getTileHeight() - offset * 2);
                }
            }
        }
    }

    private void drawLasers() {
       if (game.getTheCurrentGameStatus().equals(GameStatus.FIRING_LASERS)) {
            for (Laser laser : game.getLasers()) {
                if (!laser.hasPlayer()
                        ||(laser.hasPlayer()
                        && game.checkIfThePlayerIsOperational(laser.getPlayer()))) {
                    List<Coordinates> coordinates = game.getLaserPath(laser.getCoordinates(), laser.getDirection(), laser);
                    for (int i = 0; i < coordinates.size(); i++) {
                        if (laser.hasPlayer() && i == 0) continue;
                        batch.draw(assetsManager.getAssetFileName(laser.getTexture()),
                                boardScreen.getStartX(coordinates.get(i).getX()), boardScreen.getStartY(coordinates.get(i).getY()),
                                boardScreen.getTileWidth(), boardScreen.getTileHeight());
                    }
                }
            }
       }
    }

    @Override
    public void dispose () {
        batch.dispose();
        assetsManager.dispose();
    }

    @Override
    public void resize(int width, int height) {
        // viewport.update(width, height, true);
    }
}
