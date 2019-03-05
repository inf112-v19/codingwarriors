package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlagTest {
	private String level = "3C2R" +
			"cfr" +
			".f.";
	
	private GameBoard gameBoard = new GameBoard(level);
	private Player player = new Player("foo", 0,0);

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
		player.movePlayer(GridDirection.EAST);

		gameBoard.getObject(player.getX(), player.getY()).doAction(player); // player should not be able to pick up this flag
		assertEquals(0, player.getFlagsVisited());

		player.movePlayer(GridDirection.NORTH); // player should be able to pick up this flag
		gameBoard.getObject(player.getX(), player.getY()).doAction(player);
		assertEquals(1, player.getFlagsVisited());
		
		player.movePlayer(GridDirection.SOUTH); // now, this flag is next in line, and player should be able to pick it up
		gameBoard.getObject(player.getX(), player.getY()).doAction(player);
		assertEquals(2, player.getFlagsVisited());
	}
}