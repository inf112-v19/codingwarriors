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
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.game.IGame;
import inf112.project.RoboRally.objects.ConveyorBelt;
import inf112.project.RoboRally.objects.Floor;
import inf112.project.RoboRally.objects.GridDirection;
import inf112.project.RoboRally.objects.IObjects;

import java.util.ArrayList;

public class GraphicalUserInterface extends ApplicationAdapter {
    private IGame game;
    private GameBoard board; // to to moved to game
    private IDeck deck; // to to moved to game
    private IDeck playerDeck; // to to moved to Player
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
            ".r.............."; // to to moved to game

    private final String level2 =
            "2C3R" +
            "r.." +
            "r.."; // to to moved to game

    @Override
    public void create () {
        game = new Game();
        game.initializeGame();
        game.dealOutProgramCards();
        currentPlayerIndex = 0;
        currentPlayer = game.getPlayers().get(currentPlayerIndex);
        board = new GameBoard(level1); // to to moved to game
        deck = new Deck(); // to to moved to game
        deck.createProgramCardsDeck(); // to to moved to game
        deck.shuffle(); // to to moved to game
        playerDeck = deck.handOutCards(9); // to to moved to game
        cardScreen = new Grid(
                new Tile(0,CARD_SCREEN_WIDTH,0,CARD_SCREEN_HEIGHT)
                ,1,playerDeck.getSize());
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        batch = new SpriteBatch();
        font = new BitmapFont();
        fontGreen = new BitmapFont();
        fontGreen.setColor(0,1,0,1);
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

        this.currentPlayer = game.getPlayers().get(currentPlayerIndex);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBoard(gridSize, cardsSectionSize);
        drawPlayers(gridSize, cardsSectionSize);
        drawCards();
        userInputs();
        batch.end();

    }


    private void userInputs() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = HEIGHT-Gdx.input.getY();
            if (cardScreen.PositionIsInsideScreen(x,y)) {
                int index = cardScreen.getTileIndex(x,y);
                System.out.println(currentPlayer.getCardsInHand().showCard(index));
                currentPlayer.movePlayer(currentPlayer.getCardsInHand().getCardAtPosition(index));
                currentPlayerIndex++;
                if (currentPlayerIndex >= game.getPlayers().size()) {
                    currentPlayerIndex = 0;
                }

            }
        }
    }

    private void drawCards() {
        playerDeck = currentPlayer.getCardsInHand();
        int fontSize = 30;
        cardScreen.setNumberOtTiles(1,playerDeck.getSize());
        int offset = (cardScreen.getTileHeight()-fontSize)/2;
        for (int i = 0; i < playerDeck.getSize(); i++) {
            font.draw(batch,playerDeck.showCard(i),
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
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    /*
    public IPlayer getNextPlayer() {
        return nextPlayer;
    }
    */
}
