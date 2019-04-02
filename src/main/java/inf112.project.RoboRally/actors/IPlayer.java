package inf112.project.RoboRally.actors;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.objects.GridDirection;
import inf112.project.RoboRally.objects.Laser;

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
     * Get the players priority.<br>
     * The ID for when the player was added into the game,
     * and how the players determine positions when reentering the game.
     *
     * @return The players priority in the game.
     */
    int getPriority();


    /**
     * @return The direction the player currently faces.
     */
    GridDirection getPlayerDirection();


    /**
     * Move the player as instructed on the given program card.
     *
     * @param card
     *             The card containing the instructions which are to be executed.
     *
     * @throws IllegalArgumentException
     *      if card == null.
     */
    void movePlayer(ICard card);


    /**
     * Get the amount of damage the player has taken.
     *
     * @return The number of damage tokens.
     */
    int getPlayerDamage();


    /**
     * Deal one point of damage to this player.<br>
     * Increments the number of damage tokens.
     */
    void takeOneDamage();


    /**
     * This player repairs one point of damage.<br>
     * Decrements the number of damage tokens.
     */
    void removeOneDamage();


    /**
     * Check the amount of damage the player has sustained,
     * and lock register slots or destroy the player if necessary.
     */
    void assessCurrentDamage();


    /**
     * Unlock N register slots in the players register.
     *
     * @param numberOfSlotsToUnlock
     *                      The number of register slots to be unlocked.
     *
     * @throws IllegalArgumentException <br>
     *      if numberOfSlotsToUnlock == null,<br>
     *      or numberOfSlotsToUnlock < 0,<br>
     *      or numberOfSlotsToUnlock > register.getSize().
     */
    void unlockNRegisters(Integer numberOfSlotsToUnlock);


    /**
     * Decrement the players life-total by one.
     */
    void destroyPlayer();


    /**
     * Lock N register slots in the players register.
     *
     * @param numberOfSlotsToLock
     *                          The number of register slots to be locked.
     *
     * @throws IllegalArgumentException <br>
     *      if numberOfSlotsToLock == null,<br>
     *      or numberOfSlotsToLock < 0,<br>
     *      or numberOfSlotsToLock > register.getSize().
     */
    void lockNRegisters(Integer numberOfSlotsToLock);


    /**
     * Takes a list of cards, and adds them to the players hand.
     *
     * @param cards
     *              The cards to be added.
     * @throws IllegalArgumentException
     *      if cards == null.
     */
    void addCardsToPlayersHand(List<ICard> cards);


    /**
     * Takes a list of cards, and adds them to the players register.
     *
     * @param cards
     *              The list of cards to add to the register.
     *
     * @throws IllegalArgumentException
     *       if the list of cards is null (cards == null),
     *       or if it contains too many cards (cards.size() > register.getSize()).
     */
    void addListOfCardsToProgramRegister(List<ICard> cards);


    /**
     * Takes a deck of cards,
     * and adds the cards to the register.
     *
     * @param deck
     *              The deck of cards to be added.
     * @throws IllegalArgumentException
     *      if deck == null,<br>
     *      or deck contains too many cards
     *     (deck.getSize() > register.getNumberOfRegisterSlots).
     */
    void addADeckOfCardsToTheProgramRegister(IDeck deck);


    /**
     * Player discards their hand.
     */
    void removeRemainingCardsInHand();


    /**
     * Reveal the card in the players chosen registry slot.<br>
     * The card is not removed from the registry.
     *
     * @param registerNumber
     *                      The register slot whose contents are to be revealed.
     * @return A reference to the card residing in the register slot, if any.<br>
     *
     * @throws IllegalArgumentException
     *        if the slotNumber is negative (slotNumber < 0),<br>
     *        slotNumber is too high (slotNumber > register.getSize()),<br>
     *        or if slotNumber is null (slotNumber = null).
     */
    ICard revealProgramCardForRegisterNumber(Integer registerNumber);


    /**
     * Remove all unlocked cards from this players registry.<br>
     * Cards in locked slots, remain in their positions.
     *
     * @return A deck with the removed cards.
     */
    IDeck clearRegister();


    /**
     * Get the number of register slots that remains unlocked.
     *
     * @return The number of unlocked register slots.
     */
    int getNumberOfUnlockedRegisterSlots();


    /**
     * Check if the player was destroyed during the current turn.
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
    IDeck getCardsInHand();


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
     * @param direction
     *              The direction to be moved
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


    /**
     * Get the number of lives the player currently has.
     *
     * @return the number of lives the player has left.
     */
    int getNumberOfLivesRemaining();


    /**
     * Check whether a player is still alive or not.
     *
     * @return true if the player has more lives left,<br>
     *       false if (lives <= 0).
     */
    boolean hasLifeLeft();

    Laser getLaser();

    Color getColor();
}
