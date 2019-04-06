package inf112.project.RoboRally.actors;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.*;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.objects.GridDirection;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class CollisionTest {
    private Player player;
    private GameBoard gameBoard;
    private ICard card;
    private Game game;
    private ArrayList<IPlayer> players = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        String boardSetup = "4C4R" +
                ".uu." +
                "r..l" +
                "...l" +
                "..d.";
        String walls = "" +
                "...." +
                ".n.." +
                "...." +
                "....";
        this.gameBoard = new GameBoard(boardSetup, walls);
        this.game = new Game(boardSetup, walls);
        this.player = new Player("a",1, 3, Color.RED);
        players.add(player);
        //this.card = new Card(100, FORWARD_1);
    }

    @Test
    public void playerCollidesWithWallTest() {
        ICard card = new Card(100,Action.FORWARD_1);
        IDeck deck = new Deck();
        deck.addCardToDeck(card);
        game.executeProgramCardsForTheCurrentRegister(deck,players);
        assertEquals(1, player.getX());
        assertEquals(3, player.getY());
    }
}
