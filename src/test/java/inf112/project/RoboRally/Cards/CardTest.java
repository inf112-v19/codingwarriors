package inf112.project.RoboRally.Cards;

import org.junit.Test;

import static inf112.project.RoboRally.Cards.Action.*;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGettingCardPriority() {
        assertEquals(200, new Card(200, ROTATE_RIGHT).getPriority());
        assertEquals(480, new Card(480, FORWARD_1).getPriority());
    }

    @Test
    public void testGettingCardCommand() {
        assertEquals(ROTATE_LEFT, new Card(100, ROTATE_LEFT).getCommand());
        assertEquals(U_TURN, new Card(200, U_TURN).getCommand());
    }


    @Test
    public void testComparingCards() {
        assertTrue(new Card(200, ROTATE_LEFT).compareTo(new Card(100, ROTATE_LEFT)) > 0);
        assertTrue(new Card(100, FORWARD_1).compareTo(new Card(250, ROTATE_RIGHT)) < 0);
        assertTrue(new Card(300, ROTATE_LEFT).compareTo(new Card(300, ROTATE_LEFT)) == 0);

        assertTrue(new Card(300, ROTATE_RIGHT).compareTo(new Card(260, ROTATE_LEFT)) > 0);
        assertTrue(new Card(260, ROTATE_LEFT).compareTo(new Card(220, ROTATE_LEFT)) > 0);
        assertTrue(new Card(300, ROTATE_LEFT).compareTo(new Card(220, ROTATE_LEFT)) > 0);
    }
}