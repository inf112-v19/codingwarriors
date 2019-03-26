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
        String walls = "" +
                "...." +
                "...." +
                "...." +
                "....";
        this.gameBoard = new GameBoard(boardSetup, walls);
        this.player = new Player("a",1, 1, Color.RED);
        //this.card = new Card(100, FORWARD_1);
    }

    @Test
    public void testSetUpPlayer() {
        assertEquals(1, this.player.getX());
        assertEquals(1, this.player.getY());
        assertEquals(0, player.getPlayerDamage());
        player.takeOneDamage();
        assertEquals(1, player.getPlayerDamage());
    }

    @Test
    public void testMovePlayerForward_1ByCard() {
        this.card = new Card(100, FORWARD_1);
        player.movePlayer(this.card);
        assertEquals(1, this.player.getX());
        assertEquals(2, this.player.getY());
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
    public void testMovePlayerRotateLeftByCard() {
        this.card = new Card(100, ROTATE_LEFT);
        player.movePlayer(this.card);
        assertEquals(1, this.player.getX());
        assertEquals(1, this.player.getY());
        assertEquals(GridDirection.WEST, player.getPlayerDirection());
    }
    @Test
    public void testMovePlayerRotateRightByCard() {
        this.card = new Card(100, ROTATE_RIGHT);
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

    @Test
    public void clearingAPlayersRegisterShouldClearAllUnlockedCards() {
        ICard card1 = new Card(100, FORWARD_2);
        ICard card2 = new Card(200, FORWARD_2);
        ICard card3 = new Card(300, FORWARD_3);
        ICard card4 = new Card(400, U_TURN);
        ICard card5 = new Card(500, ROTATE_RIGHT);
        List<ICard> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);
        player.addListOfCardsToProgramRegister(cardList);

        int numberOfDamageTokens = 7;
        for (int i = 0; i < numberOfDamageTokens; i++) { //Player takes 7 damage,
            player.takeOneDamage(); //and locks 3 registers (5,4 and 3).
        }
        //Assert cards in correct position.
        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));

        player.clearRegister();

        //Assert cards are correct after clear.
        ICard placeholderCard = player.revealProgramCardForRegisterNumber(0);
        assertEquals(placeholderCard, player.revealProgramCardForRegisterNumber(0));
        assertEquals(placeholderCard, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test
    public void addingAListOfCardsToAPlayersRegisterShouldAddTheCorrectCardsInCorrectSlots() {
        ICard card1 = new Card(100, FORWARD_2);
        ICard card2 = new Card(200, FORWARD_2);
        ICard card3 = new Card(300, FORWARD_3);
        ICard card4 = new Card(400, U_TURN);
        ICard card5 = new Card(500, ROTATE_RIGHT);
        List<ICard> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);
        player.addListOfCardsToProgramRegister(cardList);

        player.addListOfCardsToProgramRegister(cardList);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingAListOfNullToAPlayersRegisterShouldFail() {
        player.addListOfCardsToProgramRegister(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingAListOfTooManyCardsToAPlayersRegistryShouldFail() {
        ICard card1 = new Card(100, FORWARD_2);
        ICard card2 = new Card(200, FORWARD_2);
        ICard card3 = new Card(300, FORWARD_3);
        ICard card4 = new Card(400, U_TURN);
        ICard card5 = new Card(500, ROTATE_RIGHT);
        ICard card6 = new Card(600, ROTATE_LEFT);
        List<ICard> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);
        cardList.add(card6);
        player.addListOfCardsToProgramRegister(cardList);
    }

    @Test
    public void destroyingAPlayerShouldRemoveOnePointFromThePlayersLives() {
        int numberOfLives = player.getNumberOfLivesRemaining();
        player.destroyPlayer();
        assertEquals(numberOfLives - 1, player.getNumberOfLivesRemaining());
    }






    @Test
    public void takingFourOrLessDamageShouldLeaveAllRegisterSlotsUnlocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int FOUR_DAMAGE = 4;
        for (int i = 0; i < FOUR_DAMAGE; i++) {
            player.takeOneDamage();
            player.clearRegister();
            ICard placeHolder = player.revealProgramCardForRegisterNumber(0);
            assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(0));
            assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(1));
            assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(2));
            assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(3));
            assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(4));
        }
    }

    @Test
    public void takingFiveDamageShouldLeaveAllButOneRegisterSlotUnlocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int FIVE_DAMAGE = 5;
        for (int i = 0; i < FIVE_DAMAGE; i++) {
            player.takeOneDamage();
        }
        player.clearRegister();

        ICard placeHolder = player.revealProgramCardForRegisterNumber(0);
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(0));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(1));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(2));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test
    public void takingSixDamageShouldLeaveAllButTwoRegisterSlotsUnlocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int SIX_DAMAGE = 6;
        for (int i = 0; i < SIX_DAMAGE; i++) {
            player.takeOneDamage();
        }
        player.clearRegister();

        ICard placeHolder = player.revealProgramCardForRegisterNumber(0);
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(0));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(1));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test
    public void takingSevenDamageShouldLeaveAllButThreeRegisterSlotsUnlocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int SEVEN_DAMAGE = 7;
        for (int i = 0; i < SEVEN_DAMAGE; i++) {
            player.takeOneDamage();
        }
        player.clearRegister();

        ICard placeHolder = player.revealProgramCardForRegisterNumber(0);
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(0));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test
    public void takingEightDamageShouldLeaveOneRegisterSlotUnlocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int EIGHT_DAMAGE = 8;
        for (int i = 0; i < EIGHT_DAMAGE; i++) {
            player.takeOneDamage();
        }
        player.clearRegister();

        ICard placeHolder = player.revealProgramCardForRegisterNumber(0);
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test
    public void takingNineDamageShouldLeaveAllRegisterSlotslocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int NINE_DAMAGE = 9;
        for (int i = 0; i < NINE_DAMAGE; i++) {
            player.takeOneDamage();
        }
        player.clearRegister();

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
    }

    @Test
    public void takingTenDamageShouldLeaveAllRegisterSlotsUnlocked() {
        ICard card1 = new Card(100, ROTATE_LEFT);
        ICard card2 = new Card(200, ROTATE_RIGHT);
        ICard card3 = new Card(300, FORWARD_2);
        ICard card4 = new Card(400, FORWARD_1);
        ICard card5 = new Card(500, U_TURN);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        player.addListOfCardsToProgramRegister(cards);

        assertEquals(card1, player.revealProgramCardForRegisterNumber(0));
        assertEquals(card2, player.revealProgramCardForRegisterNumber(1));
        assertEquals(card3, player.revealProgramCardForRegisterNumber(2));
        assertEquals(card4, player.revealProgramCardForRegisterNumber(3));
        assertEquals(card5, player.revealProgramCardForRegisterNumber(4));
        assertEquals(0, player.getPlayerDamage());

        final int TEN_DAMAGE = 10;
        for (int i = 0; i < TEN_DAMAGE; i++) {
            player.takeOneDamage();
        }
        player.clearRegister();

        ICard placeHolder = player.revealProgramCardForRegisterNumber(0);
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(0));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(1));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(2));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(3));
        assertEquals(placeHolder, player.revealProgramCardForRegisterNumber(4));
    }

}
