package inf112.project.RoboRally.objects;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlagTest {
	private String level = "3C2R" +
			"cfr" +
			".f.";
	private String otherLevel = "3C2R" +
			".f." +
			"...";
	private String walls = "" +
			"..." +
			"...";



	@Test
	public void flagShouldUpdateBackupPoint() {
		GameBoard gameBoard = new GameBoard(otherLevel, walls);
		Player player = new Player("foo", 0,0, Color.RED);

		int oldBackupX=player.getBackupX(), oldBackupY=player.getBackupY();
		player.movePlayer(GridDirection.NORTH);
		player.setCoordinates(player.getPathOfPlayer().get(0));
		player.movePlayer(GridDirection.EAST);
		player.setCoordinates(player.getPathOfPlayer().get(0));
		IObjects tile = gameBoard.getObject(player.getX(), player.getY());
		gameBoard.getObject(player.getX(), player.getY());
		
		// a small check to see that the Player is actually on a Flag
		assert(tile instanceof Flag);

		tile.doAction(player);
		assertEquals(player.getBackupX(), player.getX());
		assertEquals(player.getBackupY(), player.getY());
		assertNotEquals(player.getBackupX(), oldBackupX);
		assertNotEquals(player.getBackupY(), oldBackupY);
	}
	
	@Test
	public void playerCanOnlyPickUpCorrectFlag() {
		GameBoard gameBoard = new GameBoard(level, walls);
		Player player = new Player("foo", 0,0, Color.RED);
		
		Flag aFlag = (Flag) gameBoard.getObject(1,0);
		Flag otherFlag = (Flag) gameBoard.getObject(1,1);

		if (aFlag.getFlagNumber() == 0) {
			player.movePlayer(GridDirection.EAST);

			otherFlag.doAction(player); // player should not be able to pick up this flag
			assertEquals(0, player.getFlagsVisited());

			player.movePlayer(GridDirection.NORTH); // player should be able to pick up this flag
			aFlag.doAction(player);
			assertEquals(1, player.getFlagsVisited());

			player.movePlayer(GridDirection.SOUTH); // now, this flag is next in line, and player should be able to pick it up
			otherFlag.doAction(player);
			assertEquals(2, player.getFlagsVisited());
		} else {
			player.movePlayer(GridDirection.EAST);
			player.movePlayer(GridDirection.NORTH);

			aFlag.doAction(player); // player should not be able to pick up this flag
			assertEquals(0, player.getFlagsVisited());

			player.movePlayer(GridDirection.SOUTH); // player should be able to pick up this flag
			otherFlag.doAction(player);
			assertEquals(1, player.getFlagsVisited());

			player.movePlayer(GridDirection.NORTH); // now, this flag is next in line, and player should be able to pick it up
			aFlag.doAction(player);
			assertEquals(2, player.getFlagsVisited());
		}
	}
}