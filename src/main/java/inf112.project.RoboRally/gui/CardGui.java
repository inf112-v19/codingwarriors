package inf112.project.RoboRally.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    CardGui(IGame game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
        currentPlayer = game.getActivePlayers().get(0);
        currentPlayerIndex = 0;
        shapeRenderer = new ShapeRenderer();
        setUpTextures();
        setUpScreen();
    }

    private void setUpTextures() {
        cardBatch = new SpriteBatch();
        font = new BitmapFont();
        fontGreen = new BitmapFont();
        fontGreen.setColor(0,1,0,1);
    }

    private void setUpScreen() {
        cardScreen = new Grid(
                new Tile(0,width,0,height)
                ,1,currentPlayer.getCardsInHand().getSize());
    }

    void draw() {
        setBackground();
        switch (game.getTheCurrentGameStatus()) {
            case SELECT_CARDS:
                cardBatch.begin();
                drawSelectCards();
                cardBatch.end();
                break;
            case EXECUTING_INSTRUCTIONS:
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
        cardScreen.setNumberOtTiles(1,game.getActivePlayers().size());
        int fontSize = 30;
        int offset = (cardScreen.getTileHeight()-fontSize)/2;
        for (int i = 0; i < game.getActivePlayers().size(); i++) {
            font.setColor(game.getActivePlayers().get(i).getColor());
            font.draw(cardBatch,game.getActivePlayers().get(i).revealProgramCardForRegisterNumber(game.getCurrentSlotNumber()).toString(),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);
            font.setColor(Color.WHITE);
        }
    }

    private void drawSelectCards() {
        IDeck[] selectedCards = game.getSelectedCards();
        if (game.getNumberOfPlayersLeftInTheGame() <= 0) {
            System.out.println("No players left");
            return;
        }

        //   System.out.println("current player: " + currentPlayerIndex);
        //   System.out.println("players hand size: " + currentPlayer.getCardsInHand().getSize());
        //   System.out.println("selectedCards.size: " + selectedCards[currentPlayerIndex].getSize());
        int fontSize = 30;
        int playerCards = currentPlayer.getCardsInHand().getSize();
        int amountOfSelectedCards = selectedCards[currentPlayerIndex].getSize();
        int totalCards = playerCards + amountOfSelectedCards;

        cardScreen.setNumberOtTiles(1,totalCards);
        int offset = (cardScreen.getTileHeight()-fontSize)/2;

        int i = 0;

        // drawing cards in playerDeck
        for (; i < playerCards; i++) {
            font.draw(cardBatch,currentPlayer.getCardsInHand().showCard(i),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);

        }
        // drawing selected cards
        for (; i < totalCards; i++) {
            font.setColor(currentPlayer.getColor());
            font.draw(cardBatch, selectedCards[currentPlayerIndex].showCard(i-playerCards),
                    cardScreen.getStartX(0),cardScreen.getEndY(i)-offset, cardScreen.getTileWidth(),
                    1, true);
            font.setColor(Color.WHITE);

        }
    }

    void selectCards(int indexOfSelectedCard) {
        currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
        System.out.println("Entering select cards for " + currentPlayer.getName());
        IDeck playersDeckOfCards = currentPlayer.getCardsInHand();
        if (playersDeckOfCards.isEmpty()) {
            System.out.println("No more cards left for  " + currentPlayer.getName());
            System.out.println("current index  " + currentPlayerIndex + " active player size " + (game.getActivePlayers().size() - 1));
            currentPlayerIndex = currentPlayerIndex >= (game.getActivePlayers().size() - 1) ? 0 : ++currentPlayerIndex;
            currentPlayer = game.getActivePlayers().get(currentPlayerIndex);
            System.out.println("new index  " + currentPlayerIndex + " active player size " + (game.getActivePlayers().size() - 1));
            if (currentPlayerIndex == 0) {
                game.setGameStatus(GameStatus.EXECUTING_INSTRUCTIONS);
                System.out.println("finished selecting cards");
            }
            return;
        }
        // Switch selected card between players deck,
        // and the players list of selected cards.
        if (indexOfSelectedCard >= playersDeckOfCards.getSize()) {
            moveSelectedCardBackToPlayersDeck(indexOfSelectedCard);
            return; // Not finished selecting cards yet.
        } else {
            moveSelectedCardToPlayersListOfSelectedCards(indexOfSelectedCard);
        }

        IDeck[] selectedCards = game.getSelectedCards();
        int numberOfCardsToSelect = currentPlayer.getNumberOfUnlockedRegisterSlots();
        int numberOfSelectedCards = selectedCards[currentPlayerIndex].getSize();
        int indexOfTheLastPlayer = (game.getActivePlayers().size() - 1);
        if (numberOfSelectedCards >= numberOfCardsToSelect) {
            this.addTheSelectedCardsToTheCurrentPlayersProgramRegister();
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
     * Add this players chosen cards to this players register.
     */
    private void addTheSelectedCardsToTheCurrentPlayersProgramRegister() {
        IDeck[] selectedCards = game.getSelectedCards();
        IDeck chosenCards = selectedCards[currentPlayerIndex];
        currentPlayer.addADeckOfCardsToTheProgramRegister(chosenCards);
        selectedCards[currentPlayerIndex].removeAllCardsFromDeck();
    }

    /**
     * Remove the selected card from the players hand,
     * and add it to the players deck of selected cards.
     */
    private void moveSelectedCardToPlayersListOfSelectedCards(int index) {
        IDeck[] selectedCards = game.getSelectedCards();
        IDeck playersDeckOfCards = currentPlayer.getCardsInHand();
        ICard selectedCard = playersDeckOfCards.removeCard(index);
        int lastPos = selectedCards[currentPlayerIndex].getSize();
        selectedCards[currentPlayerIndex].addCardToDeckAtPosition(lastPos, selectedCard);
        System.out.println("Player " + currentPlayer.getName() + " selected the card \n" + selectedCard);
    }

    /**
     * Remove the selected card from the players deck of selected cards,
     * and add it to the players hand.
     */
    private void moveSelectedCardBackToPlayersDeck(int index) {
        IDeck[] selectedCards = game.getSelectedCards();
        IDeck playersDeckOfCards = currentPlayer.getCardsInHand();
        int positionOfCardToRemove = index - playersDeckOfCards.getSize();

        ICard deSelectedCard = selectedCards[currentPlayerIndex].removeCard(positionOfCardToRemove);
        playersDeckOfCards.addCardToDeck(deSelectedCard);
        System.out.println("Player " + currentPlayer.getName() + " removed the card \n" + deSelectedCard);
    }

    public void userInputs(int x, int y) {
        if (cardScreen.PositionIsInsideScreen(x,y)) {
            int index = cardScreen.getTileIndex(y);
            selectCards(index);
        }
    }
}

