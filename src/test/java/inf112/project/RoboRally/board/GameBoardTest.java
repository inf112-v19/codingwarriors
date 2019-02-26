package inf112.project.RoboRally.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import inf112.project.RoboRally.objects.ConveyorBelt;
import inf112.project.RoboRally.objects.Floor;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
    private String testLevel1;
    private GameBoard testBoard1;

    @Before
    public void setup() {
        testLevel1 =
                "4C2R" +
                "r..." +
                "....";
        testBoard1 = new GameBoard(testLevel1);
    }

    @Test
    public void numberOfColumnsInBoard() {
        assertEquals(4,testBoard1.getColumns());
    }

    @Test
    public void numberOfRowsInBoard() {
        assertEquals(2,testBoard1.getRows());
    }

    @Test
    public void ObjectPositionOnTopLeft() {
        assertTrue(testBoard1.getObject(0,1) instanceof ConveyorBelt);
    }

    @Test
    public void ObjectPositionOnTopRight() {
        assertTrue(testBoard1.getObject(3,1) instanceof Floor);
    }

    @Test
    public void ObjectPositionOnBottomLeft() {
        assertTrue(testBoard1.getObject(0,0) instanceof Floor);
    }

    @Test
    public void ObjectPositionOnBottomRight() {
        assertTrue(testBoard1.getObject(3,0) instanceof Floor);
    }

    @Test
    public void validMove() {
        assertTrue(testBoard1.moveValid(0,1));
        assertTrue(testBoard1.moveValid(1,1));
        assertTrue(testBoard1.moveValid(0,0));
        assertTrue(testBoard1.moveValid(1,0));
    }

    @Test
    public void notAValidMove() {
        assertFalse(testBoard1.moveValid(-1,1));
        assertFalse(testBoard1.moveValid(3,-1));
        assertFalse(testBoard1.moveValid(4,0));
        assertFalse(testBoard1.moveValid(3,2));
    }

}
