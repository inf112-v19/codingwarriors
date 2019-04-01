package inf112.project.RoboRally.actors;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.Card;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;
import inf112.project.RoboRally.objects.GridDirection;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static inf112.project.RoboRally.cards.Action.*;
import static org.junit.Assert.*;

public class AITest {
    private AI player;
    private GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        String boardSetup = "4C4R" +
                ".uu." +
                "r..l" +
                "...l" +
                "..d.";
        String walls = "" +
                "...." +
                "...." +
                "...." +
                "....";
        this.gameBoard = new GameBoard(boardSetup, walls);
        this.player = new AI("a", 1, 1, Color.RED);
    }

    @Test
    public void testSetUpAI() {
        assertEquals(1, this.player.getX());
        assertEquals(1, this.player.getY());
        assertEquals(0, player.getPlayerDamage());
        player.takeOneDamage();
        assertEquals(1, player.getPlayerDamage());
    }
}
