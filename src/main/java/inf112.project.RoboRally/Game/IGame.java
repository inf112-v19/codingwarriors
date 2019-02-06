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





    ArrayList<IPlayer> getActivePlayers();



    ArrayList<IPlayer> getPlayers();

}
