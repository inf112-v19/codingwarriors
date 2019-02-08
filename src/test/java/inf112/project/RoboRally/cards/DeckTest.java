package inf112.project.RoboRally.cards;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {


    @Test
    public void createNewDeckCreatesANewDeck() {
        IDeck deck = new Deck();
        deck.createProgramCardsDeck();
        assertEquals(84, deck.getSize());
    }


    /*
    public static IDeck deck;


    @Before
    public void setUp() throws Exception {
        IDeck deck = new Deck();
    }

    @Test
    public void sizeOfNewDeckIsZero() {
        assertEquals(0, deck.getSize());
    }

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