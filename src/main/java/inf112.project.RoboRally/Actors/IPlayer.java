package inf112.project.RoboRally.Actors;

import inf112.project.RoboRally.Cards.Card;
import inf112.project.RoboRally.objects.GridDirection;

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
    void movePlayer(Card card, GridDirection direction);
}
