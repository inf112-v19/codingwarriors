package inf112.project.RoboRally.gui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileTest {
    private Tile testTile;

    @Before
    public void setup() {
        int startOfX = 20;
        int startOfY = 40;
        int endOfX = 50;
        int endOfY = 45;
        testTile = new Tile(startOfX,endOfX,startOfY,endOfY);
    }

    @Test
    public void startOfX() {
        assertEquals(20,testTile.getStartX());
    }

    @Test
    public void startOfY() {
        assertEquals(40,testTile.getStartY());
    }

    @Test
    public void endOfX() { assertEquals(50,testTile.getEndX()); }

    @Test
    public void endOfY() { assertEquals(45,testTile.getEndY()); }

    @Test
    public void positionInsideTileCorners() {
        assertTrue(testTile.positionInsideTile(20,40));
        assertTrue(testTile.positionInsideTile(20,45));
        assertTrue(testTile.positionInsideTile(50,40));
        assertTrue(testTile.positionInsideTile(50,45));
    }

    @Test
    public void positionInsideTile() {
        assertTrue(testTile.positionInsideTile(30,42));
    }

    @Test
    public void positionOutsideTile() {
        assertFalse(testTile.positionInsideTile(0,42));
        assertFalse(testTile.positionInsideTile(100,42));
        assertFalse(testTile.positionInsideTile(40,0));
        assertFalse(testTile.positionInsideTile(40,100));
    }
    
}
