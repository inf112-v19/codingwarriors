package inf112.project.RoboRally.cards;

import java.util.ArrayList;
import java.util.Collection;

public interface IProgramRegister {











    int getSize();

    boolean isRegisterSlotNumberNLocked(Integer number);

    void addCardToRegister(ICard card);

    ICard getCardInSlotNumber(Integer number);

    void addCollectionOfCardsToRegister(Collection<ICard> listOfCards);

    boolean checkIfRegisterSlotNumberNIsLocked(Integer slotNumber);

    void lockRegisterSlotNumber(Integer slotNumber);
}
