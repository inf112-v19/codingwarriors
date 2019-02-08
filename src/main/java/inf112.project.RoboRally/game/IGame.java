package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;

public interface IGame {


    /**
     * Move the robots according to their programmed instructions.<br>
     * Also interact with board elements,
     * fire lasers and register flags.
     */
    void revealProgramCardsAndExecuteTheirCommands();


    /**
     * If there are no more players left alive,
     * or someone has visited all the flags,
     * the game is considered over.<br><br>
     *
     * If all the flags have been visited,
     * the players are prompted at the end of every turn
     * and asked if they want to keep playing for another round.
     *
     * @return true if there are no more players with life tokens left.<br>
     *         If all the flags have been visited:
     *         true/false depending on answer.<br>
     *         false otherwise.
     */
    boolean checkIfTheGameIsOver();


    /**
     * Add the participating players,<br>
     * generate the game board,<br>
     * reset the variables and create the deck of program cards.
     */
    void initializeGame();


    /**
     * Execute the main game,
     * and keep playing until the game is over.
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
     * Shuffle the program cards deck.
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
     * Checks if any players are standing on a wrench
     * or crossed wrench/hammer space,<br>
     * clears every players register,<br>
     * asks powered down players if they want to
     * stay powered down for another round,<br>
     * brings destroyed players back into the game
     * and resets the program cards deck.
     */
    void cleanUp();


    /**
     * Checks if a player is standing on a flag or a repair space.<br>
     * Flags are registered and archive locations are updated.
     */
    void registerFlagsAndRepairSites();


    /**
     * Board elements fire lasers,
     * followed by players firing their lasers.
     */
    void registerLaserDamage();


    /**
     * Ask each damaged player,
     * whether they would like to power down the next round.
     */
    void askPlayersIfTheyWantToPowerDown();


    /**
     * Get the games current status,
     * to determine GUI drawing mode.
     *
     * @return The current phase of the game.
     */
    GameStatus getTheCurrentGameStatus();


    /**
     * Get the player whos cards should be displayed.
     *
     * @return The currently acting player.
     */
    IPlayer getCurrentPlayer();
}
