package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.objects.GridDirection;
import inf112.project.RoboRally.objects.Laser;

import java.util.List;

public interface IGame {


    /**
     * Add the participating players,<br>
     * generate the game board using the provided board and walls layout,<br>
     * reset the variables and create the deck of program cards.
     *
     * @param boardLayout
     *                  The layout of the game board.
     * @param wallsLayout
     *                  The layout of the walls on the board.
     */
    void initializeGame(String boardLayout, String wallsLayout);


    /**
     * Add users to the list of participating players.<br><br>
     *
     * Ask for number of players.<br>
     * Ask each user for a username,<br>
     * and add them to the list of players.
     */
    void addPlayers();


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
    int calculateTheNumberOfCardsThePlayerCanDraw(IPlayer player);


    /**
     * Get the list of players that are able to act this round.
     *
     * @return The list of active players.
     */
    List<IPlayer> getActivePlayers();


    /**
     * Get the list of participating players.
     *
     * @return The list of players.
     */
    List<IPlayer> getPlayers();


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
    void drawCards(IPlayer player);

    /**
     * Shuffle the program cards deck.
     * Each player draw cards,
     * depending on how many damage tokens they have.
     */
    void dealOutProgramCards();


    /**
     * Get the list of players that are destroyed,
     * and temporarily removed from the game.
     *
     * @return The list of players that are out of
     *  the game for the remainder of the current round.
     */
    List<IPlayer> getDestroyedPlayers();


    /**
     * Get the list of players with no more lives left.
     *
     * @return The list of players permanently out of the game.
     */
    List<IPlayer> getPlayersOutOfTheGame();


    /**
     * Get the games current status,
     * to determine GUI drawing mode.
     *
     * @return The current phase of the game.
     */
    GameStatus getTheCurrentGameStatus();


    /**
     * Check if the given player is prevented from performing actions.<br>
     * A player is unable to act if they are destroyed,
     * are permanently out of the game,
     * or is powered down (to be implemented).
     *
     * @param player
     *              The player to check for action eligibility.
     * @return true if the player can perform game actions,
     *       false otherwise
     * @throws IllegalArgumentException
     *      If the given player is null (player == null).
     */
    Boolean checkIfThePlayerIsOperational(IPlayer player);

    /**
     * Returns the game board
     * @return Returns the game board
     */
    GameBoard getBoard();


    /**
     * Get the deck used to hold all the selected cards,
     * before putting them into the register.
     *
     * @return The deck of selected cards.
     */
    IDeck[] getSelectedCards();


    /**
     * Get the amount of players that has one or more lives left.
     *
     * @return The number of players still in the game.
     */
    int getNumberOfPlayersLeftInTheGame();


    /**
     * Update the current state of the game.
     *
     * @param status
     *              The new game status.
     */
    void setGameStatus(GameStatus status);


    /**
     * Update the current slot number to
     *
     * @param number
     *              The new slot number.<br>
     *              Must not be negative or above the NUMBER_OF_REGISTER_SLOTS.
     * @throws IllegalArgumentException
     *       if slotNumber is negative (slotNumber < 0),<br>
     *       slotNumber is too high (slotNumber > NUMBER_OF_REGISTER_SLOTS),<br>
     *       or slotNumber == null.
     */
    void setCurrentSlotNumber(Integer number);


    /**
     * Perform round actions based on the games current state.
     */
    void doTurn();

    List<Laser> getLasers();

    List<Coordinates> getPath(List<Coordinates> coordinates, GridDirection direction);

    boolean gameOver();
}
