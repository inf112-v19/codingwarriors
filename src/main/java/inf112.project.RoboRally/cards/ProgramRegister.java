package inf112.project.RoboRally.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProgramRegister implements IProgramRegister{

    /**
     * How to use.
     * A standard register has five register slots,
     * numbered starting from zero.
     * (register slot 0 == slot 1),
     * (register slot 4 == slot 5),
     * (register slot 5 == out of bounds).
     *
     * Single cards can be added or removed from valid positions.
     * Alternatively, a collection of ICards or a deck of cards can be used,
     * whereupon as many cards as possible will be added to the register.
     *
     * Register slots can be locked,
     * to prevent the cards from being removed or overwritten.
     * Likewise, a locked register slot can be unlocked to enable
     * the card to be removed or changed once more.
     * All register slots are unlocked by default.
     *
     * There are methods for getting the number of cards in the register,
     * getting the maximum number of slots available,
     * getting the number of register slots that are still unlocked,
     * checking if a specific register slot is locked,
     * replace individual cards with others in specific register slots,
     * and for removing all the unlocked cards from the register.
     */


    private IDeck register;
    private List<Boolean> isLocked;
    private final int NUMBER_OF_SLOTS = 5;
    private int currentRegisterSlot;


    public ProgramRegister() {
        this.register = new Deck();
        this.isLocked = new ArrayList<>();
        this.currentRegisterSlot = 0;
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            this.isLocked.add(false);
        }
    }

    @Override
    public int getSize() {
        return this.register.getSize();
    }

    @Override
    public int getNumberOfRegisterSlots() {
        return this.NUMBER_OF_SLOTS;
    }

    @Override
    public int getCurrentRegisterSlot() {
        return this.currentRegisterSlot;
    }

    @Override
    public ICard replaceTheCardInRegisterSlotNumberNWithThisCard(Integer slotNumber, ICard card) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber >= register.getSize()) {
            throw new IllegalArgumentException("Not a valid number");
        }
        if (card == null) {
            throw new IllegalArgumentException("null is not a valid card");
        }
        if (checkIsTheRegisterSlotNumberNLocked(slotNumber)) {
            throw new IllegalArgumentException("This register is locked!\nUnable to replace card");
        }
        ICard removedCard = this.register.replaceCardAtPosition(slotNumber, card);
        return removedCard;
    }

    @Override
    public ICard getCardInSlotNumber(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber >= this.register.getSize()) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        return this.register.getCardAtPosition(slotNumber);
    }

    @Override
    public void addCollectionOfCardsToRegister(Collection<ICard> listOfCards) {
        if (listOfCards == null || listOfCards.size() > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid collection of cards");
        }
        for (ICard card : listOfCards) {
            if (this.currentRegisterSlot < this.NUMBER_OF_SLOTS) {
                this.addCardToCurrentRegisterSlot(card);
            }
        }
    }

    @Override
    public void addADeckOfCardsToTheRegister(IDeck deckOfCards) {
        if (deckOfCards == null || deckOfCards.getSize() > this.getNumberOfRegisterSlots()) {
            throw new IllegalArgumentException("Not a valid deck of cards");
        }
        List<ICard> listOfCards = new ArrayList<>();
        for (ICard card : deckOfCards) {
            listOfCards.add(card);
        }
        this.addCollectionOfCardsToRegister(listOfCards);
    }

    @Override
    public boolean checkIsTheRegisterSlotNumberNLocked(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber >= NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        return this.isLocked.get(slotNumber);
    }

    @Override
    public void lockRegisterSlotNumber(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber >= NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        if (!this.checkIsTheRegisterSlotNumberNLocked(slotNumber)) {
            this.isLocked.set(slotNumber, true);
            if (slotNumber < this.register.getSize()) {
                this.currentRegisterSlot--;
                // The current register slot should
                // now point to the last unlocked register slot.
            }
        }
    }

    @Override
    public void unlockRegisterSlotNumberN(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        if (this.checkIsTheRegisterSlotNumberNLocked(slotNumber)) {
            this.isLocked.set(slotNumber, false);
            if (this.currentRegisterSlot < this.register.getSize()) {
                this.currentRegisterSlot++;
            }
        }
    }

    @Override
    public IDeck removeAllUnlockedCardsFromTheRegister() {
        IDeck removedCards = new Deck();
        while(this.currentRegisterSlot > 0) {
            ICard removedCard = this.register.removeCard(0);
            removedCards.addCardToDeck(removedCard);
            this.currentRegisterSlot--;
        }
        return removedCards;
    }

    @Override
    public int numberOfUnlockedRegisterSlots() {
        int numberOfUnlockedSlots = 0;
        for (int slotNumber = 0; slotNumber < this.isLocked.size(); slotNumber++) {
            if (!checkIsTheRegisterSlotNumberNLocked(slotNumber)) {
                numberOfUnlockedSlots++;
            }
        }
        return numberOfUnlockedSlots;
    }

    @Override
    public boolean addCardToCurrentRegisterSlot(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("Not a valid card");
        }
        if (this.currentRegisterSlot >= this.NUMBER_OF_SLOTS
                || this.currentRegisterSlot < 0) {
            return false;
        }
        this.register.addCardToDeckAtPosition(this.currentRegisterSlot++, card);
        return true;
    }

    @Override
    public ICard removeCardFromRegisterSlot(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber >= this.currentRegisterSlot) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        this.currentRegisterSlot--;
        return this.register.removeCard(slotNumber);
    }
}
