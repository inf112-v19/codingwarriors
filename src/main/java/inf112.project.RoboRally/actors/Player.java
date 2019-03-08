package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.cards.*;
import inf112.project.RoboRally.objects.Flag;
import inf112.project.RoboRally.objects.GridDirection;

import java.util.List;

public class Player implements IPlayer {
    private int lives;
    private GridDirection playerDirection;
    private int x,y;
    private int backupX, backupY;
    private String name;
    private IDeck cardsInHand;
    private int numberOfDamageTokensRecieved;
    private IProgramRegister register;
    private int flagsToVisit;
    private int flagsVisited;


    public Player(String name, int x, int y) {
        setDefaultValues();
        this.x = x;
        this.y = y;
        this.backupX = this.x;
        this.backupY = this.y;
        this.name = name;
    }

    // For future use, if we need more constructors for the Player
    private void setDefaultValues() {
        this.x = 0;
        this.y = 0;
        this.name = "";
        this.playerDirection = GridDirection.NORTH;
        this.cardsInHand = new Deck();
        this.numberOfDamageTokensRecieved = 0;
        this.lives = 3;
        this.register = new ProgramRegister();
        this.backupX = this.x;
        this.backupY = this.y;
        this.flagsToVisit = Flag.getNumberOfFlags();
        this.flagsVisited = 0;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getBackupX() {
        return backupX;
    }

    public int getBackupY() {
        return backupY;
    }

    // Possibly redundant
    // TODO Remove if redundant
    public void setNewBackupPoint(int x, int y) {
        this.backupX=x;
        this.backupY=y;
    }

    @Override
    public void setThisPointAsNewBackup() {
        this.backupX=this.x;
        this.backupY=this.y;
    }

    @Override
    public int getFlagsVisited() {
        return flagsVisited;
    }

    @Override
    public void addNewFlagVisited() {
        flagsVisited++;
    }

    @Override
    public GridDirection getPlayerDirection() {
        return playerDirection;
    }

    @Override
    public void movePlayer(ICard card) {
        GridDirection playersCurrentDirection = this.playerDirection;
        Action cardCommand = card.getCardCommand();
        switch (cardCommand) {
            case ROTATE_RIGHT: rotateRight();
            break;
            case ROTATE_LEFT: rotateLeft();
            break;
            case U_TURN: uTurn();
            break;
            case FORWARD_1: moveInDirection(playersCurrentDirection, 1);
            break;
            case FORWARD_2: moveInDirection(playersCurrentDirection, 2);
            break;
            case FORWARD_3: moveInDirection(playersCurrentDirection, 3);
            break;
            case BACKWARDS: moveInDirection(opposite(), 1);
            break;
        }
    }

    @Override
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
    public void removeOneDamage() {
        if (this.numberOfDamageTokensRecieved > 0) {
            this.numberOfDamageTokensRecieved -= 1;
        }
    }

    @Override
    public void assessCurrentDamage() {
        int currentDamageTaken = this.numberOfDamageTokensRecieved;
        switch (currentDamageTaken) {
            case 5: this.lockNRegisters(1); break;
            case 6: this.lockNRegisters(2); break;
            case 7: this.lockNRegisters(3); break;
            case 8: this.lockNRegisters(4); break;
            case 9: this.lockNRegisters(5); break;
            case 10: this.destroyPlayer(); break;
            default: break;
        }
    }

    @Override
    public void destroyPlayer() {
        this.lives -= 1;
        if (this.lives <= 0) {
            //TODO out of the game...
        }
    }

    @Override
    public void lockNRegisters(int numberOfRegisters) {
        int registerSlot = (this.register.getNumberOfRegisterSlots() - 1);
        while (numberOfRegisters > 0) {
            this.register.lockRegisterSlotNumber(registerSlot);
            registerSlot--;
            numberOfRegisters--;
        }
    }

    @Override
    public void receiveCards(List<ICard> cards) {
        this.cardsInHand.addCollectionOfCardsToDeck(cards);
    }

    @Override
    public void addListOfCardsToProgramRegister(List<ICard> cards) {
        if (cards == null || cards.size() > this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Invalid list of cards");
        }
        this.register.addCollectionOfCardsToRegister(cards);
    }

    @Override
    public void removeRemainingCardsInHand() {
        this.cardsInHand.removeAllCardsFromDeck();
    }

    @Override
    public ICard revealProgramCardForRegisterNumber(Integer registerNumber) {
        if (registerNumber == null
                || registerNumber < 0
                || registerNumber >= this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Invalid register number");
        }
        return this.register.getCardInSlotNumber(registerNumber);
    }

    @Override
    public void clearRegister() {
        this.register.clearAllUnlockedCardsFromRegister();
    }

    @Override
    public boolean wasDestroyedThisTurn() {
        return false;
    }

    @Override
    public void respawnAtLastArchiveMarker() {
        this.x=backupX;
        this.y=backupY;
        takeOneDamage();
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
                break;
            case WEST:
                for (int i = 0; i < steps; i++) {
                    x = x - 1;
                    // should check validity of position
                }
                break;
            case EAST:
                for (int i = 0; i < steps; i++) {
                    x = x + 1;
                    // should check validity of position
                }
                break;
            case SOUTH:
                for (int i = 0; i < steps; i++) {
                    y = y - 1;
                    // should check validity of position
                }
                break;
        }
    }

    @Override
    public void uTurn() {
        this.playerDirection = playerDirection.invert();
    }

    @Override
    public String getTexture() {
        return "assets/player_one.png";
    }

    @Override
    public void rotateLeft() {
        this.playerDirection = playerDirection.rotateLeft();
    }

    @Override
    public void rotateRight() {
        this.playerDirection = playerDirection.rotateRight();
    }

    public String getName() {
        return this.name;
    }
    
    public int getFlagsToVisit() {
        return flagsToVisit;
    }
    
    // For use with leaderboard, may be removed if unnecessary
    public int getRemainingFlagsToVisit() {
        return flagsToVisit-flagsVisited;
    }
}
