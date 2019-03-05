package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlagTest {
	String level = "3C2R" +
			"cfr" +
			".l.";
	
	GameBoard gameBoard = new GameBoard(level);
	
	@Test
	public void flagShouldUpdateBackupPoint() {
		Player player = new Player("foo", 0,0);
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
		//assertNotEquals(player.getBackupX(), oldBackupX);
		//assertNotEquals(player.getBackupY(), oldBackupY);
	}
	
	/*@Test
	public void flagShouldOnlyBeVisitedIfItIsNextInLine() {
		Player player = new Player(0,0);
		IObjects tile1 = gameBoard.getObject(1,1);
		IObjects tile2 = gameBoard.getObject(1,0);
		
		assert (tile1 instanceof Flag);
		assert (tile2 instanceof Flag);
	}*/
}