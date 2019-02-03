package inf112.project.RoboRally.Cards;

import org.junit.Test;

import static inf112.project.RoboRally.Cards.Action.*;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGettingCardPriority() {
        assertEquals(200, new Card(200, ROTATE_RIGHT).getPriority());
    }

    @Test
    public void testGettingCardCommand() {
        assertEquals(ROTATE_LEFT, new Card(100, ROTATE_LEFT).getCommand());
    }
}