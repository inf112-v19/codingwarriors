package inf112.project.RoboRally.gui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridTest {
    private Tile screen;
    private Grid testGrid;

    @Before
    public void setup() {
        int startOfX = 100;
        int endOfX = 600;
        int startOfY = 200;
        int endOfY = 2000;
        screen = new Tile(startOfX, endOfX, startOfY, endOfY);
        testGrid = new Grid(screen, 10, 18);
    }

    @Test
    public void widthOfTilesInGrid() {
        assertEquals(50, testGrid.getTileWidth());
    }

    @Test
    public void heightOfTilesInGrid() {
        assertEquals(100, testGrid.getTileHeight());
    }

    @Test
    public void positionInsideTileCorners() {
        assertTrue(testGrid.PositionIsInsideScreen(100,200));
        assertTrue(testGrid.PositionIsInsideScreen(100,2000));
        assertTrue(testGrid.PositionIsInsideScreen(600,200));
        assertTrue(testGrid.PositionIsInsideScreen(600,2000));
    }

    @Test
    public void positionInsideTile() {
        assertTrue(testGrid.PositionIsInsideScreen(300,1000));
    }

    @Test
    public void positionOutsideTile() {
        assertFalse(testGrid.PositionIsInsideScreen(0,500));
        assertFalse(testGrid.PositionIsInsideScreen(700,500));
        assertFalse(testGrid.PositionIsInsideScreen(500,0));
        assertFalse(testGrid.PositionIsInsideScreen(500,3000));
    }
}
