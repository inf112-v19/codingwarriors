package inf112.project.RoboRally.objects;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlagTest {
	private String level = "3C2R" +
			"cfr" +
			".f.";
	private String walls = "" +
			"..." +
			"...";
	private GameBoard gameBoard = new GameBoard(level, walls);
	private Player player = new Player("foo", 0,0, Color.RED);

	@Test
	public void flagShouldUpdateBackupPoint() {
		int oldBackupX=player.getBackupX(), oldBackupY=player.getBackupY();
		player.movePlayer(GridDirection.NORTH);
		player.movePlayer(GridDirection.EAST);
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