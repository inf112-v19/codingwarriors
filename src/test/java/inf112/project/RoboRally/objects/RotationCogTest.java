package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotationCogTest {
	String level = "3C3R" +
			".c." +
			"C.C" +
			".c.";
	GameBoard gameboard = new GameBoard(level);
	
	@Test
	public void cogRotatingLeftShouldRotateThePlayerToTheLeft() {
		Player player = new Player(1,0);
		GridDirection playerDirection = player.getPlayerDirection();
		assert(playerDirection == GridDirection.NORTH);
		Object tile = gameboard.getObject(player.getX(), player.getY());
		assert(tile instanceof RotationCog);
		assert(((RotationCog) tile).getRotation() == Rotation.LEFT);
		assertEquals(player.getPlayerDirection(), GridDirection.NORTH); //Default player direction is NORTH
		((RotationCog) tile).rotatePlayer(player);
		assertEquals(player.getPlayerDirection(), playerDirection.rotateLeft());
	}
	/*
	@Test
	public void rotateLeft() {
		fail("Not implemented yet");
	}
	
	@Test
	public void getDirection() {
		fail("Not implemented yet");
	}
	
	@Test
	public void getDamage() {
		fail("Not implemented yet");
	}
	
	@Test
	public void getRotation() {
		fail("Not implemented yet");
	}*/
}