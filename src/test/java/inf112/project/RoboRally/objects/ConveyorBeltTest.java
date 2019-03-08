package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConveyorBeltTest {
	
	
	private int x, y;
	private Player player;
	private GameBoard gameBoard;
	
	
	@Before
	public void setUp() {
		String boardSetup = "4C4R" +
				".dd." +
				"r..l" +
				"r..l" +
				".uu.";
		this.gameBoard = new GameBoard(boardSetup);
	}
	
	
	
	@Test
	public void noMovementShouldRetainPlayerPosition() {
		x=0;
		y=0;
		player = new Player("foo", x, y);
		IObjects tile = gameBoard.getObject(player.getX(), player.getY());
		assertEquals(tile.getSpeed(), 0);
		tile.doAction(player);
		assertEquals(player.getX(), x);
		assertEquals(player.getY(), y);
	}
	
	@Test
	public void oneStepNorthShouldMoveThePlayerOneStepNorth() {
		x=1;
		y=0;
		player = new Player("foo", x, y);
		IObjects tile = gameBoard.getObject(player.getX(), player.getY());
		
		// a small check that may reveal the gameboard to be the reason for failure, not the method
		assert(tile instanceof  ConveyorBelt);
		assertEquals(tile.getDirection(), GridDirection.NORTH);
		
		tile.doAction(player);
		assertEquals(player.getX(), x);
		assertEquals(player.getY(), y+1);
	}
	
	@Test
	public void oneStepSouthShouldMoveThePlayerOneStepSouth() {
		x=1;
		y=3;
		player = new Player("foo", x, y);
		IObjects tile = gameBoard.getObject(player.getX(), player.getY());
		
		// a small check that may reveal the gameboard to be the reason for failure, not the method
		assert(tile instanceof  ConveyorBelt);
		assertEquals(tile.getDirection(), GridDirection.SOUTH);
		
		tile.doAction(player);
		assertEquals(player.getX(), x);
		assertEquals(player.getY(), y-1);
	}
	
	@Test
	public void oneStepEastShouldMoveThePlayerOneStepEast() {
		x=0;
		y=1;
		player = new Player("foo", x, y);
		IObjects tile = gameBoard.getObject(player.getX(), player.getY());
		
		// a small check that may reveal the gameboard to be the reason for failure, not the method
		assert(tile instanceof  ConveyorBelt);
		assertEquals(tile.getDirection(), GridDirection.EAST);
		
		tile.doAction(player);
		assertEquals(player.getX(), x+1);
		assertEquals(player.getY(), y);
	}
	
	@Test
	public void oneStepWestShouldMoveThePlayerOneStepWest() {
		x=3;
		y=1;
		player = new Player("foo", x, y);
		IObjects tile = gameBoard.getObject(player.getX(), player.getY());
		
		// a small check that may reveal the gameboard to be the reason for failure, not the method
		assert(tile instanceof  ConveyorBelt);
		assertEquals(tile.getDirection(), GridDirection.WEST);
		
		tile.doAction(player);
		assertEquals(player.getX(), x-1);
		assertEquals(player.getY(), y);
	}
}