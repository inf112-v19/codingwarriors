package inf112.project.RoboRally.cards;

import org.junit.Test;

import static inf112.project.RoboRally.cards.Action.*;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void getCardPriorityShouldReturnCorrectPriority() {
        int priority1 = 200;
        int priority2 = 480;
        Card card1 = new Card(priority1, ROTATE_RIGHT);
        Card card2 = new Card(priority2, FORWARD_1);

        assertEquals(priority1, card1.getPriority());
        assertEquals(priority2, card2.getPriority());
    }

    @Test
    public void getCardCommandShouldReturnCorrectAction() {
        Action action1 = ROTATE_LEFT;
        Action action2 = U_TURN;
        Card card1 = new Card(100, action1);
        Card card2 = new Card(200, action2);

        assertEquals(action1, card1.getCardCommand());
        assertEquals(action2, card2.getCardCommand());
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


    @Test
    public void cardToStringShouldBeCorrectlyFormatted() {
        ICard card1 = new Card(250, ROTATE_RIGHT);
        assertEquals("Priority: 250\n Action: ROTATE_RIGHT\n", card1.toString());

        ICard card2 = new Card(600, FORWARD_2);
        assertEquals("Priority: 600\n Action: FORWARD_2\n", card2.toString());
    }
}