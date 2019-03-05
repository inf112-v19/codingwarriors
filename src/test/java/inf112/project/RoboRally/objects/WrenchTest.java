package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class WrenchTest {
    private String level = "3C3R" +
            ".w." +
            "w.w" +
            ".w.";
    private GameBoard gameboard = new GameBoard(level);

    @Test
    public void playerWithZeroDamageTokensStillHasZeroDamageTokenOnWrench() {
        Player player = new Player("foo", 0,0);
        player.movePlayer(GridDirection.NORTH);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(0, player.getPlayerDamage());
        assertEquals(1, player.getBackupY());
        assertEquals(0, player.getBackupX());
    }

    @Test
    public void playerLosesOneDamageTokenOnWrench() {
        Player player = new Player("foo", 0,0);
        player.takeOneDamage();
        player.takeOneDamage();
        player.movePlayer(GridDirection.NORTH);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(1, player.getPlayerDamage());
    }

    @Test
    public void playerBackupUpdatesOnWrench() {
        Player player = new Player("foo", 0,0);
        player.movePlayer(GridDirection.NORTH);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(1, player.getBackupY());
        assertEquals(0, player.getBackupX());
    }

}