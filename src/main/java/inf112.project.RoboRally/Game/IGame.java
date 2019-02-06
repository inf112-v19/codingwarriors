package inf112.project.RoboRally.Game;

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




}
