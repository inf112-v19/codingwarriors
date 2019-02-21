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
    public boolean isRegisterSlotNumberNLocked(Integer number) {
        if (number == null
            || number < 0
            || number > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Number is not a valid slot");
        }
        return this.isLocked.get(number);
    }

    @Override
    public void addCardToRegister(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("null is not a valid card");
        }
        this.register.addCardToDeck(card);
    }

    @Override
    public ICard getCardInSlotNumber(Integer number) {
        if (number == null
                || number < 0
                || number > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid slot number");
        }
        return this.register.getCardAtPosition(number);
    }

    @Override
    public void addCollectionOfCardsToRegister(Collection<ICard> listOfCards) {
        if (listOfCards == null || listOfCards.size() > NUMBER_OF_SLOTS) {
            throw new IllegalArgumentException("Not a valid collection of cards");
        }
        int slotNumber = 0;
        for (ICard card : listOfCards) {
            if (!checkIfRegisterSlotNumberNIsLocked(slotNumber)) {
                this.register.addCardToDeckAtPosition(slotNumber, card);
            }
            slotNumber++;
        }
    }

    @Override
    public boolean checkIfRegisterSlotNumberNIsLocked(Integer slotNumber) {
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


}
