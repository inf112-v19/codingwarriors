package inf112.project.RoboRally;

import static org.junit.Assert.assertEquals;

import inf112.project.RoboRally.board.GameBoard;
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
    public void columns() {
        assertEquals(2,testBoard1.getColums());
    }

    @Test
    public void rows() {
        assertEquals(4,testBoard1.getRows());
    }
}
