package inf112.project.RoboRally.cards;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    private final int NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK = 84;
    private IDeck deck;

    @Before
    public void setUp() throws Exception {
        this.deck = new Deck();
    }

    @Test
    public void newlyCreatedDecksShouldBeEmpty() {
        IDeck newDeck = new Deck();
        assertEquals(0, newDeck.getSize());
    }

    @Test
    public void newlyCreatedProgramCardsDeckShouldHaveCorrectAmountOfCards() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());
    }

    @Test
    public void getSizeShouldReturnCorrectAmountOfCardsInADeck() {
        deck.addCardToDeck(new Card(200, Action.FORWARD_3));
        assertEquals(1, deck.getSize());

        deck.addCardToDeck(new Card(100, Action.ROTATE_LEFT));
        assertEquals(2, deck.getSize());

        deck.addCardToDeck(new Card(300, Action.U_TURN));
        assertEquals(3, deck.getSize());

        deck.removeAllCardsFromDeck();
        assertEquals(0, deck.getSize());
    }

    @Test
    public void removingAllCardsFromADeckShouldLeaveTheDeckEmpty() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());

        deck.removeAllCardsFromDeck();
        assertEquals(0, deck.getSize());
    }

    @Test
    public void enhancedForLoopShouldWork() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());
        
        try {
            for (ICard card : deck) {}
        } catch (NullPointerException e) {
            fail("Deck iterator is not working properly");
        }
    }

    @Test
    public void sortingADeckShouldLeaveItSortedByPriority() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(300, Action.ROTATE_LEFT);
        ICard card3 = new Card(180, Action.FORWARD_2);

        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        deck.sortDeckAfterCardPriority();
        assertEquals(card2, deck.getCardAtPosition(0));
        assertEquals(card1, deck.getCardAtPosition(1));
        assertEquals(card3, deck.getCardAtPosition(2));
    }

    @Test
    public void getCardAtPositionShouldReturnCardAtTheGivenPosition() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(100, Action.FORWARD_2);
        ICard card3 = new Card(420, Action.FORWARD_2);

        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        assertEquals(card1, deck.getCardAtPosition(0));
        assertEquals(card2, deck.getCardAtPosition(1));
        assertEquals(card3, deck.getCardAtPosition(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCardAtPositionShouldFailIfPositionIsInvalid() {
        deck.getCardAtPosition(-1);
        deck.getCardAtPosition(4);
    }

    @Test
    public void addingACollectionOfCardsToADeckShouldWork() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(100, Action.FORWARD_2);
        ICard card3 = new Card(420, Action.FORWARD_2);

        newDeck.addCardToDeck(card1);
        newDeck.addCardToDeck(card2);
        newDeck.addCardToDeck(card3);

        assertEquals(3, newDeck.getSize());
        assertEquals(0, deck.getSize());

        deck.addCollectionOfCardsToDeck(newDeck.handOutNCards(2));

    }

    @Test
    public void transferNCardsFromOneDeckToAnotherShouldWork() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(220, Action.ROTATE_LEFT);
        ICard card2 = new Card(150, Action.FORWARD_2);
        ICard card3 = new Card(490, Action.FORWARD_2);

        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        assertEquals(3, deck.getSize());
        assertEquals(0, newDeck.getSize());

        int numberOfCardsToTransfer = 2;
        deck.transferNCardsFromThisDeckToTargetDeck(numberOfCardsToTransfer, newDeck);

        assertEquals(numberOfCardsToTransfer, newDeck.getSize());
        assertEquals(card1, newDeck.getCardAtPosition(0));
        assertEquals(card2, newDeck.getCardAtPosition(1));
    }

    /*

    @Test
    public void handOutCards() {
        fail();
    }

    @Test
    public void createDeck() {
        fail();
    }

    @Test
    public void shuffle() {
        fail();
    }
    */
}