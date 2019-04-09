package inf112.project.RoboRally.actors;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.cards.*;
import inf112.project.RoboRally.objects.GridDirection;
import inf112.project.RoboRally.objects.Laser;

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
    private int flagsVisited;
    private boolean wasDestroyedThisTurn;
    private Laser laser;
    private Color color;

    private static int counter = 0;
    private int priority;

    public Player(String name, int x, int y, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.backupX = this.x;
        this.backupY = this.y;
        this.name = name;
        this.playerDirection = GridDirection.NORTH;
        this.cardsInHand = new Deck();
        this.numberOfDamageTokensRecieved = 0;
        this.lives = 3;
        this.register = new ProgramRegister();
        this.flagsVisited = 0;
        this.wasDestroyedThisTurn = false;
        this.laser = new Laser(1, this);
        this.priority = counter++;
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
    public int getPriority() {
        return  this.priority;
    }

    @Override
    public GridDirection getPlayerDirection() {
        return playerDirection;
    }

    @Override
    public void movePlayer(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("Not a valid card");
        }
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
    public boolean equals(Object obj) {
        Player player = (Player) obj;
        if (x == player.getX() && y == player.getY())
            return true;
        return false;
    }

    @Override
    public int getPlayerDamage() {
        return this.numberOfDamageTokensRecieved;
    }

    @Override
    public void takeOneDamage() {
        this.numberOfDamageTokensRecieved += 1;
        System.out.println(this.name + " has " + numberOfDamageTokensRecieved
        + " damage tokens, and has " + lives + " lives remaining.");
        this.assessCurrentDamage();
    }


    @Override
    public void removeOneDamage() {
        if (this.numberOfDamageTokensRecieved > 0) {
            this.numberOfDamageTokensRecieved -= 1;
            this.assessCurrentDamage();
        }
    }

    @Override
    public void assessCurrentDamage() {
        int currentDamageTaken = this.numberOfDamageTokensRecieved;
        switch (currentDamageTaken) {
            case 5: this.lockNRegistersAndUnlockMRegisters(1, 4);
                    break;
            case 6: this.lockNRegistersAndUnlockMRegisters(2, 3);
                    break;
            case 7: this.lockNRegistersAndUnlockMRegisters(3, 2);
                    break;
            case 8: this.lockNRegistersAndUnlockMRegisters(4, 1);
                    break;
            case 9: this.lockNRegistersAndUnlockMRegisters(5, 0);
                    break;
            default: this.unlockNRegisters(5); break;
        }
    }

    /**
     * Handles the locking and unlocking of registers when the player is damaged.
     *
     * @param numberOfRegistersToLock
     *                              The number of registers that should be locked.
     * @param numberOfRegistersToUnlock
     *                              The number of registers that should be unlocked.
     *
     * @throws IllegalArgumentException
     *       if numberOfRegistersToLock == null,
     *       or numberOfRegistersToUnLock == null,
     *       or numberOfRegistersToLock < 0,
     *       or numberOfRegistersToUnLock < 0,
     *       or numberOfRegistersToLock > register.getSize(),
     *       or numberOfRegistersToUnLock > register.getSize().
     */
    private void lockNRegistersAndUnlockMRegisters(Integer numberOfRegistersToLock,
                                                  Integer numberOfRegistersToUnlock) {
        if (numberOfRegistersToLock == null
                || numberOfRegistersToLock < 0
                || numberOfRegistersToLock > this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Not a valid register to lock");
        }
        if (numberOfRegistersToUnlock == null
                || numberOfRegistersToUnlock < 0
                || numberOfRegistersToUnlock > this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Not a valid register to unlock");
        }
        this.lockNRegisters(numberOfRegistersToLock);
        this.unlockNRegisters(numberOfRegistersToUnlock);
    }


    @Override
    public void unlockNRegisters(Integer numberOfSlotsToUnlock) {
        if (numberOfSlotsToUnlock == null
                || numberOfSlotsToUnlock < 0
                || numberOfSlotsToUnlock > this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Not a valid number of slots");
        }
        for (int slotNumber = 0; slotNumber < numberOfSlotsToUnlock; slotNumber++) {
            this.register.unlockRegisterSlotNumberN(slotNumber);
        }
    }

    @Override
    public void destroyPlayer() {
        this.lives -= 1;
        this.numberOfDamageTokensRecieved = 0; // Reset damage
        this.assessCurrentDamage(); // unlock registers
        this.wasDestroyedThisTurn = true;
    }

    @Override
    public void lockNRegisters(Integer numberOfSlotsToLock) {
        if (numberOfSlotsToLock == null
                || numberOfSlotsToLock < 0
                || numberOfSlotsToLock > this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Not a valid number of slots");
        }
        int registerSlot = (this.register.getNumberOfRegisterSlots() - 1);
        while (numberOfSlotsToLock > 0) {
            this.register.lockRegisterSlotNumber(registerSlot);
            registerSlot--;
            numberOfSlotsToLock--;
        }
    }

    @Override
    public void addCardsToPlayersHand(List<ICard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("List of cards is invalid");
        }
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
    public void addADeckOfCardsToTheProgramRegister(IDeck deck) {
        if (deck == null || deck.getSize() > this.register.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Invalid deck of cards");
        }
        this.register.addADeckOfCardsToTheRegister(deck);
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
    public IDeck clearRegister() {
        return this.register.removeAllUnlockedCardsFromTheRegister();
    }

    @Override
    public int getNumberOfUnlockedRegisterSlots() {
        return this.register.numberOfUnlockedRegisterSlots();
    }


    @Override
    public boolean wasDestroyedThisTurn() {
        return this.wasDestroyedThisTurn;
    }

    @Override
    public void respawnAtLastArchiveMarker() {
        this.x=backupX;
        this.y=backupY;
        wasDestroyedThisTurn = false;
        takeOneDamage(); // Take two damage
        takeOneDamage();
    }

    @Override
    public IDeck getCardsInHand() {
        return cardsInHand;
    }

    private GridDirection opposite() {
        return playerDirection.invert();
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
    public void rotateLeft() { this.playerDirection = playerDirection.rotateLeft(); }

    @Override
    public void rotateRight() { this.playerDirection = playerDirection.rotateRight(); }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public int getNumberOfLivesRemaining() {
        return this.lives;
    }

    @Override
    public boolean hasLifeLeft() {
        return this.lives > 0;
    }

    @Override
    public Laser getLaser() {
        return laser;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(getX(), getY());
    }
}

