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
     * You can only add a collection of ICards to the register,
     * (ArrayLists and other classes that implement the collection interface)
     * but it is possible to replace the contents of a register slot, with a new card,
     * if the slot already contains a card.
     *
     * Removing all the cards from the register will overwrite existing
     * cards in unlocked slots with placeholders.
     * (Too ensure that other cards remain in their designated slots)
     * Each register slot can be locked to prevent the card from being overwritten.
     * Unlocking a register slot reverses the effect of locking.
     *
     * You can access the card in a specific register slot at any time,
     * provided that the slot contains a card.
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
        /*
        if (this.register.isEmpty()) {
            this.register.addCollectionOfCardsToDeck(listOfCards);
        } else {
            int slotNumber = 0;
            for (ICard card : listOfCards) {
                if (!checkIsTheRegisterSlotNumberNLocked(slotNumber)) {
                    this.replaceTheCardInRegisterSlotNumberNWithThisCard(slotNumber, card);
                }
                slotNumber++;
            }
        }
         */
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
        this.isLocked.set(slotNumber, true);
        if (slotNumber < this.register.getSize()) {
            this.currentRegisterSlot--;
            // The current register slot should
            // now point to the last unlocked register slot.
        }
    }

    @Override
    public void unlockRegisterSlotNumberN(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        this.isLocked.set(slotNumber, false);
    }

    @Override
    public IDeck removeAllUnlockedCardsFromTheRegister() {
        IDeck removedCards = new Deck();
        while(this.currentRegisterSlot > 0) {
            ICard removedCard = this.register.removeCard(0);
            removedCards.addCardToDeck(removedCard);
            this.currentRegisterSlot--;
        }
/*
        ICard placeHolder = new Card(-1, Action.IF_YOU_SEE_THIS_SOMETHING_WENT_WRONG);
        for (int slotNumber = 0; slotNumber < register.getSize(); slotNumber++) {
            if (!checkIsTheRegisterSlotNumberNLocked(slotNumber)) {
                ICard removedCard = this.register.replaceCardAtPosition(slotNumber,
                                                                        placeHolder);
                removedCards.addCardToDeck(removedCard);
            }
        }*/
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
    public void addCardToCurrentRegisterSlot(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("Not a valid card");
        }
        if (this.currentRegisterSlot >= this.NUMBER_OF_SLOTS
                || this.currentRegisterSlot < 0) {
            throw new IllegalArgumentException("Not a valid register slot");
        }
        this.register.addCardToDeckAtPosition(this.currentRegisterSlot++, card);
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
