package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.AI;
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
import java.util.Random;

import static inf112.project.RoboRally.game.GameStatus.*;


public class Game implements IGame {

    private GameBoard board;
    private List<IPlayer> players; // All the participating players, ordered after player creation.
    // Certain events that prioritises starting order, may use this list for reference.
    // For example: players[0] has higher priority than players[1].
    private List<IPlayer> activePlayers; // Players that are not incapacitated.
    private List<IPlayer> destroyedPlayers; // Players that are destroyed, but can come back.
    private List<IPlayer> playersOutOfTheGame; // Players with no more lives, and permanently out of the game.
    // First to loose is ordered last in the list.
    // For example: When somebody is removed from the game,
    // they are inserted into position 0 in this list.
    private List<Laser> lasers; // The list of lasers in the game.
    private IDeck programCards;
    private IDeck discardedProgramCards;
    // private IDeck[] selectedCards;
    private GameStatus currentGameStatus;
    private boolean everyFlagHasBeenVisited;
    private int currentSlotNumber;
    private final int NUMBER_OF_REGISTER_SLOTS = 5;
    private final int MAX_DAMAGE_TOKENS_BEFORE_BEING_DESTROYED = 9; // The number of damage tokens a player
    // can receive before being destroyed. (NumberOfTokens >= 10) == destroy(player);
    private IPlayer winner;

    /**
     * Default constructor for making a new game with default settings.<br>
     * Uses a standard board and walls layout.
     */
    public Game() {
        String defaultLayout = "16C12R" +
                "....kr.rrr......" +
                ".R..RRRRRRRRDu.." +
                ".U.........cD..." +
                ".U........i.D..." +
                ".U......ll..D.p." +
                "rU..LLLL.LLLD..." +
                "ll.....w....C..." +
                ".r..p....lll...." +
                ".r.....w.....i.." +
                "...ff..w.....p.." +
                "..........n....." +
                ".r....WW....dd..";
        String defaultWalls = "" +
                "fnnnnnnnnnnnnnng" +
                "................" +
                "................" +
                ".nnnn..........." +
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

    /**
     * Add the participating players,<br>
     * generate the game board using the provided board and walls layout,<br>
     * initialize the variables and create the deck of program cards.
     *
     * @param gameBoardLayout
     *                  The layout of the game board.
     * @param wallsLayout
     *                  The layout of the walls on the board.
     */
    private void initializeGame(String gameBoardLayout, String wallsLayout) {
        this.board = new GameBoard(gameBoardLayout, wallsLayout);
        this.players = new ArrayList<>();
        this.activePlayers = new ArrayList<>();
        this.destroyedPlayers = new ArrayList<>();
        this.playersOutOfTheGame = new ArrayList<>();
        this.programCards = new Deck();
        this.programCards.createProgramCardsDeck();
        this.discardedProgramCards = new Deck();
        this.lasers = new ArrayList<>();
        this.everyFlagHasBeenVisited = false;
        this.currentSlotNumber = 0;
        this.addPlayers();
        this.registerLasers();
        // this.updateDeckOfSelectedCards();
        this.dealOutProgramCards();
        this.setGameStatus(SELECT_CARDS);
    }

    @Override
    public void addPlayers() {
        // Hardcoded players for demonstration.
        IPlayer player1 = new Player("Buzz", 5, 4, Color.RED);
        IPlayer player2 = new Player("Emma", 6, 4, Color.CYAN);
        IPlayer player3 = new Player("G-bot", 7, 4, Color.LIME);
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.activePlayers.addAll(players);
    }

    /**
     * Add each players laser and all laser towers,
     * to the games list of operational lasers.
     */
    private void registerLasers() {
        for (IPlayer player : this.players) {
            this.lasers.add(player.getLaser());
        }
        this.registerLaserTowers();
    }

    /**
     * Adds laser towers to the games list of operational lasers.
     */
    private void registerLaserTowers() {
        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                IObjects gameObject = board.getObject(y, x);
                if (gameObject instanceof LaserTower) {
                    lasers.add(((LaserTower) gameObject).getLaser());
                }
            }
        }
    }

    /**
     * Shuffle the program cards deck.
     * Each player draw cards,
     * depending on how many damage tokens they have.
     */
    private void dealOutProgramCards() {
        programCards.shuffle();
        for (IPlayer player : activePlayers) {
            int numberOfCardsPlayerCanDraw =
                    calculateTheNumberOfCardsThePlayerCanDraw(player);
            player.addCardsToPlayersHand(programCards.handOutNCards(numberOfCardsPlayerCanDraw));
            System.out.println("Dealt cards to " + player.getName());
        }
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

    /**
     * Determine the number of cards the player is eligible to receive.
     *
     * @param player
     *              The chosen player.
     * @return The number of cards this player should be dealt.
     *
     * @throws IllegalArgumentException
     *      if player == null.
     */
    private int calculateTheNumberOfCardsThePlayerCanDraw(IPlayer player) {
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
    public void doTurn() {
        switch (this.currentGameStatus) {
            case EXECUTING_INSTRUCTIONS:
                System.out.println();
                System.out.println("Register phase " + (currentSlotNumber + 1));
                System.out.println("EXECUTING_INSTRUCTIONS");
                this.executingInstructions();
                return;
            case EXECUTING_GAME_BOARD_OBJECTS:
                System.out.println("EXECUTING_GAME_OBJECTS");
                this.executingGameBoardObjects();
                return;
            case FIRING_LASERS:
                System.out.println("FIRING_LASERS");
                this.fireLasers();
                return;
            case TOUCH_FLAGS_AND_REPAIR_SITES:
                System.out.println("TOUCH_FLAGS_AND_REPAIR_SITES");
                this.flagsAndRepairs();
                return;
            case FINISHING_UP_THE_TURN:
                System.out.println("FINISHING_UP_THE_TURN");
                this.cleanUpTurn();
                return;
            case SOMEONE_HAS_WON:
                System.out.println(winner.getName() + " has won the game!");
                break;
            case THE_END:
                System.out.println("All players are out, the game ends in a draw...");
        }
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
        IDeck cardsForThisRegisterSlot = new Deck();
        ArrayList<IPlayer> listOfPlayers = new ArrayList<>(); // For keeping track of players and their cards.
        this.revealEachPlayersProgramCardForTheCurrentRegister(cardsForThisRegisterSlot, listOfPlayers);
        this.sortCardsAfterPriority(cardsForThisRegisterSlot, listOfPlayers);
        this.executeProgramCardsForTheCurrentRegister(cardsForThisRegisterSlot, listOfPlayers);
        updateCurrentRegisterSlot();
        if (this.activePlayers.size() <= 0) {
            this.finishEarly();
        }
        this.setGameStatus(EXECUTING_GAME_BOARD_OBJECTS);
//        if (activePlayers.size() != 0)
  //          setGameStatus(EXECUTING_GAME_BOARD_OBJECTS);
    }

    /**
     * Reveal each players program card for the current register slot,
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
     * Execute the revealed program cards with their associated players.
     *
     * @param cardsForThisRegisterSlot The deck of revealed Program cards.
     * @param listOfPlayers            The list of players connected with the revealed program cards.
     */
    public void executeProgramCardsForTheCurrentRegister(IDeck cardsForThisRegisterSlot,
                                                         ArrayList<IPlayer> listOfPlayers) {
        for (int i = 0; i < listOfPlayers.size(); i++) {
            ICard card = cardsForThisRegisterSlot.getCardAtPosition(i);
            IPlayer player = listOfPlayers.get(i);
            player.movePlayer(card);
            Coordinates validPositionForPlayer = moveToValidCoordinates(player.getPathOfPlayer(), player);
            player.resetPathOfPlayer();
            player.setCoordinates(validPositionForPlayer);
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
     * All players interact with Game objects if they stand on any.<br>
     * If a player is in an invalid position (outside the board),
     * they are destroyed.
     */
    private void executingGameBoardObjects() {
        for (IPlayer player : players) {
            if (this.checkIfThePlayerIsInTheGame(player)) {
                if (board.moveValid(player.getX(), player.getY())) {
                    IObjects object = board.getObject(player.getCoordinates());
                    if (object instanceof ConveyorBelt || object instanceof  RotationCog)
                        object.doAction(player);
                    //board.getObject(player.getX(), player.getY()).doAction(player);
                    int sizeOfMovement = player.getPathOfPlayer().size();
                    if (sizeOfMovement != 0)
                        player.setCoordinates(moveToValidCoordinates(player.getPathOfPlayer(), player));
                } else {
                    this.destroyPlayer(player);
                }
                // If the player is moved off the board by a game object,
                // the position will no longer be valid.
                if (this.checkIfThePlayerIsInTheGame(player)
                        && !board.moveValid(player.getX(), player.getY())) {
                    this.destroyPlayer(player);
                }
            }
        }
        prepareLasersForFiring();
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
            System.out.println("player " + player.getName() + " is permanently out of the game");
            this.playersOutOfTheGame.add(player);
            this.emptyThePlayersRegister(player);
            if (this.gameOver()) {
                this.setGameStatus(THE_END);
                this.doTurn();
            }
        } else {
            this.destroyedPlayers.add(player);
        }
        if (this.activePlayers.size() <= 0) { // Cut the round short if all players are incapacitated.
            this.finishEarly();
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
     * Prepares for the current turn to end early.
     */
    private void finishEarly() {
        this.setCurrentSlotNumber(0);
        this.setGameStatus(FINISHING_UP_THE_TURN);
    }

    /**
     * Resets the lasers positions and test fires,
     * so that the GUI can draw the laser beams correctly.
     */
    private void prepareLasersForFiring() {
        for (Laser laser : lasers) {
            if (!laser.hasPlayer() || (laser.hasPlayer()
                    && this.checkIfThePlayerIsOperational(laser.getPlayer()))) {
                laser.resetLaserPosition();
                laser.doAction(board.getRows(), board.getColumns());
            }
        }
    }

    @Override
    public Boolean checkIfThePlayerIsOperational(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        boolean playerIsOperational = true;
        if (!this.checkIfThePlayerIsInTheGame(player)) {
            playerIsOperational = false;
        }
        //TODO: check if player is powered down
        return playerIsOperational;
    }

    /**
     * Fire the laser of every active player and tower,
     * and removes lasers from players that are out of the game.
     */
    private void fireLasers() {
        int counter = 1;
        for (Laser laser : lasers) {
            if (!laser.hasPlayer() || (laser.hasPlayer()
                    && this.checkIfThePlayerIsOperational(laser.getPlayer()))) {
                String name = laser.hasPlayer() ? laser.getPlayer().getName() : "Tower";
                System.out.println(counter + " " + name);
                fireLaser(laser);
                counter++;
            }
        }
        this.removeLasersBelongingToDeadPlayers();
        this.setGameStatus(TOUCH_FLAGS_AND_REPAIR_SITES);
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
        laser.resetLaserPosition();
        List coordinatesHitByLaser = laser.doAction(board.getRows(), board.getColumns());
        List shortestPathToPlayer = getLaserPath(coordinatesHitByLaser, laser.getDirection(),
                laser);
        for (IPlayer otherPlayer : players) { // poor optimization
            if (shortestPathToPlayer.contains(((Player) otherPlayer).getCoordinates())
                    && otherPlayer.getLaser() != laser
                    && this.checkIfThePlayerIsInTheGame(otherPlayer)) {
                System.out.print(otherPlayer.getName() + " was hit by a laser from ");
                if (laser.hasPlayer()) System.out.println(laser.getPlayer().getName());
                else System.out.println("a tower");
                otherPlayer.takeOneDamage();
                this.destroyPlayerIfNecessary(otherPlayer);
                System.out.println(" and now has " + otherPlayer.getPlayerDamage() + " damage tokens and "
                        + otherPlayer.getNumberOfLivesRemaining() + " lives remaining");
            }
        }
    }

    // This would be better if the coordinates list was appended by the current player coordinates
    private Coordinates moveToValidCoordinates(List<Coordinates> coordinates, IPlayer player) {
        Coordinates currentPlayerCoordinates = new Coordinates(player.getX(), player.getY());
        if (coordinates.size() == 0)
            return currentPlayerCoordinates;

        GridDirection directionPlayerWantsToMove = currentPlayerCoordinates.getDirection(coordinates.get(0));

        // Checks the tile the player currently obtains
        if (board.moveValid(currentPlayerCoordinates.getX(), currentPlayerCoordinates.getY()) &&
                board.getObject(currentPlayerCoordinates).isWall(directionPlayerWantsToMove)) {
            return currentPlayerCoordinates;
        }

        // Ugly repetition, but it works...
        if (board.moveValid(currentPlayerCoordinates.getX(), currentPlayerCoordinates.getY()) &&
                board.getObject(currentPlayerCoordinates) instanceof Pit) {
            if (!destroyedPlayers.contains(player))
                destroyPlayer(player);
            return currentPlayerCoordinates;
        }

        // Checks if any tiles in path contain players
        Coordinates previousCoordinates = currentPlayerCoordinates;
        for (Coordinates playerCoordinates : coordinates) {
            if (board.moveValid(playerCoordinates.getX(), playerCoordinates.getY()) &&
                    board.getObject(playerCoordinates) instanceof Pit) {
                if (!destroyedPlayers.contains(player))
                    destroyPlayer(player);
                return playerCoordinates;
            }

            for (IPlayer player1 : players) {
                Coordinates previousPlayerPosition = player1.getCoordinates();
                if (playerCoordinates.equals(previousPlayerPosition)) {
                    // Compares coordinates with other player to get direction of movement
                    // (needed if a player moves two players on a row)
                    GridDirection direction = player.getCoordinates().getDirection(player1.getCoordinates());
                    player1.movePlayer(direction);
                    Coordinates positionOfPushedPlayer = moveToValidCoordinates(player1.getPathOfPlayer(), player1);
                    // if the other player wasn't moved - the current player shouldn't move either
                    if (positionOfPushedPlayer.equals(previousPlayerPosition))
                        return previousCoordinates;
                    previousCoordinates = playerCoordinates;
                    player1.setCoordinates(positionOfPushedPlayer);
                }
            }

        }

        // Checks the path the player wishes to move
        if (pathHasWall(coordinates, directionPlayerWantsToMove)) {
            previousCoordinates = currentPlayerCoordinates;
            for (Coordinates playerCoordinates : coordinates) {
                IObjects mightBeWall = board.getObject(playerCoordinates);
                if (mightBeWall.isWall(directionPlayerWantsToMove.invert())) {
                    return previousCoordinates;
                }
                else if (mightBeWall.isWall(directionPlayerWantsToMove)) {
                    return playerCoordinates;
                }

                previousCoordinates = playerCoordinates;
            }
        }

        return (coordinates.get(coordinates.size() - 1));
    }


    public boolean pathHasWall(List<Coordinates> coordinates, GridDirection direction) {
        List<Coordinates> coordinatesInsideMap = new ArrayList<>();

        for (Coordinates pathCoordinates : coordinates) {
            if (board.moveValid(pathCoordinates.getX(), pathCoordinates.getY()))
                coordinatesInsideMap.add(pathCoordinates);
        }

        for (Coordinates coordinates1 : coordinatesInsideMap) {
            IObjects object = board.getObject(coordinates1.getX(), coordinates1.getY());
            if (object.isWall(direction) || object.isWall(direction.invert()))
                return true;
        }
        return false;
    }

    @Override
    public List<Coordinates> getLaserPath(List<Coordinates> coordinates, GridDirection direction, Laser laser) {
        List<Coordinates> path = new ArrayList<>();
        for (Coordinates coordinate : coordinates) {

            // looking for walls
            if (!board.moveValid(coordinate.getX(), coordinate.getY())) {
                return path;
            }
            IObjects current = board.getObject(coordinate.getX(), coordinate.getY());
            if (current.isWall(direction.invert()) && !coordinate.equals(coordinates.get(0))) {
                return path;
            } else if (current.isWall(direction)) {
                path.add(coordinate);
                return path;
            }

            // looking for players
            for (IPlayer player : players) {
                if (this.checkIfThePlayerIsInTheGame(player)) { // only look for players that can be hit
                    if (player.getX() == coordinate.getX() && player.getY() == coordinate.getY()
                            && player.getLaser() != laser) {
                        path.add(coordinate);
                        return path;
                    }
                }
            }

            path.add(coordinate);
        }
        return path;
    }

    /**
     * Checks if the given player should be destroyed.
     * If yes, it destroys the player.
     * If no, the player is left alive.
     *
     * @param player
     *              The player to potentially be destroyed.
     */
    private void destroyPlayerIfNecessary(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        if (player.getPlayerDamage() > MAX_DAMAGE_TOKENS_BEFORE_BEING_DESTROYED) {
            this.destroyPlayer(player);
        }
    }

    /**
     * Remove lasers attached to players that are no longer in the game.
     */
    private void removeLasersBelongingToDeadPlayers() {
        Laser laserToRemove = null;
        for (IPlayer player : this.getPlayersOutOfTheGame()) {
            for (Laser laser : lasers) {
                if (laser.hasPlayer() && laser.getPlayer().equals(player)) {
                    laserToRemove = laser; // Assumes only one laser per player.
                }
            }
            lasers.remove(laserToRemove);
        }
    }

    private void flagsAndRepairs() {
        for (IPlayer player : activePlayers) {
            IObjects object = board.getObject(player.getCoordinates());
            if (object instanceof Flag || object instanceof SingleWrench || object instanceof CrossedWrench) {
                object.doAction(player);
            }
            if (player.getFlagsVisited() == numberOfFlags()) {
                setGameStatus(SOMEONE_HAS_WON);
                winner = player;
                return;
            }
        }
        if (this.currentSlotNumber == 0) { // Gone through all the register slots,
            this.setGameStatus(FINISHING_UP_THE_TURN); // so the round is over.
        } else {
            this.setGameStatus(EXECUTING_INSTRUCTIONS);
        }
    }

    private int numberOfFlags() {
        int nrOfFlags = 0;
        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                if (board.getObject(y,x) instanceof Flag)
                    nrOfFlags++;
            }
        }
        return nrOfFlags;
    }

    /**
     * Clean up the game before the next round.<br>
     * Players standing on wrench tiles removes one damage,
     * and all destroyed players are brought back into the game.
     */
    private void cleanUpTurn() {
        for (IPlayer player : players) {
            if (this.checkIfThePlayerIsInTheGame(player)) {
                IObjects playerIsStandingOn = this.board.getObject(player.getX(), player.getY());
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
        System.out.println();
        System.out.println("New turn");
        System.out.println();
    }

    /**
     * Restore all the destroyed players to their last backup locations.
     */
    private void restoreDestroyedPlayers() {
        for (IPlayer player : destroyedPlayers) {
            System.out.println("respawning player: " + player.getName());
            System.out.println("x: " + player.getX());
            System.out.println("y: " + player.getY());
            this.restorePlayerBasedOnPriority(player);
            Coordinates positionPlayerWishesToRespawn = player.respawnAtLastArchiveMarker();
            Random random = new Random();
            for (IPlayer player1 : activePlayers) {
                if (player1.getCoordinates().equals(positionPlayerWishesToRespawn)) {
                    List<Coordinates> validCoordinates = validCoordinatesForRespawn(positionPlayerWishesToRespawn);
                    positionPlayerWishesToRespawn = validCoordinates.get(random.nextInt(validCoordinates.size()));
                }
            }
            player.setCoordinates(positionPlayerWishesToRespawn);
            //TODO: Ask player for which direction they would like to face.
        }
    }

    private List<Coordinates> validCoordinatesForRespawn(Coordinates coordinates) {
        List<Coordinates> validCoordinates = new ArrayList<>();
        for (int x = coordinates.getX()-1; x < coordinates.getX() + 2; x++) {
            for (int y = coordinates.getY()-1; y < coordinates.getY() + 2; y++) {
                if (!board.moveValid(x,y) || board.getObject(x,y) instanceof Pit
                        || (x == coordinates.getX() && y == coordinates.getY())) {
                    continue;
                }
                validCoordinates.add(new Coordinates(x,y));
            }
        }
        return validCoordinates;
    }

    /**
     * Determine the position the destroyed player should have
     * in the list of activePlayers, based on start order.
     *
     * @param player
     *              The player to be restored.
     * @throws IllegalArgumentException
     *       If the player is null (player == null).
     */
    private void restorePlayerBasedOnPriority(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        if (activePlayers.isEmpty()) {
            activePlayers.add(player);
        } else {
            for (int pos = 0; pos < activePlayers.size(); pos++) {
                IPlayer otherPlayer = activePlayers.get(pos);
                if (player.getPriority() < otherPlayer.getPriority()) {
                    System.out.println("Added to pos: " + pos);
                    activePlayers.add(pos, player);
                    break;
                }
                if (pos == (activePlayers.size() - 1)) {
                    System.out.println("Added at the end");
                    activePlayers.add(player);
                    break;
                }
            }
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
     * Empties each players deck of cards,
     * before dealing out new cards and
     * preparing for the selection of a new program.
     */
    private void setupCardSelectionForNewRound() {
        this.removeAllCardsFromEachPlayersHand();

        for (IPlayer player : activePlayers) {
            this.drawCards(player);
        }

        // this.updateDeckOfSelectedCards();
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
     * The given player draws as many cards as they are allowed
     * from the program cards deck.<br>
     * If the deck doesn't have enough cards,
     * then the player draws the cards that are left in the deck.<br>
     *
     * The pile of discarded program cards are
     * subsequently shuffled back into the main deck,
     * and the player draws the remaining cards owed.
     *
     * @param player
     *              The player that wants to draw cards.
     *
     * @throws IllegalArgumentException
     *      if player == null.
     */
    private void drawCards(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Not a valid player");
        }
        List<ICard> drawnCards;
        int numberOfCardsToDraw = this.calculateTheNumberOfCardsThePlayerCanDraw(player);
        int numberOfCardsLeftInProgramCardsDeck = this.programCards.getSize();
        int numberOfCardsMissing = numberOfCardsToDraw -
                numberOfCardsLeftInProgramCardsDeck;
        if (numberOfCardsMissing > 0) {
            drawnCards = this.programCards.handOutNCards(numberOfCardsLeftInProgramCardsDeck);
            this.shuffleDiscardedProgramCardsIntoProgramCardsDeck();
            drawnCards.addAll(this.programCards.handOutNCards(numberOfCardsMissing));
        } else {
            drawnCards = this.programCards.handOutNCards(numberOfCardsToDraw);
        }
        System.out.println("Dealt cards to player: " + player.getName());
        player.addCardsToPlayersHand(drawnCards);
    }

    @Override
    public GameBoard getBoard() {
        return board;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public List<IPlayer> getActivePlayers() {
        return activePlayers;
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
    public List<Laser> getLasers() {
        return lasers;
    }

    @Override
    public int getCurrentSlotNumber() {
        return currentSlotNumber;
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
    public GameStatus getTheCurrentGameStatus() {
        return this.currentGameStatus;
    }

    @Override
    public void setGameStatus(GameStatus status) {
        if (status == null
                || !GameStatus.validStatus(status)) {
            throw new IllegalArgumentException("Not a valid status");
        }
        this.currentGameStatus = status;
    }

    @Override
    public int getNumberOfPlayersLeftInTheGame() {
        return (players.size() - playersOutOfTheGame.size());
    }

    @Override
    public boolean gameOver() {
        return this.getNumberOfPlayersLeftInTheGame() <= 0;
    }

}
