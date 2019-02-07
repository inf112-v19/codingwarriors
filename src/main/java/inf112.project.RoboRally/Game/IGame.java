package inf112.project.RoboRally.Game;

import inf112.project.RoboRally.Actors.IPlayer;

import java.util.ArrayList;

public interface IGame {


    /**
     *
     * @return
     */
    boolean checkIfTheGameHasBeenWon();


    /**
     * Add the participating players,<br>
     * generate the game board,<br>
     * and create the deck of program cards.
     */
    void initializeGame();


    /**
     *
     */
    void playRoboRally();


    /**
     * Initialize and run the game.
     */
    void startGame();

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
     */
    int calculateTheNumberOfCardsThePlayerCanDraw(IPlayer player);


    /**
     * Get the list of players that are able to act this round.
     *
     * @return The list of active players.
     */
    ArrayList<IPlayer> getActivePlayers();


    /**
     * Get the list of participating players.
     *
     * @return The list of players.
     */
    ArrayList<IPlayer> getPlayers();


    /**
     * If a player is powered down,<br>
     * remove their damage tokens,<br>
     * and remove them from the list of active players.
     */
    void processPoweredDownPlayers();


    /**
     * Each player draw cards,
     * depending on how many damage tokens they have.
     */
    void dealOutProgramCards();


    /**
     * Each player inputs a program,
     * and discard their remaining cards.
     */
    void playersProgramRobots();


    /**
     * Ask each damaged player,
     * whether they would like to power down the next round.
     */
    void askPlayersIfTheyWantToPowerDown();


}
