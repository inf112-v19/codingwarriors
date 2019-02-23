package inf112.project.RoboRally.cards;

import java.util.ArrayList;
import java.util.Collection;

public class ProgramRegister implements IProgramRegister{


    private IDeck register;
    private ArrayList<Boolean> isLocked;
    private final int NUMBER_OF_SLOTS = 5;


    public ProgramRegister() {
        this.register = new Deck();
        this.isLocked = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            this.isLocked.add(false);
        }
    }

    @Override
    public int getSize() {
        return this.register.getSize();
    }

    @Override
    public void addCardToRegisterAtSlotNumber(Integer slotNumber, ICard card) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber > register.getSize()) {
            throw new IllegalArgumentException("Not a valid number");
        }
        if (card == null) {
            throw new IllegalArgumentException("null is not a valid card");
        }
        this.register.addCardToDeckAtPosition(slotNumber, card);
    }

    @Override
    public ICard getCardInSlotNumber(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        return this.register.getCardAtPosition(slotNumber);
    }

    @Override
    public void addCollectionOfCardsToRegister(Collection<ICard> listOfCards) {
        if (listOfCards == null || listOfCards.size() > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid collection of cards");
        }
        int slotNumber = 0;
        for (ICard card : listOfCards) {
            if (!checkIsRegisterSlotNumberNLocked(slotNumber)) {
                this.addCardToRegisterAtSlotNumber(slotNumber, card);
            }
            slotNumber++;
        }
    }

    @Override
    public boolean checkIsRegisterSlotNumberNLocked(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        return this.isLocked.get(slotNumber);
    }

    @Override
    public void lockRegisterSlotNumber(Integer slotNumber) {
        if (slotNumber == null
                || slotNumber < 0
                || slotNumber > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        this.isLocked.set(slotNumber, true);
    }

    @Override
    public int getNumberOfRegisterSlots() {
        return this.NUMBER_OF_SLOTS;
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
    public void clearAllUnlockedCardsFromRegister() {
        ICard placeHolder = new Card(-1, Action.IF_YOU_SEE_THIS_SOMETHING_WENT_WRONG);
        for (int slotNumber = 0; slotNumber < register.getSize(); slotNumber++) {
            if (!checkIsRegisterSlotNumberNLocked(slotNumber)) {
                register.removeCard(slotNumber);
                register.addCardToDeckAtPosition(slotNumber, placeHolder);
            }
        }
    }
}
