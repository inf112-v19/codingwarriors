package inf112.project.RoboRally.objects;

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
	public void setUp() throws Exception {
		String boardSetup = "4C4R" +
				".uu." +
				"r..l" +
				"r..l" +
				".dd.";
		this.gameBoard = new GameBoard(boardSetup);
	}
	
	
	
	@Test
	public void noMovementShouldRetainPlayerPosition() {
		x=0;
		y=0;
		player = new Player(x,y);
		assertEquals(player.getX(), x);
		assertEquals(player.getY(), y);
	}
	
	@Test
	public void oneStepNorthShouldMoveThePlayerOneStepNorth() {
		x=1;
		y=0;
		player = new Player(x,y);
		IObjects conveyorTile = gameBoard.getObject(player.getX(), player.getY());
		assert(conveyorTile instanceof  ConveyorBelt);
		player.movePlayer(conveyorTile.getDirection());
		assertEquals(player.getX(), x);
		assertEquals(player.getY(), y+1);
	}
	
	@Test
	public void oneStepSouthShouldMoveThePlayerOneStepSouth() {
		x=1;
		y=3;
		player = new Player(x,y);
		IObjects conveyorTile = gameBoard.getObject(player.getX(), player.getY());
		assert(conveyorTile instanceof  ConveyorBelt);
		player.movePlayer(conveyorTile.getDirection());
		assertEquals(player.getX(), x);
		assertEquals(player.getY(), y-1);
	}
	
	@Test
	public void oneStepEastShouldMoveThePlayerOneStepEast() {
		x=0;
		y=1;
		player = new Player(x,y);
		IObjects conveyorTile = gameBoard.getObject(player.getX(), player.getY());
		assert(conveyorTile instanceof  ConveyorBelt);
		player.movePlayer(conveyorTile.getDirection());
		assertEquals(player.getX(), x+1);
		assertEquals(player.getY(), y);
	}
	
	@Test
	public void oneStepWestShouldMoveThePlayerOneStepWest() {
		x=3;
		y=1;
		player = new Player(x,y);
		IObjects conveyorTile = gameBoard.getObject(player.getX(), player.getY());
		assert(conveyorTile instanceof  ConveyorBelt);
		player.movePlayer(conveyorTile.getDirection());
		assertEquals(player.getX(), x-1);
		assertEquals(player.getY(), y);
	}
}