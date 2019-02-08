package inf112.project.RoboRally.Actors;

import inf112.project.RoboRally.Cards.ICard;
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
     * @param direction of the player
     */
    void movePlayer(ICard card, GridDirection direction);

    int getPlayerDamage();

    void receiveCards(ArrayList<ICard> iCards);

    void addCardsToProgramRegister();

    void removeRemainingCardsInHand();

    ICard revealProgramCardForRegisterNumber(int registerNumber);

    void clearRegister();

    boolean wasDestroyedThisTurn();

    void respawnAtLastArchiveMarker();
}
