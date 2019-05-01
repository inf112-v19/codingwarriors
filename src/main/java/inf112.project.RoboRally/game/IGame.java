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
     * Add users to the list of participating players.<br><br>
     *
     * Ask for number of players.<br>
     * Ask each user for a username,<br>
     * and add them to the list of players.
     */
    void addPlayers();


    /**
     * Initiate the various game phases, based on the current state of the game.
     */
    void doTurn();


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
     * Analyze the path/trajectory of the given laser being fired.<br>
     * If something is hit that prevents the laser from going further,
     * we stop, and return the coordinates that can be traversed.
     *
     * @param coordinates
     *                  The list of coordinates where the laser potentially travels.
     * @param direction
     *                  The facing direction of the object firing the laser.
     * @param laser
     *              The laser being fired.
     * @return A list containing the updated coordinates where the laser travels,
     *        stopping at players and walls.
     */
    List<Coordinates> getLaserPath(List<Coordinates> coordinates, GridDirection direction, Laser laser);


    /**
     * Returns the game board.
     *
     * @return Returns the game board.
     */
    GameBoard getBoard();


    /**
     * Get the list of participating players.
     *
     * @return The list of players.
     */
    List<IPlayer> getPlayers();


    /**
     * Get the list of players that are able to act this round.
     *
     * @return The list of active players.
     */
    List<IPlayer> getActivePlayers();


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
     * Get the list of lasers that are currently capable of firing.
     *
     * @return The list of operational lasers.
     */
    List<Laser> getLasers();


    /**
     * Get the number for the currently executed register slot.
     *
     * @return The current register slot being executed.
     */
    int getCurrentSlotNumber();


    /**
     * Update the current slot number.
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
     * Get the games current status,
     * to determine GUI drawing mode.
     *
     * @return The current phase of the game.
     */
    GameStatus getTheCurrentGameStatus();


    /**
     * Update the current state of the game.
     *
     * @param status
     *              The new game status.
     * @throws IllegalArgumentException
     *      If the status is null (status == null),<br>
     *      or not a valid status (!GameStatus.validStatus(status)).
     */
    void setGameStatus(GameStatus status);


    /**
     * Get the amount of players that has one or more lives left.
     *
     * @return The number of players still in the game.
     */
    int getNumberOfPlayersLeftInTheGame();


    /**
     * Check if all the players are permanently out of the game.
     *
     * @return true if there are no more players left alive,
     *      false otherwise.
     */
    boolean gameOver();
}
