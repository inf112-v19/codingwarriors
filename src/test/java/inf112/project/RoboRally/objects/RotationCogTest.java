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
	public void noRotationShouldRetainPlayerDirection() {
		Player player = new Player(0,0);
		assertEquals(player.getPlayerDirection(), GridDirection.NORTH);
	}
	
	@Test
	public void cogRotatingLeftShouldRotateThePlayerToTheLeft() {
		Player player = new Player(1,0);
		GridDirection playerDirection = player.getPlayerDirection();
		assertEquals(player.getPlayerDirection(), GridDirection.NORTH); //Default player direction is NORTH
		Object tile = gameboard.getObject(player.getX(), player.getY());
		assert(tile instanceof RotationCog);
		assert(((RotationCog) tile).getRotation() == Rotation.LEFT);
		((RotationCog) tile).rotatePlayer(player);
		assertEquals(player.getPlayerDirection(), playerDirection.rotateLeft());
	}

	@Test
	public void cogRotatingRightShouldRotateThePlayerToTheRight() {
		Player player = new Player(0,1);
		GridDirection playerDirection = player.getPlayerDirection();
		assertEquals(player.getPlayerDirection(), GridDirection.NORTH); //Default player direction is NORTH
		Object tile = gameboard.getObject(player.getX(), player.getY());
		assert(tile instanceof RotationCog);
		assert(((RotationCog) tile).getRotation() == Rotation.RIGHT);
		((RotationCog) tile).rotatePlayer(player);
		assertEquals(player.getPlayerDirection(), playerDirection.rotateRight());
	}
	/*
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