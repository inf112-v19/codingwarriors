package inf112.project.RoboRally.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import inf112.project.RoboRally.Board.GameBoard;
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
                "2C4R" +
                "r..." +
                "....";
        testBoard1 = new GameBoard(testLevel1);
    }

    @Test
    public void numberOfColumnsInBoard() {
        assertEquals(2,testBoard1.getColums());
    }

    @Test
    public void numberOfRowsInBoard() {
        assertEquals(4,testBoard1.getRows());
    }

    @Test
    public void ObjectPositionOnTopLeft() {
        assertTrue(testBoard1.getObject(0,0) instanceof ConveyorBelt);
    }

    @Test
    public void ObjectPositionOnTopRight() {
        assertTrue(testBoard1.getObject(3,0) instanceof Floor);
    }

    @Test
    public void ObjectPositionOnBottomLeft() {
        assertTrue(testBoard1.getObject(0,1) instanceof Floor);
    }

    @Test
    public void ObjectPositionOnBottomRight() {
        assertTrue(testBoard1.getObject(3,1) instanceof Floor);
    }

}
