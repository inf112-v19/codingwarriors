package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.Card;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.objects.GridDirection;
import org.junit.Before;
import org.junit.Test;

import static inf112.project.RoboRally.cards.Action.*;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    private GameBoard gameBoard;
    private ICard card;

        @Before
        public void setUp() throws Exception {
            String boardSetup = "4C4R" +
                    ".uu." +
                    "r..l" +
                    "...l" +
                    "..d.";
            this.gameBoard = new GameBoard(boardSetup);
            this.player = new Player(1, 1);
            //this.card = new Card(100, FORWARD_1);
        }

    @Test
    public void testMovePlayerForward_1ByCard() {
        this.card = new Card(100, FORWARD_1);
            player.movePlayer(this.card);
        assertEquals(1, this.player.getX());
        assertEquals(0, this.player.getY());
    }
    @Test
    public void testMovePlayerUturnByCard() {
        this.card = new Card(100, U_TURN);
        player.movePlayer(this.card);
        assertEquals(1, this.player.getX());
        assertEquals(1, this.player.getY());
        assertEquals(GridDirection.SOUTH, player.getPlayerDirection());
    }



    @Test
    public void testMovePlayerUturn() {

        assertEquals(1, this.player.getX());
        assertEquals(1, this.player.getY());
        //assertEquals(GridDirection.SOUTH, player.());
    }
    @Test
    public void testMovePlayerRotateLeftByCard() {
        this.card = new Card(100, ROTATE_LEFT);
        player.movePlayer(this.card);
        assertEquals(1, this.player.getX());
        assertEquals(1, this.player.getY());
        assertEquals(GridDirection.EAST, player.getPlayerDirection());
    }


        @Test
        public void testMovePlayerDirectionNorth() {
            this.player.movePlayer(GridDirection.NORTH);
            assertEquals(1, this.player.getX());
            assertEquals(2, this.player.getY());
        }

    @Test
    public void testMovePlayerDirectionWest() {
        this.player.movePlayer(GridDirection.WEST);
        assertEquals(0, this.player.getX());
        assertEquals(1, this.player.getY());
    }

    @Test
    public void testMovePlayerDirectionSouth() {
        this.player.movePlayer(GridDirection.SOUTH);
        assertEquals(1, this.player.getX());
        assertEquals(0, this.player.getY());
    }
    @Test
    public void testMovePlayerDirectionEast() {
        this.player.movePlayer(GridDirection.EAST);
        assertEquals(2, this.player.getX());
        assertEquals(1, this.player.getY());
    }
    }
