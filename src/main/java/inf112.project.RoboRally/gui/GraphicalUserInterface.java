package inf112.project.RoboRally.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.game.GameStatus;
import inf112.project.RoboRally.game.IGame;
import inf112.project.RoboRally.objects.*;

import java.util.List;

public class GraphicalUserInterface extends ApplicationAdapter{
    private IGame game;
    private int currentPlayerIndex;
    private IPlayer currentPlayer;
    private Grid cardScreen;
    private Grid boardScreen;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int CARD_SCREEN_WIDTH = 200;
    private static final int CARD_SCREEN_HEIGHT = HEIGHT;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private BitmapFont font;
    private BitmapFont fontGreen;
    private AssetsManagement assetsManager = new AssetsManagement();

    private int[] xPositionDrawer;
    private int[] yPositionDrawer;

    // to be moved
    private IDeck[] selectedCards;

    @Override
    public void create () {
        createNewGame();
        setupScreens();
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FillViewport(WIDTH, HEIGHT, camera);
        viewport.apply();
        Input input = new Input(camera);
        Gdx.input.setInputProcessor(input);
        loadTextures();

    }

    private void loadTextures() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        fontGreen = new BitmapFont();
        fontGreen.setColor(0,1,0,1);
        assetsManager.loadTextures();
        assetsManager.finishLoading();
    }

    private void setupScreens() {
        cardScreen = new Grid(
                new Tile(0,CARD_SCREEN_WIDTH,0,CARD_SCREEN_HEIGHT)
                ,1,currentPlayer.getCardsInHand().getSize());
        boardScreen = new Grid(
                new Tile(CARD_SCREEN_WIDTH,WIDTH,0,HEIGHT)
                ,game.getBoard().getColumns(),game.getBoard().getRows());
    }

    private void createNewGame() {
        game = new Game();
        game.initializeGame();
        game.dealOutProgramCards();
        game.setGameStatus(GameStatus.SELECT_CARDS);
        selectedCards = new Deck[game.getPlayers().size()];
        for (int i = 0; i < game.getPlayers().size(); i++) {
            selectedCards[i] = new Deck();
        }
        currentPlayerIndex = 0;
        currentPlayer = game.getPlayers().get(currentPlayerIndex);
        xPositionDrawer = new int[game.getPlayers().size()];
        yPositionDrawer = new int[game.getPlayers().size()];
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.currentPlayer = game.getPlayers().get(currentPlayerIndex);

        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        userInputs();
        drawBoard();
        drawCards();
        drawPlayers();
        batch.end();
    }


    private void userInputs() {
        if (Gdx.input.justTouched() && game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS) {
            int x = Gdx.input.getX();
            int y = HEIGHT-Gdx.input.getY();
            if (cardScreen.PositionIsInsideScreen(x,y)) {
                int index = cardScreen.getTileIndex(y);
                selectCards(index);
            }
        } else if (Gdx.input.justTouched()) {
            game.doTurn();
        }
    }

    private void selectCards(int index) {
        int playerDeckSize = currentPlayer.getCardsInHand().getSize();

        // if selected card is in selectedCards, then move the card to playerDeck and return
        if (index >= playerDeckSize) {
            ICard deSelectCard = selectedCards[currentPlayerIndex].removeCard(index-playerDeckSize);
            currentPlayer.getCardsInHand().addCardToDeck(deSelectCard);
            System.out.println("Player " + currentPlayer.getName() + " removed the card \n" + deSelectCard);
            return;
        }

        // remove selected card from playerDeck to selected cards
        ICard selectedCard = currentPlayer.getCardsInHand().getCardAtPosition(index);
        System.out.println("Player " + currentPlayer.getName() + " selected the card \n" + selectedCard);
        selectedCards[currentPlayerIndex].addCardToDeckAtPosition(0,currentPlayer.getCardsInHand().removeCard(index));

        // if five cards selected and there are more players left, switch current player to be next player
        if (game.getPlayers().size()-1 > currentPlayerIndex && selectedCards[currentPlayerIndex].getSize() >= 5) {
            currentPlayerIndex++;
        } else if (selectedCards[currentPlayerIndex].getSize() >= 5) { // done, all cards for all players is selected
            game.setUpTurn(selectedCards);
            currentPlayerIndex = 0;
            game.setGameStatus(GameStatus.EXECUTING_INSTRUCTIONS);

        }
    }

    private void drawCards() {
        switch (game.getTheCurrentGameStatus()) {
            case SELECT_CARDS:
                drawSelectCards();
                break;
            case EXECUTING_INSTRUCTIONS:
                break;
        }
    }

    private void drawSelectCards() {
        int fontSize = 30;
        int playerCards = currentPlayer.getCardsInHand().getSize();
        int selectedCards = this.selectedCards[currentPlayerIndex].getSize();
        int totalCards = playerCards + selectedCards;

        cardScreen.setNumberOtTiles(1,totalCards);
        int offset = (cardScreen.getTileHeight()-fontSize)/2;

        int i = 0;

        // drawing cards in playerDeck
        for (; i < playerCards; i++) {
            font.draw(batch,currentPlayer.getCardsInHand().showCard(i),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);

        }
        // drawing selected cards
        for (; i < totalCards; i++) {
            fontGreen.draw(batch, this.selectedCards[currentPlayerIndex].showCard(i-playerCards),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);

        }
    }

    private void drawPlayers() {
        List<IPlayer> players= game.getPlayers();
        int animationSpeed = 4;
        for (int i = 0; i < players.size(); i++) {
            int xPosPlayer = boardScreen.getStartX(players.get(i).getX());
            if (xPositionDrawer[i] != xPosPlayer) {
                if (xPositionDrawer[i] < xPosPlayer) {
                    xPositionDrawer[i] = xPositionDrawer[i]+animationSpeed > xPosPlayer ?
                            xPosPlayer : xPositionDrawer[i]+animationSpeed;
                } else {
                    xPositionDrawer[i] = xPositionDrawer[i]-animationSpeed < xPosPlayer ?
                            xPosPlayer : xPositionDrawer[i]-animationSpeed;
                }
            }
            int yPosPlayer = boardScreen.getStartY(players.get(i).getY());
            if (yPositionDrawer[i] != yPosPlayer) {
                if (yPositionDrawer[i] < yPosPlayer) {
                    yPositionDrawer[i] = yPositionDrawer[i]+animationSpeed > yPosPlayer ?
                            yPosPlayer : yPositionDrawer[i]+animationSpeed;
                } else {
                    yPositionDrawer[i] = yPositionDrawer[i]-animationSpeed < yPosPlayer ?
                            yPosPlayer : yPositionDrawer[i]-animationSpeed;
                }
            }
            batch.draw(assetsManager.getAssetFileName(players.get(i).getTexture()),
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
            }
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        assetsManager.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

}
