package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.IDeck;

import java.util.List;

public interface IGame {


    /**
     * Add the participating players,<br>
     * generate the game board,<br>
     * reset the variables and create the deck of program cards.
     */
    void initializeGame();


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
    List<IPlayer> getActivePlayers();


    /**
     * Get the list of participating players.
     *
     * @return The list of players.
     */
    List<IPlayer> getPlayers();


    /**
     * Shuffle the program cards deck.
     * Each player draw cards,
     * depending on how many damage tokens they have.
     */
    void dealOutProgramCards();


    /**
     * Get the games current status,
     * to determine GUI drawing mode.
     *
     * @return The current phase of the game.
     */
    GameStatus getTheCurrentGameStatus();


    /**
     * Returns the game board
     * @return Returns the game board
     */
    GameBoard getBoard();

    void setGameStatus(GameStatus status);

    void doTurn();

    void setUpTurn(IDeck[] selectedCards);
}
