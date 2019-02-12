package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.cards.Action;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.gui.Grid;
import inf112.project.RoboRally.objects.GridDirection;

import java.util.ArrayList;

public class Player implements IPlayer {
    private int lives = 3;
    private GridDirection playerDirection;
    private int x,y;
    private String name;
    private IDeck cardsInHand;
    private int numberOfDamageTokensRecieved;

    public Player(String name, int x, int y) {
        this.playerDirection = GridDirection.NORTH;
        this.x = x;
        this.y = y;
        this.name = name;
        this.cardsInHand = new Deck();
        this.numberOfDamageTokensRecieved = 0;
    }

    public Player(int x, int y) {
        this.playerDirection = GridDirection.NORTH;
        this.x=x;
        this.y=y;
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

    @Override
    public void movePlayer(ICard card) {
        GridDirection playersCurrentDirection = this.playerDirection;
        Action cardCommand = card.getCommand();
        switch (cardCommand) {
            case ROTATE_RIGHT:
                rotateRight();
            case ROTATE_LEFT:
                rotateLeft();
            case U_TURN:
                uTurn();
            case FORWARD_1:
                moveInDirection(playersCurrentDirection, 1);
            case FORWARD_2:
                moveInDirection(playersCurrentDirection, 2);
            case FORWARD_3:
                moveInDirection(playersCurrentDirection, 3);
            case BACKWARDS:
                moveInDirection(opposite(), 1);
        }
    }

    
    public void movePlayer(GridDirection direction) {
        if (direction == GridDirection.NORTH) {
            y++;
        } else if (direction == GridDirection.WEST) {
            x--;
        } else if (direction == GridDirection.SOUTH) {
            y--;
        } else if (direction == GridDirection.EAST) {
            x++;
        }
    }

    @Override
    public int getPlayerDamage() {
        return this.numberOfDamageTokensRecieved;
    }

    @Override
    public void takeOneDamage() {
        this.numberOfDamageTokensRecieved += 1;
        this.assessCurrentDamage();
    }

    @Override
    public void assessCurrentDamage() {
        int currentDamageTaken = this.numberOfDamageTokensRecieved;
        switch (currentDamageTaken) {
            case 5: this.lockNRegisters(1);
            case 6: this.lockNRegisters(2);
            case 7: this.lockNRegisters(3);
            case 8: this.lockNRegisters(4);
            case 9: this.lockNRegisters(5);
            case 10: this.destroyPlayer();
            default: break;
        }
    }

    @Override
    public void destroyPlayer() {
        this.lives -= 1;
        if (this.lives <= 0) {

        }
    }

    @Override
    public void lockNRegisters(int numberOfRegisters) {
        // Lock registers from 1 to numberOfRegisters.
    }

    @Override
    public void receiveCards(ArrayList<ICard> cards) {
        this.cardsInHand.addCollectionOfCardsToDeck(cards);
    }

    @Override
    public void addCardsToProgramRegister() {

    }

    @Override
    public void removeRemainingCardsInHand() {
        this.cardsInHand.removeAllCardsFromDeck();
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

    @Override
    public IDeck getCardsInHand() {
        return cardsInHand;
    }

    private GridDirection opposite() {
        GridDirection playersCurrentDirection = this.playerDirection;
        switch (playersCurrentDirection) {
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


    private void uTurn() {
        GridDirection playersCurrentDirection = this.playerDirection;
        switch (playersCurrentDirection) {
            case NORTH: this.playerDirection = GridDirection.SOUTH;
            case WEST: this.playerDirection = GridDirection.EAST;
            case SOUTH: this.playerDirection = GridDirection.NORTH;
            case EAST: this.playerDirection = GridDirection.WEST;
            default: break;
        }
    }

    private void rotateLeft() {
        GridDirection playersCurrentDirection = this.playerDirection;
        switch (playersCurrentDirection) {
            case NORTH: this.playerDirection = GridDirection.WEST;
            case WEST: this.playerDirection = GridDirection.SOUTH;
            case SOUTH: this.playerDirection = GridDirection.EAST;
            case EAST: this.playerDirection = GridDirection.NORTH;
            default: break;
        }
    }

    private void rotateRight() {
        GridDirection playersCurrentDirection = this.playerDirection;
        switch (playersCurrentDirection) {
            case NORTH: this.playerDirection = GridDirection.EAST;
            case EAST: this.playerDirection = GridDirection.SOUTH;
            case SOUTH: this.playerDirection = GridDirection.WEST;
            case WEST: this.playerDirection = GridDirection.NORTH;
            default: break;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
