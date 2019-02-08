package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

class ConveyorBeltTest {
	int x=6;
	int y=1;
	Player player = new Player(x,y);
	String boardSetup = "10C7R" +
			".........." +
			"....uu...." +
			"..l..L..U." +
			".........." +
			"...dD....." +
			".....r...." +
			"......R...";
	GameBoard gameBoard = new GameBoard(boardSetup);
	
	@Test
	public void noMovementShouldRetainPlayerPosition() {
		assertEquals(player.getX(), x);
	}
	
	/*@Test
	public void oneStepNorthShouldMoveThePlayerOneStepNorth() {
		x=player.getX();
		player.movePlayer(GridDirection.NORTH);
		IObjects conveyorTile = gameBoard.getObject(player.getX(), player.getY());
		System.out.println(conveyorTile.toString());
		if (conveyorTile instanceof ConveyorBelt) {
			((ConveyorBelt) conveyorTile).movePlayer(player);
		}
		assertEquals(player.getX(), x+2);
	} */
}