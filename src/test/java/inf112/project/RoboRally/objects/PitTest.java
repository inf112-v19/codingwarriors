package inf112.project.RoboRally.objects;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class PitTest {
    private String level = "3C3R" +
            "..." +
            "..." +
            ".p.";
    private String walls = "" +
            "..." +
            "..." +
            "...";
    private GameBoard gameboard = new GameBoard(level, walls);

    @Test
    public void playerRespawnOnLastArchiveMarkerWhenFallingInPit() {
        Player player = new Player("foo", 0,0, Color.RED);
        player.movePlayer(GridDirection.EAST);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(0, player.getY());
        assertEquals(0, player.getX());
    }

    @Test
    public void playerTakesTwoDamageWhenFallingInPit() {
        Player player = new Player("foo", 0,0, Color.RED);
        player.movePlayer(GridDirection.EAST);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(2, player.getPlayerDamage());
    }

}