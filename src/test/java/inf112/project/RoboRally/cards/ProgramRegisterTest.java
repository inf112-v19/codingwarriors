package inf112.project.RoboRally.cards;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
            assertEquals(false, register.isRegisterSlotNumberNLocked(i));
        }
    }

    @Test
    public void gettingRegisterSizeShouldReturnCorrectSize() {
        assertEquals(0, register.getSize());
        ICard card = new Card(200, Action.FORWARD_3);
        register.addCardToRegister(card);
        assertEquals(1, register.getSize());
    }

    @Test
    public void shouldBeAbleToAddCardsToTheRegister() {
        assertEquals(0, register.getSize());
        ICard card = new Card(330, Action.FORWARD_1);

        register.addCardToRegister(card);
        assertEquals(1, register.getSize());
        assertEquals(card, register.getCardInSlotNumber(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingNullToTheRegisterShouldFail() {
        register.addCardToRegister(null);
    }

    @Test
    public void gettingCardFromSlotNumberNShouldReturnCorrectCard() {
        ICard card1 = new Card(100, Action.ROTATE_LEFT);
        ICard card2 = new Card(200, Action.ROTATE_RIGHT);
        ICard card3 = new Card(300, Action.FORWARD_1);
        ICard card4 = new Card(400, Action.FORWARD_2);
        ICard card5 = new Card(500, Action.FORWARD_3);
        register.addCardToRegister(card1);
        register.addCardToRegister(card2);
        register.addCardToRegister(card3);
        register.addCardToRegister(card4);
        register.addCardToRegister(card5);
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
        register.addCardToRegister(card);
        assertEquals(1, register.getSize());

        register.getCardInSlotNumber(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void gettingCardFromANegativeSlotNumberShouldFail() {
        ICard card = new Card(150, Action.ROTATE_RIGHT);
        register.addCardToRegister(card);
        assertEquals(1, register.getSize());

        register.getCardInSlotNumber(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void gettingCardFromSlotNumberNullShouldFail() {
        ICard card = new Card(150, Action.ROTATE_RIGHT);
        register.addCardToRegister(card);
        assertEquals(1, register.getSize());

        register.getCardInSlotNumber(null);
    }

    @Test
    public void addingACollectionOfCardsToTheRegisterShouldWork() {
        ArrayList<ICard> listOfCards = new ArrayList<>();
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

    @Test
    public void shouldBeAbleToLockRegisterSlots() {
        for (int i = 0; i < register.getSize(); i++) {
            assertEquals(false, register.isRegisterSlotNumberNLocked(i));
        }
        register.lockRegisterSlotNumber(4);
        assertEquals(true, register.isRegisterSlotNumberNLocked(4));
        register.lockRegisterSlotNumber(3);
        assertEquals(true, register.isRegisterSlotNumberNLocked(3));
        register.lockRegisterSlotNumber(2);
        assertEquals(true, register.isRegisterSlotNumberNLocked(2));
        register.lockRegisterSlotNumber(1);
        assertEquals(true, register.isRegisterSlotNumberNLocked(1));
        register.lockRegisterSlotNumber(0);
        assertEquals(true, register.isRegisterSlotNumberNLocked(0));
    }

}