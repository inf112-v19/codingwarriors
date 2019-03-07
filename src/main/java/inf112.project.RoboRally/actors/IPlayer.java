package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.objects.GridDirection;

import java.util.List;

public interface IPlayer {

    /**
     * @return x-coordinate of player
     */
    int getX();

    /**
     * @return y-coordinate of player
     */
    int getY();

    /**
     * @return The direction the player currently faces.
     */
    GridDirection getPlayerDirection();

    /**
     * Move the player as instructed on the given program card.
     *
     * @param card
     *             The card containing the instructions which are to be executed.
     */
    void movePlayer(ICard card);


    /**
     * Get the amount of damage the player has taken.
     *
     * @return The number of damage tokens.
     */
    int getPlayerDamage();


    /**
     * Deal one point of damage to this player.
     * Increments the number of damage tokens.
     */
    void takeOneDamage();


    /**
     * Discard one damage token
     */
    void discardOneDamage();


    /**
     * Check the amount of damage the player has sustained,
     * and trigger effects accordingly.
     */
    void assessCurrentDamage();


    /**
     *
     *
     *
     *
     */
    void destroyPlayer();


    /**
     * Lock N registers of the players register.
     *
     * @param numberOfRegisters
     *                          The number of registers to be locked.
     */
    void lockNRegisters(int numberOfRegisters);


    /**
     * Takes a list of cards, and adds them to the players hand.
     *
     * @param cards
     *              The cards to be added.
     */
    void receiveCards(List<ICard> cards);


    /**
     *
     *
     *
     */
    void addCardsToProgramRegister();


    /**
     * Player discards their hand.
     */
    void removeRemainingCardsInHand();


    /**
     *
     *
     *
     * @param registerNumber
     * @return
     */
    ICard revealProgramCardForRegisterNumber(int registerNumber);


    /**
     *
     *
     */
    void clearRegister();


    /**
     *
     *
     *
     * @return true if the player was destroyed<br>
     *         false otherwise.
     */
    boolean wasDestroyedThisTurn();


    /**
     *
     *
     *
     *
     */
    void respawnAtLastArchiveMarker();


    /**
     * Get the cards currently held by the player.
     *
     * @return The players deck of cards.
     */
    public IDeck getCardsInHand();


    /**
     * Get the name of the player.
     *
     * @return The players current name.
     */
    String getName();
    
    /**
     * A method for setting backup point to the given position
     */
    void setThisPointAsNewBackup();
    
    /**
     * A method for obtaining the number of flags that have been visited by the IPlayer
     * @return The number of flags visited
     */
    int getFlagsVisited();
    
    /**
     * Add a flag to the number of visited flags
     */
    void addNewFlagVisited();
    
    /**
     * A method for moving the player one step in a given direction
     * @param direction The direction to be moved
     */
    void movePlayer(GridDirection direction);
    
    /**
     * A method for rotating the player to the left (counter-clockwise)
     */
    void rotateLeft();
    
    /**
     * A method for rotating the player to the right (clockwise)
     */
    void rotateRight();
    
    /**
     * A method for rotating the player to the opposite direction
     */
    void uTurn();

    /**
     * For use in drawing the player
     * @return Texture location and name in string form ex. assets/textureName.png
     */
    String getTexture();
}
