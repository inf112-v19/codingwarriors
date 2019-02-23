package inf112.project.RoboRally.cards;

import java.util.ArrayList;
import java.util.Collection;

public interface IProgramRegister {











    int getSize();

    void addCardToRegisterAtSlotNumber(Integer position, ICard card);

    ICard getCardInSlotNumber(Integer number);

    void addCollectionOfCardsToRegister(Collection<ICard> listOfCards);

    boolean checkIsRegisterSlotNumberNLocked(Integer slotNumber);

    void lockRegisterSlotNumber(Integer slotNumber);

    int getNumberOfRegisterSlots();

    void unlockRegisterSlotNumberN(Integer slotNumber);

    void clearAllUnlockedCardsFromRegister();
}
