package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;

import java.util.ArrayList;
import java.util.List;

import static inf112.project.RoboRally.game.GameStatus.*;


public class Game implements IGame {


    private IDeck programCards;
    private IDeck discardedProgramCards;
    private List<IPlayer> players; // All the participating players, ordered after player creation.
    // Certain events that prioritises starting order, may use this list for reference.
    // For example: players[0] has higher priority than players[1].
    private List<IPlayer> activePlayers; // Players that are not incapacitated.
    private GameBoard board;
    private int numberOfPlayersLeftInTheGame;
    private boolean everyFlagHasBeenVisited;
    private GameStatus currentGameStatus;
    private IPlayer currentlyActingPlayer; // The player whose cards are to be displayed.
    private IDeck[] selectedCards;
    private int currentSlotNumber;
    private final int NUMBER_OF_REGISTER_SLOTS = 5;



    @Override
    public void drawCards(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        List<ICard> drawnCards;
        int numberOfCardsToDraw = calculateTheNumberOfCardsThePlayerCanDraw(player);
        int numberOfCardsLeftInProgramCardsDeck = this.programCards.getSize();
        int numberOfCardsMissing = numberOfCardsToDraw -
                                        numberOfCardsLeftInProgramCardsDeck;
        if (numberOfCardsMissing > 0) {
            drawnCards = this.programCards.handOutNCards(numberOfCardsLeftInProgramCardsDeck);
            shuffleDiscardedProgramCardsIntoProgramCardsDeck();
            drawnCards.addAll(this.programCards.handOutNCards(numberOfCardsMissing));
        } else {
            drawnCards = this.programCards.handOutNCards(numberOfCardsToDraw);
        }
        player.addCardsToPlayersHand(drawnCards);
    }

    /**
     * Put all the discarded program cards from the discard pile,
     * into the main program cards deck,
     * and randomise the programCards deck.
     */
    private void shuffleDiscardedProgramCardsIntoProgramCardsDeck() {
        this.discardedProgramCards.transferNCardsFromThisDeckToTargetDeck(
                discardedProgramCards.getSize(),
                this.programCards);
        this.programCards.shuffle();
    }


    @Override
    public void dealOutProgramCards() {
        programCards.shuffle();
        for (IPlayer player : activePlayers) {
            int numberOfCardsPlayerCanDraw =
                    calculateTheNumberOfCardsThePlayerCanDraw(player);
            System.out.println("player receives " + numberOfCardsPlayerCanDraw + " cards");
            player.addCardsToPlayersHand(programCards.handOutNCards(numberOfCardsPlayerCanDraw));
        }
    }

    @Override
    public int calculateTheNumberOfCardsThePlayerCanDraw(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        int numberOfCards = 9; // The default and maximum number of cards
        // that can be dealt to a player.
        int playerDamage = player.getPlayerDamage(); // The number of damage tokens
        // the player has received.
        if (playerDamage >= 10) {
            return 0;
        }
        return (numberOfCards - playerDamage);
    }

    @Override
    public void initializeGame() {
        //TODO: Make initializeGame take a gameboard as parameter,
        // which can be chosen at the start menu?
        addPlayers();
        String gameBoardLayout = "16C12R" +
                "f....r.rrr...f.." +
                ".rrrrrrr....uu.." +
                ".r.........c...." +
                ".r...f....|....." +
                ".r......ll....p.." +
                "rr......f......." +
                "ll.....w....C..." +
                ".r..p....lll...." +
                ".r.....w........" +
                ".r.....w.....p.." +
                ".r...f....-....." +
                ".r....WW....dd..";
        this.board = new GameBoard(gameBoardLayout);
        this.programCards = new Deck();
        this.discardedProgramCards = new Deck();
        this.programCards.createProgramCardsDeck();
        this.everyFlagHasBeenVisited = false;
        this.numberOfPlayersLeftInTheGame = players.size();
        this.currentSlotNumber = 0;
    }

    @Override
    public void addPlayers() {
     // Hardcoded players for demonstration.
        IPlayer player1 = new Player("Buzz", 0, 10);
        IPlayer player2 = new Player("Emma", 5, 10);
        IPlayer player3 = new Player("G-bot", 2, 5);
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.activePlayers = new ArrayList<>();
        this.activePlayers.addAll(players);
    }

    @Override
    public List<IPlayer> getActivePlayers() {
        return activePlayers;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public GameStatus getTheCurrentGameStatus() {
        return this.currentGameStatus;
    }

    public GameBoard getBoard() {
        return board;
    }

    @Override
    public void setGameStatus(GameStatus status) {
        currentGameStatus = status;
    }

    @Override
    public void setCurrentSlotNumber(Integer number) {
        if (number == null
                || number < 0
                || number > this.NUMBER_OF_REGISTER_SLOTS) {
            throw new IllegalArgumentException("Not a valid number");
        }
        this.currentSlotNumber = number;
    }

    @Override
    public void doTurn() {
        switch (this.currentGameStatus) {
            case EXECUTING_INSTRUCTIONS:
                executingInstructions();
                return;
            case EXECUTING_GAME_BOARD_OBJECTS:
                executingGameBoardObjects();
        }

    }

    private void executingGameBoardObjects() {
        for (IPlayer player: players) {
            if(board.moveValid(player.getX(),player.getY())) {
                board.getObject(player.getX(), player.getY()).doAction(player);
            } else {
                player.respawnAtLastArchiveMarker();
            }
        }
        setGameStatus(EXECUTING_INSTRUCTIONS);

    }

    private void executingInstructions() {
        IDeck cardsForThisRegisterSlot = new Deck();
        ArrayList<IPlayer> listOfPlayers = new ArrayList<>(); // For keeping track of players and their cards.
        for (IPlayer player : this.activePlayers) {
            ICard programCard = player.revealProgramCardForRegisterNumber(currentSlotNumber);
            cardsForThisRegisterSlot.addCardToDeck(programCard);
            listOfPlayers.add(player);
        }
        this.sortCardsAfterPriority(cardsForThisRegisterSlot, listOfPlayers);

        for (int i = 0; i < listOfPlayers.size(); i++) {
            ICard card = cardsForThisRegisterSlot.getCardAtPosition(i);
            IPlayer player = listOfPlayers.get(i);
            player.movePlayer(card);
        }
        if (this.currentSlotNumber == (this.NUMBER_OF_REGISTER_SLOTS - 1)) {
            this.emptyEachPlayersRegister();
            this.setGameStatus(GameStatus.SELECT_CARDS);
            this.setupCardSelectionForNewRound();
            this.updateCurrentRegisterSlot();
            return;
        }
        updateCurrentRegisterSlot();
/*
        if (selectedCards[0].isEmpty()) {
            setupCardSelectionForNewRound();
            setGameStatus(GameStatus.SELECT_CARDS);

            return;
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(selectedCards[i].getSize() - 1); // <- TODO: remove this line
            ICard currentCard = selectedCards[i].removeCard(selectedCards[i].getSize() - 1);
            players.get(i).movePlayer(currentCard);
            discardedProgramCards.addCardToDeck(currentCard);
        }*/

        setGameStatus(EXECUTING_GAME_BOARD_OBJECTS);
    }

    /**
     * Move all unlocked cards from each players registry,
     * to the pile of discarded program cards.
     */
    private void emptyEachPlayersRegister() {
        for (IPlayer player : this.players) {
            IDeck cardsToBeDiscarded = player.clearRegister();
            cardsToBeDiscarded.transferNCardsFromThisDeckToTargetDeck(
                                        cardsToBeDiscarded.getSize(),
                                        this.discardedProgramCards);
        }
    }

    /**
     * Increment the current register slot number.
     * If it is too high, it is reset to zero.
     */
    private void updateCurrentRegisterSlot() {
        if (this.currentSlotNumber >= (this.NUMBER_OF_REGISTER_SLOTS - 1)) {
            this.setCurrentSlotNumber(0);
        } else {
            this.setCurrentSlotNumber(this.currentSlotNumber + 1);
        }
    }

    /**
     * Sort the deck of cards based on priority,
     * so that those with highest priority comes first.
     * <br><br>
     * The index of the cards and players,
     * determine which player the card belongs to.
     *
     * @param cardsForThisRegisterSlot
     *                              The deck of cards to sort.
     * @param listOfPlayers
     *                      The accompanying list of players.
     */
    private void sortCardsAfterPriority(IDeck cardsForThisRegisterSlot, ArrayList<IPlayer> listOfPlayers) {
        int numberOfCards = cardsForThisRegisterSlot.getSize() - 1; // -1 for proper index.
        for (int i = numberOfCards; i > 0; i--) {
            for (int j = 0; j < numberOfCards; j++) {
                if (i == j) {
                    break;
                }
                ICard card = cardsForThisRegisterSlot.getCardAtPosition(i);
                ICard otherCard = cardsForThisRegisterSlot.getCardAtPosition(j);
                if (card.compareTo(otherCard) > 0) {
                    this.swap(i, j, cardsForThisRegisterSlot, listOfPlayers);
                }
            }
        }
    }

    /**
     * Swap the positions of cards and players,
     * so that their indexes remain the same.
     *
     * @param i
     *          Index i.
     * @param j
     *          Index j.
     * @param cardsForThisRegisterSlot
     *                              The deck of cards.
     * @param listOfPlayers
     *                      The list of players.
     */
    private void swap(int i, int j, IDeck cardsForThisRegisterSlot, ArrayList<IPlayer> listOfPlayers) {
        cardsForThisRegisterSlot.swapCardsInPosition(i, j);
        IPlayer playerI = listOfPlayers.remove(i);
        IPlayer playerJ = listOfPlayers.remove(j);
        listOfPlayers.add(j, playerI);
        listOfPlayers.add(i, playerJ);
    }

    private void setupCardSelectionForNewRound() {
        // all un used cards is moved to discard pile
        for (IPlayer player: players) {
            IDeck cardsInPlayerDeck = player.getCardsInHand();
            cardsInPlayerDeck.transferNCardsFromThisDeckToTargetDeck(cardsInPlayerDeck.getSize(),
                                                                            discardedProgramCards);
        }
        // new cards is dealt
        for (IPlayer player: players) {
            drawCards(player);

            /*

            IDeck playerCards = player.getCardsInHand();
            while (playerCards.getSize() != 9) {
                if (programCards.getSize() == 0) {
                    int discardSize = discardedProgramCards.getSize();
                    programCards.addCollectionOfCardsToDeck(discardedProgramCards.handOutNCards(discardSize));
                    programCards.shuffle();
                }
                playerCards.addCardToDeck(programCards.removeCard(0));
            }*/
        }
    }

    @Override
    public void setUpTurn(IDeck[] selectedCards) {
        this.selectedCards = selectedCards;
    }
}
