package inf112.project.RoboRally.Cards;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testGetSize() {
        assertEquals(0, new Deck().getSize());
        IDeck deck = new Deck().createProgramCardsDeck();



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
}