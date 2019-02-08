package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.objects.GridDirection;

import java.util.ArrayList;

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

    int getPlayerDamage();

    /**
     * Takes a list of cards, and adds them to the players hand.
     *
     * @param cards
     *              The cards to be added.
     */
    void receiveCards(ArrayList<ICard> cards);

    void addCardsToProgramRegister();

    /**
     * Player discards their hand.
     */
    void removeRemainingCardsInHand();

    ICard revealProgramCardForRegisterNumber(int registerNumber);

    void clearRegister();

    boolean wasDestroyedThisTurn();

    void respawnAtLastArchiveMarker();
}
