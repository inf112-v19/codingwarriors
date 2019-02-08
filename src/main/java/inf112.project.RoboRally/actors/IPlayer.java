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
     * @return player direction
     */
    GridDirection getPlayerDirection();

    /**
     * @param card player moves according to
     */
    void movePlayer(ICard card);

    int getPlayerDamage();

    void receiveCards(ArrayList<ICard> iCards);

    void addCardsToProgramRegister();

    void removeRemainingCardsInHand();

    ICard revealProgramCardForRegisterNumber(int registerNumber);

    void clearRegister();

    boolean wasDestroyedThisTurn();

    void respawnAtLastArchiveMarker();
}
