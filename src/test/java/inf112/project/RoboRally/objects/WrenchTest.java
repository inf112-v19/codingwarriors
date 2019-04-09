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
        Coordinates playerCoordinates = player.movePlayer(GridDirection.NORTH).get(0);
        player.setCoordinates(playerCoordinates);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(0, player.getPlayerDamage());
        assertEquals(1, player.getBackupY());
        assertEquals(0, player.getBackupX());
        Coordinates newPlayerCoordinates = player.movePlayer(GridDirection.SOUTH).get(0);
        player.setCoordinates(newPlayerCoordinates);
        Coordinates lastPlayerCoordinates = player.movePlayer(GridDirection.EAST).get(0);
        player.setCoordinates(lastPlayerCoordinates);
        assertEquals(0, player.getPlayerDamage());
    }

    @Test
    public void playerLosesOneDamageTokenOnWrench() {
        Player player = new Player("foo", 0,0, Color.BLACK);
        player.takeOneDamage();
        player.takeOneDamage();
        Coordinates playerCoordinates = player.movePlayer(GridDirection.NORTH).get(0);
        player.setCoordinates(playerCoordinates);

        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(1, player.getPlayerDamage());
        Coordinates newPlayerCoordinates = player.movePlayer(GridDirection.SOUTH).get(0);
        player.setCoordinates(newPlayerCoordinates);

        Coordinates lastPlayerCoordinates = player.movePlayer(GridDirection.EAST).get(0);
        player.setCoordinates(lastPlayerCoordinates);

        IObjects tile2 = gameboard.getObject(player.getX(), player.getY());
        tile2.doAction(player);
        assertEquals(0, player.getPlayerDamage());
    }

    @Test
    public void playerBackupUpdatesOnWrench() {
        Player player = new Player("foo", 0,0, Color.RED);
        List<Coordinates> playerCoordinates = player.movePlayer(GridDirection.NORTH);
        Coordinates playerCoord = playerCoordinates.get(playerCoordinates.size()-1);
        player.setCoordinates(playerCoord);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        tile.doAction(player);
        assertEquals(1, player.getBackupY());
        assertEquals(0, player.getBackupX());

        Coordinates newCoords = player.movePlayer(GridDirection.SOUTH).get(0);
        player.setCoordinates(newCoords);

        Coordinates lastCoords = player.movePlayer(GridDirection.EAST).get(0);
        player.setCoordinates(lastCoords);
        IObjects tile2 = gameboard.getObject(player.getX(), player.getY());

        tile2.doAction(player);
        assertEquals(0, player.getBackupY());
        assertEquals(1, player.getBackupX());
    }


    @Test
    public void wrenchCauseNoDamageAndSpeed() {
        Player player = new Player("foo", 0,0, Color.RED);
        Coordinates playerCoordinates = player.movePlayer(GridDirection.NORTH).get(0);
        player.setCoordinates(playerCoordinates);
        IObjects tile = gameboard.getObject(player.getX(), player.getY());
        assertEquals(0, tile.getDamage());
        assertEquals(0, tile.getSpeed());
        Coordinates newPlayerCoordinates = player.movePlayer(GridDirection.EAST).get(0);
        player.setCoordinates(newPlayerCoordinates);
        Coordinates lastPlayerCoordinates = player.movePlayer(GridDirection.SOUTH).get(0);
        player.setCoordinates(lastPlayerCoordinates);
        tile = gameboard.getObject(player.getX(), player.getY());
        assertEquals(0, tile.getDamage());
        assertEquals(0, tile.getSpeed());
    }


}