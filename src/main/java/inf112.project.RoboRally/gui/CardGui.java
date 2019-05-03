package inf112.project.RoboRally.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.game.GameStatus;
import inf112.project.RoboRally.game.IGame;

public class CardGui {
    private int width;
    private int height;
    private IGame game;
    private Grid cardScreen;
    private SpriteBatch cardBatch;
    private int currentPlayerIndex;
    private IPlayer currentPlayer;
    private BitmapFont font; // move to AssetsManagement
    private BitmapFont fontGreen; // move to AssetsManagement
    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private Skin skin;
    TextButton powerDown;
    TextButton confirmSelection;
    boolean PowerSelectionDone;

    CardGui(IGame game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
        currentPlayer = game.getActivePlayers().get(0);
        currentPlayerIndex = 0;
        shapeRenderer = new ShapeRenderer();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        PowerSelectionDone = false;
        setUpTextures();
        setUpScreen();
        loadButtons();
    }

    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    private void setUpTextures() {
        cardBatch = new SpriteBatch();
        font = new BitmapFont();
        fontGreen = new BitmapFont();
        fontGreen.setColor(0,1,0,1);
    }

    private void setUpScreen() {
        cardScreen = new Grid(
                new Tile(0,width,50,height-200)
                ,1,currentPlayer.getCardsInHand().getSize());
    }

    void draw() {
        setBackground();
        switch (game.getTheCurrentGameStatus()) {
            case SELECT_CARDS:
                cardBatch.begin();
                currentPlayer = game.getActivePlayers().get(currentPlayerIndex);

                    displayPlayerData(height - 10, currentPlayer);
                    drawSelectCards();

                    incrementCurrentPlayer();

                cardBatch.end();
                break;
            case SELECT_POWER_STATUS:
                if (currentPlayer.poweringDownNextTurn()) {
                    cardBatch.begin();
                    displayPlayerData(height-10, currentPlayer);
                    cardBatch.end();
                }
                break;
            default:
                cardBatch.begin();
                drawExecutingCards();
                cardBatch.end();
                break;
        }
    }

    private void setBackground() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(
                currentPlayer.getColor().r/5
                ,currentPlayer.getColor().g/5
                ,currentPlayer.getColor().b/5
                ,200/255f);
        shapeRenderer.rect(0,0,width,height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawExecutingCards() {
        // cardScreen.setNumberOtTiles(1,game.getActivePlayers().size());
        int fontSize = 30;
        int offset = (cardScreen.getTileHeight()-fontSize)/2;
        for (int i = 0; i < game.getActivePlayers().size(); i++) {
            ICard lastPlayedCard = game.getActivePlayers().get(i).getLastPlayedCard();
            if (lastPlayedCard == null) { continue; }
            font.setColor(game.getActivePlayers().get(i).getColor());
            font.draw(cardBatch, lastPlayedCard.toString(),
                    cardScreen.getStartX(0), cardScreen.getEndY(i) - offset, cardScreen.getTileWidth(),
                    1, true);
            font.setColor(Color.WHITE);

        }
    }

    public void displayPlayerData(int StartingPositionYAxis, IPlayer player) {
        font.setColor(player.getColor());
        font.draw(cardBatch,player.toString(),
                5,StartingPositionYAxis, cardScreen.getTileWidth(),
                -1, true);
        font.setColor(Color.WHITE);
    }

    private void drawSelectCards() {
        if (game.getNumberOfPlayersLeftInTheGame() <= 0) {
            System.out.println("No players left");
            return;
        }

        int fontSize = 30;
        int playerCardsSize = currentPlayer.getCardsInHand().getSize();
        int registerCardsSize = currentPlayer.numberOfCardsInUnlockedRegister();
        int totalCards = playerCardsSize + registerCardsSize;

        cardScreen.setNumberOtTiles(1,totalCards);
        int offset = (cardScreen.getTileHeight()-fontSize)/2;

        int i = 0;

        // drawing cards in playerDeck
        for (; i < playerCardsSize; i++) {
            font.draw(cardBatch,currentPlayer.getCardsInHand().showCard(i),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);

        }
        // drawing selected cards
        for (; i < totalCards; i++) {
            font.setColor(currentPlayer.getColor());
            font.draw(cardBatch, currentPlayer.revealProgramCardForRegisterNumber(i-playerCardsSize).toString(),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);
            font.setColor(Color.WHITE);

        }
    }

    public void loadButtons() {

        powerDown = new TextButton("PowerDown", skin);
        powerDown.setPosition(5, height-200);
        powerDown.setSize(190, 45);

        powerDown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS && currentPlayer.getPlayerDamage() !=0) {
                    currentPlayer.reversePowerDownStatus();
                    System.out.println(currentPlayer.getName() + " power down status is now " + currentPlayer.poweringDownNextTurn());
                } else if (game.getTheCurrentGameStatus() == GameStatus.SELECT_POWER_STATUS && currentPlayer.poweringDownNextTurn()) {
                    currentPlayer.reversePowerDownStatus();
                    System.out.println(currentPlayer.getName() + " power down status is now " + currentPlayer.poweringDownNextTurn());
                    currentPlayerIndex = currentPlayerIndex >= (game.getActivePlayers().size() - 1) ? 0 : ++currentPlayerIndex;
                    currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
                    if (currentPlayerIndex == 0) {
                        PowerSelectionDone = true;
                        game.setGameStatus(GameStatus.FINISHING_UP_THE_TURN);
                    }
                }
            }
        });

        confirmSelection = new TextButton("Confirm card selection", skin);
        confirmSelection.setPosition(5, 5);
        confirmSelection.setSize(190, 45);
        confirmSelection.setColor(Color.RED);

        confirmSelection.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS && currentPlayer.registerIsFull()
                        || game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS && currentPlayer.getCardsInHand().isEmpty()) {
                    currentPlayer.setCardSelectionConfirmedStatus(true);
                    incrementCurrentPlayer();
                } else if (game.getTheCurrentGameStatus() == GameStatus.SELECT_CARDS){
                    System.out.println("Not enough selected cards");
                } else if (game.getTheCurrentGameStatus() == GameStatus.SELECT_POWER_STATUS && currentPlayer.poweringDownNextTurn()) {
                    currentPlayerIndex = currentPlayerIndex >= (game.getActivePlayers().size() - 1) ? 0 : ++currentPlayerIndex;
                    currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
                    if (currentPlayerIndex == 0) {
                        PowerSelectionDone = true;
                        game.setGameStatus(GameStatus.FINISHING_UP_THE_TURN);
                    }
                }
            }
        });

        stage.addActor(powerDown);
        stage.addActor(confirmSelection);

    }

    public void updateButtons() {
        if (game.getTheCurrentGameStatus() == GameStatus.SELECT_POWER_STATUS) {
            powerDown.setText("Press to power up");
            powerDown.setColor(Color.RED);
        } else if (currentPlayer.poweringDownNextTurn() == true) {
            powerDown.setColor(Color.RED);
            powerDown.setText("Power is down");
        } else if (currentPlayer.getPlayerDamage() == 0) {
            powerDown.setColor(Color.CLEAR);
        } else {
            powerDown.setColor(Color.GREEN);
            powerDown.setText("Power is on");
        }
        // confirm button
        if (currentPlayer.registerIsFull() || currentPlayer.getCardsInHand().isEmpty()) {
            confirmSelection.setText("Confirm selection");
            confirmSelection.setColor(Color.WHITE);
        } else if (game.getTheCurrentGameStatus() == GameStatus.SELECT_POWER_STATUS && currentPlayer.poweringDownNextTurn()) {
            confirmSelection.setText("Stay powered down");
            confirmSelection.setColor(Color.WHITE);
        } else {
            confirmSelection.setText("Select cards");
            confirmSelection.setColor(Color.RED);
        }
    }


    void selectCards(int indexOfSelectedCard) {
        currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
        IDeck playersDeckOfCards = currentPlayer.getCardsInHand();
        // Switch selected card between players deck,
        // and the players list of selected cards.
        if (playersDeckOfCards.isEmpty() || currentPlayer.isPoweredDown()) {
            incrementCurrentPlayer();
        } else if (indexOfSelectedCard >= playersDeckOfCards.getSize()) {
            System.out.println("deck index: " + indexOfSelectedCard);
            System.out.println("deck size: " + playersDeckOfCards.getSize());
            moveSelectedCardBackToPlayersDeck(indexOfSelectedCard);
        } else {
            moveSelectedCardToPlayersListOfSelectedCards(indexOfSelectedCard);
        }
    }

    public void incrementCurrentPlayer() {
        int indexOfTheLastPlayer = (game.getActivePlayers().size() - 1);
        if (currentPlayer.registerIsFull() && currentPlayer.cardSelectionConfirmed()
                || currentPlayer.getCardsInHand().isEmpty() && currentPlayer.cardSelectionConfirmed()
                || currentPlayer.isPoweredDown()) {

            currentPlayer.setCardSelectionConfirmedStatus(false);
            if (currentPlayerIndex == indexOfTheLastPlayer) {
                currentPlayerIndex = 0;
                game.setGameStatus(GameStatus.EXECUTING_INSTRUCTIONS);
                System.out.println("finished selecting cards");
            } else {
                System.out.println("updating current player");
                currentPlayerIndex++;
            }
        }
        currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
    }

    /**
     * Remove the selected card from the players hand,
     * and add it to the players deck of selected cards.
     */
    private void moveSelectedCardToPlayersListOfSelectedCards(int index) {
        IDeck playersDeckOfCards = currentPlayer.getCardsInHand();
        ICard selectedCard = playersDeckOfCards.removeCard(index);
        if (!currentPlayer.addACardToProgramRegister(selectedCard)) {
            System.out.println("No more room in register");
            playersDeckOfCards.addCardToDeck(selectedCard);
        }
        System.out.println("Player " + currentPlayer.getName() + " selected the card:" + selectedCard.getCardCommand());
    }

    /**
     * Remove the selected card from the players deck of selected cards,
     * and add it to the players hand.
     */
    private void moveSelectedCardBackToPlayersDeck(int index) {
        IDeck playersDeckOfCards = currentPlayer.getCardsInHand();
        int positionOfCardToRemove = index - playersDeckOfCards.getSize();
        ICard deSelectedCard = currentPlayer.removeACardFromProgramRegisterAtSlotNumber((positionOfCardToRemove));
        playersDeckOfCards.addCardToDeck(deSelectedCard);
        System.out.println("Player " + currentPlayer.getName() + " removed the card" + deSelectedCard.getCardCommand());
    }

    public void userInputs(int x, int y) {
        if (cardScreen.PositionIsInsideScreen(x,y)) {
            int index = cardScreen.getTileIndex(y);
            selectCards(index);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.clear();
        stage.dispose();
        font.dispose();
        skin.dispose();
    }

    public void selectPowerStatus() {
        if (game.getActivePlayers().size() == 0) {
            PowerSelectionDone = true;
            game.setGameStatus(GameStatus.FINISHING_UP_THE_TURN);
            return;
        }
        while (!currentPlayer.poweringDownNextTurn()) {
            currentPlayerIndex = currentPlayerIndex >= (game.getActivePlayers().size() - 1) ? 0 : ++currentPlayerIndex;
            currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
            if (currentPlayerIndex == 0) {
                PowerSelectionDone = true;
                game.setGameStatus(GameStatus.FINISHING_UP_THE_TURN);
                return;
            }
        }
    }
}

