package inf112.project.RoboRally.objects;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WrenchTest {
    private String level = "3C3R" +
            ".W." +
            "w.w" +
            ".W.";
    private String walls = "" +
            "..." +
            "..." +
            "...";
    private GameBoard gameboard = new GameBoard(level, walls);

    @Test
    public void playerWithZeroDamageTokensStillHasZeroDamageTokenOnWrench() {
        Player player = new Player("foo", 0,0, Color.RED);
        player.movePlayer(GridDirection.NORTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(0, player.getPlayerDamage());
        assertEquals(1, player.getBackupY());
        assertEquals(0, player.getBackupX());
        player.movePlayer(GridDirection.SOUTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        player.movePlayer(GridDirection.EAST);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        assertEquals(0, player.getPlayerDamage());
    }

    @Test
    public void playerLosesOneDamageTokenOnWrench() {
        Player player = new Player("foo", 0,0, Color.BLACK);
        player.takeOneDamage();
        player.takeOneDamage();
        //Coordinates playerCoordinates = player.movePlayer(GridDirection.NORTH).get(0);
        //player.setCoordinates(playerCoordinates);
        player.movePlayer(GridDirection.NORTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));

        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(1, player.getPlayerDamage());
        //Coordinates newPlayerCoordinates = player.movePlayer(GridDirection.SOUTH).get(0);
        //player.setCoordinates(newPlayerCoordinates);
        player.movePlayer(GridDirection.SOUTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));

        //Coordinates lastPlayerCoordinates = player.movePlayer(GridDirection.EAST).get(0);
        //player.setCoordinates(lastPlayerCoordinates);
        player.movePlayer(GridDirection.EAST);
        player.setCoordinates(player.getPathOfPlayer().get(0));

        IObjects tile2 = gameboard.getObject(player.getX(), player.getY());
        tile2.doAction(player);
        assertEquals(0, player.getPlayerDamage());
    }

    @Test
    public void playerBackupUpdatesOnWrench() {
        Player player = new Player("foo", 0,0, Color.RED);
        player.movePlayer(GridDirection.NORTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(1, player.getBackupY());
        assertEquals(0, player.getBackupX());

        player.movePlayer(GridDirection.SOUTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));

        player.movePlayer(GridDirection.EAST);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        IObjects tile2 = gameboard.getObject(player.getX(), player.getY());

        tile2.doAction(player);
        assertEquals(0, player.getBackupY());
        assertEquals(1, player.getBackupX());
    }


    @Test
    public void wrenchCauseNoDamageAndSpeed() {
        Player player = new Player("foo", 0,0, Color.RED);
        player.movePlayer(GridDirection.NORTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        assertEquals(0, tile.getDamage());
        assertEquals(0, tile.getSpeed());
        player.movePlayer(GridDirection.EAST);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        player.movePlayer(GridDirection.SOUTH);
        player.setCoordinates(player.getPathOfPlayer().get(0));
        tile = gameboard.getObject(player.getX(), player.getY());
        assertEquals(0, tile.getDamage());
        assertEquals(0, tile.getSpeed());
    }


}