package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RotationCogTest {
	private String level = "3C3R" +
			".c." +
			"C.C" +
			".c.";
	private GameBoard gameboard = new GameBoard(level);
	
	@Test
	public void noRotationShouldRetainPlayerDirection() {
		Player player = new Player(0,0);
		IObjects tile = gameboard.getObject(player.getX(), player.getY());
		tile.doAction(player);
		assertEquals(player.getPlayerDirection(), GridDirection.NORTH);
	}
	
	@Test
	public void cogRotatingLeftShouldRotateThePlayerToTheLeft() {
		Player player = new Player(1,0);
		GridDirection playerDirection = player.getPlayerDirection();
		IObjects tile = gameboard.getObject(player.getX(), player.getY());
		tile.doAction(player);
		assertEquals(player.getPlayerDirection(), playerDirection.rotateLeft());
	}

	@Test
	public void cogRotatingRightShouldRotateThePlayerToTheRight() {
		Player player = new Player(0,1);
		GridDirection playerDirection = player.getPlayerDirection();
		IObjects tile = gameboard.getObject(player.getX(), player.getY());
		tile.doAction(player);
		assertEquals(player.getPlayerDirection(), playerDirection.rotateRight());
	}
	
	@Test
	public void getDirection() {
		RotationCog rotationCog = new RotationCog(Rotation.LEFT);
		assertEquals(rotationCog.getDirection(), GridDirection.NORTH); //default setting
	}
	
	@Test
	public void noRotationCogShouldCauseDamage() {
		for (int x=0; x < gameboard.getColums(); x++) {
			for (int y=0; y < gameboard.getRows(); y++) {
				IObjects tile = gameboard.getObject(x,y);
				if (tile instanceof RotationCog) {
					assertEquals(0, tile.getDamage());
				}
			}
		}
	}
	
	@Test
	public void getRotation() {
		Random random = new Random();
		GridDirection direction = GridDirection.listOfDirections()[random.nextInt(GridDirection.listOfDirections().length)];
		Rotation rotation = Rotation.getRandomRotation();
		RotationCog rotationCog = new RotationCog(rotation);
		assertEquals(rotationCog.getRotation(), rotation);
	}
}