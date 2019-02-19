package inf112.project.RoboRally.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.game.IGame;
import inf112.project.RoboRally.objects.ConveyorBelt;
import inf112.project.RoboRally.objects.Floor;
import inf112.project.RoboRally.objects.IObjects;

import java.util.ArrayList;

public class GraphicalUserInterface extends ApplicationAdapter {
    private IGame game;
    private int currentPlayerIndex;
    private IPlayer currentPlayer;
    private Grid cardScreen;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int CARD_SCREEN_WIDTH = 200;
    private static final int CARD_SCREEN_HEIGHT = HEIGHT;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private BitmapFont font;
    private BitmapFont fontGreen;
    private Texture player;
    private Texture floor;
    private Texture arrow;
    private Texture card;


    @Override
    public void create () {
        createNewGame();
        setupScreens();
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        loadTextures();

    }

    private void loadTextures() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        fontGreen = new BitmapFont();
        fontGreen.setColor(0,1,0,1);
        floor = new Texture("metalFloor.jpg");
        arrow = new Texture("arrow.png");
        player = new Texture("robotBrawlBot.png");
        card = new Texture("card back blue.png");
    }

    private void setupScreens() {
        cardScreen = new Grid(
                new Tile(0,CARD_SCREEN_WIDTH,0,CARD_SCREEN_HEIGHT)
                ,1,currentPlayer.getCardsInHand().getSize());
    }

    private void createNewGame() {
        game = new Game();
        game.initializeGame();
        game.dealOutProgramCards();
        currentPlayerIndex = 0;
        currentPlayer = game.getPlayers().get(currentPlayerIndex);
    }

    @Override
    public void render () {
        double width = viewport.getWorldWidth();
        double height = viewport.getWorldHeight();
        int cardsSectionSize = 200;
        int gridSize = (int)Math.min(height/game.getBoard().getColums(),(width-cardsSectionSize)/game.getBoard().getRows());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.currentPlayer = game.getPlayers().get(currentPlayerIndex);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        userInputs();
        drawBoard(gridSize, cardsSectionSize);
        drawCards();
        drawPlayers(gridSize, cardsSectionSize);
        batch.end();
    }


    private void userInputs() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = HEIGHT-Gdx.input.getY();
            if (cardScreen.PositionIsInsideScreen(x,y)) {
                int index = cardScreen.getTileIndex(x,y);
                currentPlayer.movePlayer(currentPlayer.getCardsInHand().getCardAtPosition(index));
                currentPlayerIndex++;
                if (currentPlayerIndex >= game.getPlayers().size()) {
                    currentPlayerIndex = 0;
                }
            }
        }
    }

    private void drawCards() {
        int fontSize = 30;
        cardScreen.setNumberOtTiles(1,currentPlayer.getCardsInHand().getSize());
        int offset = (cardScreen.getTileHeight()-fontSize)/2;
        for (int i = 0; i < currentPlayer.getCardsInHand().getSize(); i++) {
            font.draw(batch,currentPlayer.getCardsInHand().showCard(i),
                    cardScreen.getStartX(0,i),cardScreen.getEndY(0,i)-offset, cardScreen.getTileWidth(),
                    1, true);
        }
    }

    private void drawPlayers(int gridSize, int startX) {
        ArrayList<IPlayer> players= game.getPlayers();
        for (IPlayer player: players) {
            batch.draw(this.player,player.getX()*gridSize+startX,player.getY()*gridSize,gridSize,gridSize);
        }

    }

    private void drawBoard(int gridSize, int startX) {
        int offset = 1;

        for (int y = 0, i = game.getBoard().getColums()-1; i >= 0; y += gridSize, i--) {
            for (int x = startX, j = 0; j < game.getBoard().getRows(); x += gridSize, j++) {
                IObjects object = game.getBoard().getObject(j,i);
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
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

}
