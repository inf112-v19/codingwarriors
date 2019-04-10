package inf112.project.RoboRally.actors;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.*;
import inf112.project.RoboRally.game.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class CollisionTest {
    private Player player;
    private GameBoard gameBoard;
    private Game game;
    private ICard forward1 = new Card(100,Action.FORWARD_1);
    private ICard rotateRight = new Card(120,Action.ROTATE_RIGHT);
    private ArrayList<IPlayer> players = new ArrayList<>();

    @Before
    public void setUp() {
        String boardSetup = "4C4R" +
                "...." +
                "...." +
                "...." +
                "....";
        String walls = "" +
                "...." +
                ".n.." +
                "...." +
                "....";
        this.gameBoard = new GameBoard(boardSetup, walls);
        this.game = new Game(boardSetup, walls);
    }

    @Test
    public void playerCollidesWithWallTest() {
        IDeck deck = new Deck();
        deck.addCardToDeck(rotateRight);
        deck.addCardToDeck(rotateRight);
        deck.addCardToDeck(forward1);
        this.player = new Player("a",1, 3, Color.RED);
        players.add(player);
        game.executeProgramCardsForTheCurrentRegister(deck,players);

        assertEquals(1, player.getX());
        assertEquals(3, player.getY());
    }

    @Test
    public void playerCanWalkToTileWithWallInOppositeDirectionTest() {
        IDeck deck = new Deck();
        this.player = new Player("a", 1,1,Color.RED);
        players.add(player);
        deck.addCardToDeck(forward1);
        game.executeProgramCardsForTheCurrentRegister(deck,players);

        assertEquals(1, player.getX());
        assertEquals(2, player.getY());
    }
}
