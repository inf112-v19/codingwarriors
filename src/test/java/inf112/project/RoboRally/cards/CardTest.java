package inf112.project.RoboRally.cards;

import org.junit.Test;

import static inf112.project.RoboRally.cards.Action.*;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGettingCardPriority() {
        assertEquals(200, new Card(200, ROTATE_RIGHT).getPriority());
        assertEquals(480, new Card(480, FORWARD_1).getPriority());
    }

    @Test
    public void getCardCommandShouldReturnCorrectAction() {
        assertEquals(ROTATE_LEFT, new Card(100, ROTATE_LEFT).getCardCommand());
        assertEquals(U_TURN, new Card(200, U_TURN).getCardCommand());
    }

    @Test
    public void cardsShouldBeComparedCorrectly() {
        Card card1 = new Card(200, ROTATE_LEFT);

        Card card2 = new Card(100, ROTATE_LEFT);
        Card card3 = new Card(100, FORWARD_1);
        Card card4 = new Card(100, FORWARD_2);

        Card card5 = new Card(300, ROTATE_RIGHT);
        Card card6 = new Card(260, ROTATE_LEFT);
        Card card7 = new Card(220, ROTATE_LEFT);


        assertTrue(card1.compareTo(card1) == 0); // Reflexive

        assertTrue(card2.compareTo(card3) == 0); // x.compareTo(y) == 0;
        assertTrue(card3.compareTo(card2) == 0); // y.compareTo(x) == 0;

        assertTrue(card1.compareTo(card6) < 0); // x.compareTo(y) < 0;
        assertTrue(card2.compareTo(card1) < 0); // z.compareTo(x) < 0;
        assertTrue(card2.compareTo(card6) < 0); // z.compareTo(y) < 0;

        assertTrue(card5.compareTo(card6) > 0); // Transitive
        assertTrue(card6.compareTo(card7) > 0);
        assertTrue(card5.compareTo(card7) > 0);

        assertTrue(card2.compareTo(card3) == 0); // x.compareTo(y) == 0;
        assertTrue(card2.compareTo(card4) == 0); // x.compareTo(z) == 0;
        assertTrue(card3.compareTo(card4) == 0); // y.compareTo(z) == 0;
    }





}