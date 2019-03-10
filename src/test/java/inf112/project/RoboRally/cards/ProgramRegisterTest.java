package inf112.project.RoboRally.cards;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProgramRegisterTest {


    private IProgramRegister register;
    
    @Before
    public void setUp() throws Exception {
        this.register = new ProgramRegister();
    }


    @Test
    public void creatingANewRegisterShouldMakeAnEmptyDeckAndBooleanArray() {
        IProgramRegister register = new ProgramRegister();
        assertEquals(0, register.getSize());
        for (int i = 0; i < 5; i++) {
            assertEquals(false, register.checkIsRegisterSlotNumberNLocked(i));
        }
    }




    @Test
    public void gettingRegisterSizeShouldReturnCorrectSize() {
        assertEquals(0, register.getSize());
        ICard card = new Card(200, Action.FORWARD_3);
        List<ICard> cards = new ArrayList<>();
        cards.add(card);
        register.addCollectionOfCardsToRegister(cards);
        assertEquals(1, register.getSize());
    }





    @Test
    public void replacingACardShouldLeaveTheNewCardInTheCorrectSlot() {
        assertEquals(0, register.getSize());
        ICard card1 = new Card(330, Action.FORWARD_1);
        ICard card2 = new Card(200, Action.ROTATE_RIGHT);
        ICard card3 = new Card(300, Action.FORWARD_1);
        ICard card4 = new Card(400, Action.FORWARD_2);
        ICard card5 = new Card(500, Action.FORWARD_3);
        List<ICard> listOfCards = new ArrayList<>();
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        register.addCollectionOfCardsToRegister(listOfCards);

        assertEquals(5, register.getSize());
        assertEquals(card1, register.getCardInSlotNumber(0));
        assertEquals(card2, register.getCardInSlotNumber(1));
        assertEquals(card3, register.getCardInSlotNumber(2));
        assertEquals(card4, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));

        register.replaceTheCardInRegisterSlotNumberNWithThisCard(0, card5);
        assertEquals(5, register.getSize());
        assertEquals(card5, register.getCardInSlotNumber(0));
        assertEquals(card5, register.getCardInSlotNumber(4));

        register.replaceTheCardInRegisterSlotNumberNWithThisCard(1, card4);
        assertEquals(card4, register.getCardInSlotNumber(1));
        assertEquals(card4, register.getCardInSlotNumber(3));

        register.replaceTheCardInRegisterSlotNumberNWithThisCard(2, card3);
        assertEquals(card3, register.getCardInSlotNumber(2));

        register.replaceTheCardInRegisterSlotNumberNWithThisCard(3, card2);
        assertEquals(card2, register.getCardInSlotNumber(3));
        assertEquals(card4, register.getCardInSlotNumber(1));

        register.replaceTheCardInRegisterSlotNumberNWithThisCard(4, card1);
        assertEquals(5, register.getSize());
        assertEquals(card1, register.getCardInSlotNumber(4));
        assertEquals(card5, register.getCardInSlotNumber(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void replacingACardAtTooHighASlotNumberShouldFail() {
        ICard card = new Card(200, Action.ROTATE_RIGHT);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(register.getSize(), card);
    }

    @Test (expected = IllegalArgumentException.class)
    public void replacingACardAtANegativeSlotNumberShouldFail() {
        ICard card = new Card(200, Action.ROTATE_RIGHT);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(-1, card);
    }

    @Test (expected = IllegalArgumentException.class)
    public void replacingACardAtSlotNumberNullShouldFail() {
        ICard card = new Card(200, Action.ROTATE_RIGHT);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(null, card);
    }

    @Test (expected = IllegalArgumentException.class)
    public void replacingACardInTheRegisterWithNullShouldFail() {
        ICard card = new Card(2,Action.ROTATE_RIGHT);
        List<ICard> cards = new ArrayList<>();
        cards.add(card);
        register.addCollectionOfCardsToRegister(cards);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(0, null);
    }





    @Test
    public void gettingCardFromSlotNumberNShouldReturnCorrectCard() {
        ICard card1 = new Card(100, Action.ROTATE_LEFT);
        ICard card2 = new Card(200, Action.ROTATE_RIGHT);
        ICard card3 = new Card(300, Action.FORWARD_1);
        ICard card4 = new Card(400, Action.FORWARD_2);
        ICard card5 = new Card(500, Action.FORWARD_3);
        List<ICard> listOfCards = new ArrayList<>();
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        register.addCollectionOfCardsToRegister(listOfCards);
        assertEquals(5, register.getSize());
        
        assertEquals(card1, register.getCardInSlotNumber(0));
        assertEquals(card2, register.getCardInSlotNumber(1));
        assertEquals(card3, register.getCardInSlotNumber(2));
        assertEquals(card4, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));
    }

    @Test (expected = IllegalArgumentException.class)
    public void gettingCardFromTooHighASlotNumberShouldFail() {
        ICard card = new Card(150, Action.ROTATE_RIGHT);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(0, card);
        assertEquals(1, register.getSize());

        register.getCardInSlotNumber((register.getNumberOfRegisterSlots() + 1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void gettingCardFromANegativeSlotNumberShouldFail() {
        ICard card = new Card(150, Action.ROTATE_RIGHT);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(0, card);
        assertEquals(1, register.getSize());

        register.getCardInSlotNumber(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void gettingCardFromSlotNumberNullShouldFail() {
        ICard card = new Card(150, Action.ROTATE_RIGHT);
        register.replaceTheCardInRegisterSlotNumberNWithThisCard(0, card);
        assertEquals(1, register.getSize());

        register.getCardInSlotNumber(null);
    }






    @Test
    public void addingACollectionOfCardsToTheRegisterShouldAddAllCardsToTheRegister() {
        List<ICard> listOfCards = new ArrayList<>();
        ICard card1 = new Card(100, Action.ROTATE_LEFT);
        ICard card2 = new Card(200, Action.ROTATE_RIGHT);
        ICard card3 = new Card(300, Action.FORWARD_1);
        ICard card4 = new Card(400, Action.FORWARD_2);
        ICard card5 = new Card(500, Action.FORWARD_3);
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        register.addCollectionOfCardsToRegister(listOfCards);

        assertEquals(5, register.getSize());
        assertEquals(card1, register.getCardInSlotNumber(0));
        assertEquals(card2, register.getCardInSlotNumber(1));
        assertEquals(card3, register.getCardInSlotNumber(2));
        assertEquals(card4, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingACollectionOfNullShouldNotWork() {
        register.addCollectionOfCardsToRegister(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingACollectionOfTooManyCardsShouldFail() {
        ICard card = new Card(120, Action.ROTATE_LEFT);
        List<ICard> listOfCards = new ArrayList<>();
        for (int i = 0; i <= register.getNumberOfRegisterSlots(); i++) {
            listOfCards.add(card);
        }
        register.addCollectionOfCardsToRegister(listOfCards);
    }







    @Test
    public void lockingARegisterSlotShouldLockTheCorrectSlot() {
        for (int i = 0; i < register.getSize(); i++) {
            assertEquals(false, register.checkIsRegisterSlotNumberNLocked(i));
        }
        register.lockRegisterSlotNumber(4);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(4));
        register.lockRegisterSlotNumber(3);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(3));
        register.lockRegisterSlotNumber(2);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(2));
        register.lockRegisterSlotNumber(1);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(1));
        register.lockRegisterSlotNumber(0);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void lockingARegisterSlotShouldFailIfNumberIsNull() {
        register.lockRegisterSlotNumber(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void lockingARegisterSlotShouldFailIfNumberIsTooHigh() {
        register.lockRegisterSlotNumber((register.getNumberOfRegisterSlots() + 1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void lockingARegisterSlotShouldFailIfNumberIsNegative() {
        register.lockRegisterSlotNumber(-1);
    }





    @Test
    public void checkingIfRegisterSlotNumberNIsLockedShouldReturnCorrectResult() {
        for (int number = 0; number < register.getNumberOfRegisterSlots(); number++) {
            assertEquals(false, register.checkIsRegisterSlotNumberNLocked(number));
        }
        register.lockRegisterSlotNumber(4);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(4));
        register.lockRegisterSlotNumber(1);
        assertEquals(true, register.checkIsRegisterSlotNumberNLocked(1));

        register.unlockRegisterSlotNumberN(4);
        assertEquals(false, register.checkIsRegisterSlotNumberNLocked(4));
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkingIfRegisterSlotNumberNIsLockedShouldFailIfNumberIsNull() {
        register.checkIsRegisterSlotNumberNLocked(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkingIfRegisterSlotNumberNIsLockedShouldFailIfNumberIsTooHigh() {
        register.checkIsRegisterSlotNumberNLocked((register.getNumberOfRegisterSlots() + 1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkingIfRegisterSlotNumberNIsLockedShouldFailIfNumberIsNegative() {
        register.checkIsRegisterSlotNumberNLocked(-1);
    }





    @Test
    public void unlockingRegisterSlotNumberNShouldUnlockTheCorrectRegisterSlot() {
        for (int slotNumber = 0; slotNumber < register.getNumberOfRegisterSlots(); slotNumber++) {
            assertEquals(false, register.checkIsRegisterSlotNumberNLocked(slotNumber));
            register.lockRegisterSlotNumber(slotNumber);
            assertEquals(true, register.checkIsRegisterSlotNumberNLocked(slotNumber));
            register.unlockRegisterSlotNumberN(slotNumber);
            assertEquals(false, register.checkIsRegisterSlotNumberNLocked(slotNumber));
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryingToUnlockARegisterSlotNumberThatIsTooHighShouldFail() {
        register.unlockRegisterSlotNumberN((register.getNumberOfRegisterSlots() + 1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryingToUnlockARegisterSlotNumberThatIsNegativeShouldFail() {
        register.unlockRegisterSlotNumberN(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryingToUnlockRegisterSlotNumberNullShouldFail() {
        register.unlockRegisterSlotNumberN(null);
    }




    @Test
    public void removingAllUnlockedCardsFromTheRegisterShouldReplaceAllCardsInSlotsThatAreNotLocked() {
        List<ICard> listOfCards = new ArrayList<>();
        ICard card1 = new Card(100, Action.ROTATE_LEFT);
        ICard card2 = new Card(200, Action.ROTATE_RIGHT);
        ICard card3 = new Card(300, Action.FORWARD_1);
        ICard card4 = new Card(400, Action.FORWARD_2);
        ICard card5 = new Card(500, Action.FORWARD_3);
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        register.addCollectionOfCardsToRegister(listOfCards);

        assertEquals(5, register.getSize());
        assertEquals(card1, register.getCardInSlotNumber(0));
        assertEquals(card2, register.getCardInSlotNumber(1));
        assertEquals(card3, register.getCardInSlotNumber(2));
        assertEquals(card4, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));

        register.removeAllUnlockedCardsFromTheRegister();
        assertEquals(5, register.getSize());

        ICard placeHolderCard = register.getCardInSlotNumber(0);
        assertEquals(placeHolderCard, register.getCardInSlotNumber(0));
        assertEquals(placeHolderCard, register.getCardInSlotNumber(1));
        assertEquals(placeHolderCard, register.getCardInSlotNumber(2));
        assertEquals(placeHolderCard, register.getCardInSlotNumber(3));
        assertEquals(placeHolderCard, register.getCardInSlotNumber(4));
    }

    @Test
    public void removingAllUnlockedCardsFromTheRegisterShouldNotReplaceCardsInLockedSlots() {
        List<ICard> listOfCards = new ArrayList<>();
        ICard card1 = new Card(100, Action.ROTATE_LEFT);
        ICard card2 = new Card(200, Action.ROTATE_RIGHT);
        ICard card3 = new Card(300, Action.FORWARD_1);
        ICard card4 = new Card(400, Action.FORWARD_2);
        ICard card5 = new Card(500, Action.FORWARD_3);
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        register.addCollectionOfCardsToRegister(listOfCards);

        register.lockRegisterSlotNumber(4);
        register.lockRegisterSlotNumber(1);

        register.removeAllUnlockedCardsFromTheRegister();
        assertEquals(5, register.getSize());
        ICard placeHolderCard = register.getCardInSlotNumber(0);

        assertEquals(placeHolderCard, register.getCardInSlotNumber(0));
        assertEquals(card2, register.getCardInSlotNumber(1));
        assertEquals(placeHolderCard, register.getCardInSlotNumber(2));
        assertEquals(placeHolderCard, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));

        // Second pass after a slot is unlocked
        register.unlockRegisterSlotNumberN(1);
        register.removeAllUnlockedCardsFromTheRegister();
        assertEquals(5, register.getSize());
        ICard placeHolderCardSecondPass = register.getCardInSlotNumber(0);

        assertEquals(placeHolderCardSecondPass, register.getCardInSlotNumber(0));
        assertEquals(placeHolderCardSecondPass, register.getCardInSlotNumber(1));
        assertEquals(placeHolderCardSecondPass, register.getCardInSlotNumber(2));
        assertEquals(placeHolderCardSecondPass, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));
    }

    @Test
    public void removingAllUnlockedCardsFromTheRegisterShouldReturnADeckContainingTheRemovedCards() {
        List<ICard> listOfCards = new ArrayList<>();
        ICard card1 = new Card(150, Action.ROTATE_RIGHT);
        ICard card2 = new Card(220, Action.ROTATE_RIGHT);
        ICard card3 = new Card(340, Action.U_TURN);
        ICard card4 = new Card(450, Action.FORWARD_2);
        ICard card5 = new Card(520, Action.FORWARD_1);
        listOfCards.add(card1);
        listOfCards.add(card2);
        listOfCards.add(card3);
        listOfCards.add(card4);
        listOfCards.add(card5);
        register.addCollectionOfCardsToRegister(listOfCards);

        register.lockRegisterSlotNumber(3);
        register.lockRegisterSlotNumber(4);

        IDeck removedCards = register.removeAllUnlockedCardsFromTheRegister();
        int numberOfCardsRemoved = 3;
        assertEquals(numberOfCardsRemoved, removedCards.getSize());
        assertEquals(card1, removedCards.getCardAtPosition(0));
        assertEquals(card2, removedCards.getCardAtPosition(1));
        assertEquals(card3, removedCards.getCardAtPosition(2));
        assertEquals(card4, register.getCardInSlotNumber(3));
        assertEquals(card5, register.getCardInSlotNumber(4));
    }
}