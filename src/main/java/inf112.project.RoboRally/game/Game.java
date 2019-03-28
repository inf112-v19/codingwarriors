package inf112.project.RoboRally.game;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.objects.*;

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
    private List<IPlayer> destroyedPlayers; // Players that are destroyed, but can come back.
    private List<IPlayer> playersOutOfTheGame; // Players with no more lives, and permanently out of the game.
    // First to loose is ordered last in the list.
    // For example: When somebody is removed from the game,
    // they are inserted into position 0 in this list.
    private GameBoard board;
    private int numberOfPlayersLeftInTheGame;
    private boolean everyFlagHasBeenVisited;
    private GameStatus currentGameStatus;
    private IPlayer currentlyActingPlayer; // The player whose cards are to be displayed.
    private int currentSlotNumber;
    private final int NUMBER_OF_REGISTER_SLOTS = 5;
    private IDeck[] selectedCards;
    private List<Laser> lasers = new ArrayList<>();


    /**
     * Default constructor for making a new game with default settings.<br>
     * Uses a standard board and walls layout.
     */
    public Game() {
        String defaultLayout = "16C12R" +
                "f....r.rrr...f.." +
                "............uu.." +
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
        String defaultWalls = "" +
                "fnnnnnnnnnnnnnng" +
                "................" +
                "................" +
                "................" +
                "......Mexico...." +
                "................" +
                "................" +
                "................" +
                "................" +
                "................" +
                "................" +
                "................";
        this.initializeGame(defaultLayout, defaultWalls);
    }

    /**
     * Constructor for custom boards.
     *
     * @param boardLayout     The custom made board.
     * @param boardWallLayout The custom made boards walls.
     */
    public Game(String boardLayout, String boardWallLayout) {
        this.initializeGame(boardLayout, boardWallLayout);
    }

    public void addLaserTowers() {
        // currently have to hardcode each tower cause there isn't really a communication between walls and lasertowers
        LaserTower tower = new LaserTower(new Coordinates(2, 12), GridDirection.SOUTH);
        lasers.add(tower.getLaser());
    }

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
        System.out.println("dealt cards to player: " + player.getName());
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
    public void initializeGame(String gameBoardLayout, String gameBoardWalls) {
        //TODO: Make initializeGame take a gameboard as parameter,
        // which can be chosen at the start menu?
        addPlayers();
        this.board = new GameBoard(gameBoardLayout, gameBoardWalls);
        this.programCards = new Deck();
        this.discardedProgramCards = new Deck();
        this.programCards.createProgramCardsDeck();
        this.everyFlagHasBeenVisited = false;
        this.numberOfPlayersLeftInTheGame = players.size();
        this.currentSlotNumber = 0;
        this.destroyedPlayers = new ArrayList<>();
        this.playersOutOfTheGame = new ArrayList<>();
        addLaserTowers();

        this.updateDeckOfSelectedCards();
        this.dealOutProgramCards();
        this.setGameStatus(SELECT_CARDS);
    }

    @Override
    public void addPlayers() {
        // Hardcoded players for demonstration.
        IPlayer player1 = new Player("Buzz", 2, 10, Color.RED);
        IPlayer player2 = new Player("Emma", 5, 10, Color.CYAN);
        IPlayer player3 = new Player("G-bot", 2, 5, Color.LIME);
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.lasers.add(player1.getLaser()); // adds the lasers belonging to the players
        this.lasers.add(player2.getLaser());
        this.lasers.add(player3.getLaser());
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
    public List<IPlayer> getDestroyedPlayers() {
        return destroyedPlayers;
    }

    @Override
    public List<IPlayer> getPlayersOutOfTheGame() {
        return playersOutOfTheGame;
    }

    @Override
    public GameStatus getTheCurrentGameStatus() {
        return this.currentGameStatus;
    }

    public GameBoard getBoard() {
        return board;
    }

    @Override
    public IDeck[] getSelectedCards() {
        return this.selectedCards;
    }

    @Override
    public int getNumberOfPlayersLeftInTheGame() {
        return this.numberOfPlayersLeftInTheGame;
    }

    @Override
    public void setGameStatus(GameStatus status) {
        if (status == null
                || !GameStatus.validStatus(status)) {
            throw new IllegalArgumentException("Not a valid status");
        }

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
                System.out.println("exe game obj");
                return;
            case FIRING_LASERS:
                System.out.println("firing lasers");
                this.fireLasers();
                return;
            case FINISHING_UP_THE_TURN:
                this.cleanUpTurn();
                return;
            case THE_END:
                System.out.println("All players are out, the game ends in a draw...");
                return;
        }

    }

    /**
     * Clean up the game before the next round.<br>
     * Players standing on wrench tiles removes one damage,
     * and all destroyed players are brought back into the game.
     */
    private void cleanUpTurn() {
        System.out.println("cleaning");
        for (IPlayer player : players) {
            if (this.checkIfThePlayerIsInTheGame(player)) {
                IObjects playerIsStandingOn = this.getBoard().getObject(player.getX(), player.getY());
                if (playerIsStandingOn.equals(CrossedWrench.class)) {
                    player.removeOneDamage();
                    // TODO: this.drawOneOptionCard(player);
                } else if (playerIsStandingOn.equals(SingleWrench.class)) {
                    player.removeOneDamage();
                }
            }
        }

        this.restoreDestroyedPlayers();
        this.destroyedPlayers.clear(); // All destroyed players has been restored.
        this.emptyEachPlayersRegister();
        this.setupCardSelectionForNewRound();
        this.setGameStatus(SELECT_CARDS);
    }

    /**
     * Restore all the destroyed players to their last backup locations.
     */
    private void restoreDestroyedPlayers() {
        for (IPlayer player : destroyedPlayers) {
            System.out.println("respawning player: " + player.getName());
            System.out.println("x: " + player.getX());
            System.out.println("y: " + player.getY());
            activePlayers.add(player);
            player.respawnAtLastArchiveMarker();
            //TODO: Ask player for which direction they would like to face.
        }
    }

    /**
     * All players interact with Game objects if they stand on any.<br>
     * If a player is in an invalid position (outside the board),
     * they are destroyed.
     */
    private void executingGameBoardObjects() {
        for (IPlayer player : players) {
            if (this.checkIfThePlayerIsInTheGame(player)) {
                if (board.moveValid(player.getX(), player.getY())) {
                    board.getObject(player.getX(), player.getY()).doAction(player);
                    //   this.firePlayersLaser(player); // to be moved
                } else {
                    this.destroyPlayer(player);
                    if (this.activePlayers.size() <= 0) { // Cut the round short if all players are incapacitated.
                        this.setCurrentSlotNumber(0);
                        this.setGameStatus(FINISHING_UP_THE_TURN);
                        return;
                    }
                }
            }
        }
        setGameStatus(FIRING_LASERS);
    }

    /**
     * A player is outside of the game if they are currently destroyed,
     * or permanently out of the game.
     *
     * @param player The player to be checked.
     * @return true if the player is in the game,<br>
     * false otherwise.
     * @throws IllegalArgumentException If the player is null (player == null).
     */
    private boolean checkIfThePlayerIsInTheGame(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        boolean playerIsInTheGame = false;
        if (!this.playersOutOfTheGame.contains(player)
                && !this.destroyedPlayers.contains(player)) {
            playerIsInTheGame = true;
        }
        return playerIsInTheGame;
    }

    /**
     * Fire the laser of every active player.
     */
    private void fireLasers() {
        for (Laser laser : lasers) {
            fireLaser(laser);
            if (laser.hasPlayer()) // checks if its a laser fired from a player or a tower
                laser.resetLaserPosition(laser.getPlayer().getCoordinates(), laser.getPlayer().getPlayerDirection());
            else {
                laser.resetLaserPosition(laser.getTower().getCoordinates(), null);
            }
        }

        if (this.currentSlotNumber == 0) { // Gone through all the register slots,
            this.setGameStatus(FINISHING_UP_THE_TURN); // so the round is over.
        } else {
            this.setGameStatus(EXECUTING_INSTRUCTIONS);
        }
    }

    /**
     * Fire the given players laser.<br>
     * Dealing one damage to any player in direct line of sight.
     *
     * @param laser The laser that should be fired.
     * @throws IllegalArgumentException if laser is null (laser == null).
     */
    private void fireLaser(Laser laser) {
        if (laser == null) {
            throw new IllegalArgumentException("Not a valid laser");
        }
        List coordinatesHitByLaser = laser.doAction(board.getRows(), board.getColumns());
        List shortestPathToPlayer = shortestPathToObstacle(coordinatesHitByLaser, laser);
        for (IPlayer otherPlayer : players) { // poor optimization
            if (shortestPathToPlayer.contains(((Player) otherPlayer).getCoordinates()) &&
                    otherPlayer.getLaser() != laser) {
                otherPlayer.takeOneDamage();
                System.out.print(otherPlayer.getName() + " was hit by a laser from ");
                if (laser.hasPlayer()) System.out.println(laser.getPlayer().getName());
                else System.out.println("a tower");
            }
        }
    }


    // in the case of multiple players being on the same line this method checks for the shortest path to a player
    public List shortestPathToObstacle(List laserCoordinates, Laser laser) {
        List finalLaserCoordinates = new ArrayList();
        List comparativeList = new ArrayList();
        int smallestListSize = laserCoordinates.size();

        for (IPlayer player : players) {
            if (laser.hasPlayer() && player.getLaser() == laser) // if the laser fired hits the player who fired it just continue
                continue;
            for (int i = 0; i < laserCoordinates.size(); i++) {
                if (laserCoordinates.get(i).equals(((Player) player).getCoordinates())) {
                    comparativeList.add(laserCoordinates.get(i));
                    if (comparativeList.size() < smallestListSize) {
                        smallestListSize = comparativeList.size();
                        finalLaserCoordinates = new ArrayList(comparativeList);
                        comparativeList.clear();
                    }
                    break;
                }
                comparativeList.add(laserCoordinates.get(i));
            }
            comparativeList.clear();
        }
        return finalLaserCoordinates;
    }

    /**
     * Reveals the selected program cards for the current register slot,
     * sorts them by priority before executing the commands in order,
     * and then updates the register slot for the next pass.
     * <p>
     * If the current register slot is the last one,
     * execute instructions in a TODO: finish this
     */
    private void executingInstructions() {
        System.out.println("exe instr");
        IDeck cardsForThisRegisterSlot = new Deck();
        ArrayList<IPlayer> listOfPlayers = new ArrayList<>(); // For keeping track of players and their cards.
        this.revealEachPlayersProgramCardForTheCurrentRegister(cardsForThisRegisterSlot, listOfPlayers);
        this.sortCardsAfterPriority(cardsForThisRegisterSlot, listOfPlayers);
        this.executeProgramCardsForTheCurrentRegister(cardsForThisRegisterSlot, listOfPlayers);
        updateCurrentRegisterSlot();
        setGameStatus(EXECUTING_GAME_BOARD_OBJECTS);
    }

    /**
     * Execute the revealed program cards with their associated players.
     *
     * @param cardsForThisRegisterSlot The deck of revealed Program cards.
     * @param listOfPlayers            The list of players connected with the revealed program cards.
     */
    private void executeProgramCardsForTheCurrentRegister(IDeck cardsForThisRegisterSlot,
                                                          ArrayList<IPlayer> listOfPlayers) {
        for (int i = 0; i < listOfPlayers.size(); i++) {
            ICard card = cardsForThisRegisterSlot.getCardAtPosition(i);
            IPlayer player = listOfPlayers.get(i);
            player.movePlayer(card);
        }
    }

    /**
     * Reveal each players program card for this register slot,
     * and connects it with the player it belongs to.
     * <br><br>
     * Players and cards are connected by their mutual index.
     *
     * @param cardsForThisRegisterSlot The deck of revealed Program cards.<br>
     *                                 Should contain all the revealed program cards
     *                                 after this method finishes.
     * @param listOfPlayers            The list of players connected with the revealed program cards.<br>
     *                                 Should contain all the players whose cards were revealed.
     */
    private void revealEachPlayersProgramCardForTheCurrentRegister(IDeck cardsForThisRegisterSlot,
                                                                   ArrayList<IPlayer> listOfPlayers) {
        for (IPlayer player : this.activePlayers) {
            ICard programCard = player.revealProgramCardForRegisterNumber(currentSlotNumber);
            cardsForThisRegisterSlot.addCardToDeck(programCard);
            listOfPlayers.add(player);
        }
    }

    /**
     * Move all unlocked cards from each players registry,
     * to the pile of discarded program cards.
     */
    private void emptyEachPlayersRegister() {
        for (IPlayer player : this.activePlayers) {
            this.emptyThePlayersRegister(player);
        }
    }

    /**
     * Move all unlocked cards from the players register,
     * to the pile of discarded cards.
     *
     * @param player The player whose register should be emptied.
     */
    private void emptyThePlayersRegister(IPlayer player) {
        IDeck cardsToBeDiscarded = player.clearRegister();
        cardsToBeDiscarded.transferNCardsFromThisDeckToTargetDeck(
                cardsToBeDiscarded.getSize(),
                this.discardedProgramCards);
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
     * @param cardsForThisRegisterSlot The deck of cards to sort.
     * @param listOfPlayers            The accompanying list of players.
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
     * @param i                        Index i.
     * @param j                        Index j.
     * @param cardsForThisRegisterSlot The deck of cards.
     * @param listOfPlayers            The list of players.
     */
    private void swap(int i, int j, IDeck cardsForThisRegisterSlot, ArrayList<IPlayer> listOfPlayers) {
        cardsForThisRegisterSlot.swapCardsInPosition(i, j);
        IPlayer playerI = listOfPlayers.remove(i);
        IPlayer playerJ = listOfPlayers.remove(j);
        listOfPlayers.add(j, playerI);
        listOfPlayers.add(i, playerJ);
    }

    /**
     * Removes a player from the game.<br>
     * If the player is out of lives,
     * they will be moved to the list of dead players.
     * Otherwise they are temporarily removed until the end of the current turn.<br><br>
     * <p>
     * If there are no players left alive,
     * the game is considered to be over.???
     *
     * @param player The player to be destroyed.
     */
    private void destroyPlayer(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        System.out.println("player " + player.getName() + " was destroyed at");
        System.out.println("x: " + player.getX());
        System.out.println("y: " + player.getY());
        player.destroyPlayer();
        this.activePlayers.remove(player);
        if (!player.hasLifeLeft()) {
            this.playersOutOfTheGame.add(player);
            this.numberOfPlayersLeftInTheGame--;
            this.emptyThePlayersRegister(player);
            if (this.numberOfPlayersLeftInTheGame <= 0) {
                //game over
                this.setGameStatus(THE_END);
                this.doTurn();
            }
            System.out.println("player " + player.getName() + " is permanently out of the game");
        } else {
            this.destroyedPlayers.add(player);
        }
    }

    /**
     * Empties each players deck of cards,
     * before dealing out new cards and
     * preparing for the selection of a new program.
     */
    private void setupCardSelectionForNewRound() {
        this.removeAllCardsFromEachPlayersHand();

        for (IPlayer player : activePlayers) {
            drawCards(player);
        }

        this.updateDeckOfSelectedCards();
    }

    /**
     * Takes all the players program cards,
     * and moves the cards to the discard pile.
     */
    private void removeAllCardsFromEachPlayersHand() {
        for (IPlayer player : players) {
            IDeck cardsInPlayerDeck = player.getCardsInHand();
            cardsInPlayerDeck.transferNCardsFromThisDeckToTargetDeck(
                    cardsInPlayerDeck.getSize(),
                    discardedProgramCards);
        }
    }

    /**
     * Remakes the decks that hold the cards during the selection process.<br>
     * Ensures that the decks are only made for players that needs it.
     */
    private void updateDeckOfSelectedCards() {
        this.selectedCards = new IDeck[numberOfPlayersLeftInTheGame];
        for (int i = 0; i < numberOfPlayersLeftInTheGame; i++) {
            this.selectedCards[i] = new Deck();
        }
    }

}
