package inf112.project.RoboRally.Actors;

import inf112.project.RoboRally.Cards.Action;
import inf112.project.RoboRally.Cards.ICard;
import inf112.project.RoboRally.objects.GridDirection;

import java.util.ArrayList;

public class Player implements IPlayer {
    private int lives = 3;
    private GridDirection playerDirection;
    private int x,y;

    public Player () {
        this.playerDirection = GridDirection.NORTH;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public GridDirection getPlayerDirection() {
        return playerDirection;
    }

    // Kan ikke GridDirection bli hentet inne i metoden,
    // istedet for å sende den som et parameter?
    @Override
    public void movePlayer(ICard card, GridDirection direction) { // Changed Card to ICard, and updated IPlayer
        if (card.getCommand() == Action.FORWARD_1)                // and the import statements in both classes.
            moveInDirection(direction, 1);
        if (card.getCommand() == Action.FORWARD_2)
            moveInDirection(direction, 2);
        if (card.getCommand() == Action.FORWARD_3)
            moveInDirection(direction, 3);
        if (card.getCommand() == Action.BACKWARDS)
            moveInDirection(opposite(direction), 1);
        if (card.getCommand() == Action.ROTATE_LEFT)
            rotateLeft(direction);
        if (card.getCommand() == Action.ROTATE_RIGHT)
            rotateRight(direction);
        if (card.getCommand() == Action.U_TURN)
            uTurn(direction);
    }

    @Override
    public int getPlayerDamage() {
        return 0;
    }

    @Override
    public void receiveCards(ArrayList<ICard> iCards) {

    }

    @Override
    public void addCardsToProgramRegister() {

    }

    @Override
    public void removeRemainingCardsInHand() {

    }

    @Override
    public ICard revealProgramCardForRegisterNumber(int registerNumber) {
        return null;
    }

    @Override
    public void clearRegister() {

    }

    @Override
    public boolean wasDestroyedThisTurn() {
        return false;
    }

    @Override
    public void respawnAtLastArchiveMarker() {

    }

    private GridDirection opposite(GridDirection direction) {
        switch (direction) {
            case NORTH: return GridDirection.SOUTH;
            case WEST: return GridDirection.EAST;
            case EAST: return GridDirection.WEST;
            case SOUTH: return GridDirection.NORTH;
            default: return null;
        }
    }

    private void moveInDirection(GridDirection direction, int steps) {
        switch (direction) {
            case NORTH:
                for (int i = 0; i < steps; i++) {
                    y = y + 1;
                    // should check validity of position
                }
            case WEST:
                for (int i = 0; i < steps; i++) {
                    x = x - 1;
                    // should check validity of position
                }
            case EAST:
                for (int i = 0; i < steps; i++) {
                    x = x + 1;
                    // should check validity of position
                }
            case SOUTH:
                for (int i = 0; i < steps; i++) {
                    y = y - 1;
                    // should check validity of position
                }
        }
    }


    private void uTurn(GridDirection direction) {
        switch (direction) {
            case NORTH: this.playerDirection = GridDirection.SOUTH;
            case WEST: this.playerDirection = GridDirection.EAST;
            case SOUTH: this.playerDirection = GridDirection.NORTH;
            case EAST: this.playerDirection = GridDirection.WEST;
        }
    }

    private void rotateLeft(GridDirection direction) {
        switch (direction) {
            case NORTH: this.playerDirection = GridDirection.WEST;
            case WEST: this.playerDirection = GridDirection.SOUTH;
            case SOUTH: this.playerDirection = GridDirection.EAST;
            case EAST: this.playerDirection = GridDirection.NORTH;
        }
    }

    private void rotateRight(GridDirection direction) {
        switch (direction) {
            case NORTH: this.playerDirection = GridDirection.EAST;
            case EAST: this.playerDirection = GridDirection.SOUTH;
            case SOUTH: this.playerDirection = GridDirection.WEST;
            case WEST: this.playerDirection = GridDirection.NORTH;
        }
    }
}
