package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.cards.Action;
import inf112.project.RoboRally.cards.Card;
import inf112.project.RoboRally.cards.ICard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    private IGame game;
    private ArrayList<IPlayer> players;
    private ArrayList<IPlayer> activePlayers;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
        this.players = game.getPlayers();
        this.activePlayers = game.getActivePlayers();
    }

    @Test
    public void checkIfActivePlayersIsEqualToPlayersOnStartingANewGame() {
        game.addPlayers();
        assertEquals(players, activePlayers);
    }

    @Test
    public void aPlayerThatHasTakenZeroDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenZeroDamage = new Player("ZeroDamage", 0, 0);
        int numberOfCards = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenZeroDamage);
        int maxNumberOfCards = 9;
        assertEquals(maxNumberOfCards, numberOfCards);
    }

    @Test
    public void aPlayerThatHasTakenFiveDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenFiveDamage = new Player("FiveDamage", 0,0);
        int correctAmountOfCards = 4;
        for (int i = 0; i < 5; i++) {
            hasTakenFiveDamage.takeOneDamage();
        }
        int numberOfCardsDrawn = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenFiveDamage);
        assertEquals(correctAmountOfCards, numberOfCardsDrawn);
    }

    @Test
    public void aPlayerThatHasTakenNineDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenNineDamage = new Player("NineDamage", 0,0);
        int correctAmountOfCards = 0;
        for (int i = 0; i < 9; i++) {
            hasTakenNineDamage.takeOneDamage();
        }
        int numberOfCardsDrawn = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenNineDamage);
        assertEquals(correctAmountOfCards, numberOfCardsDrawn);
    }

    @Test
    public void aPlayerThatHasTakenTenDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenTenDamage = new Player("TenDamage", 0,0);
        int correctAmountOfCards = 0;
        for (int i = 0; i < 10; i++) {
            hasTakenTenDamage.takeOneDamage();
        }
        int numberOfCardsDrawn = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenTenDamage);
        assertEquals(correctAmountOfCards, numberOfCardsDrawn);
    }

/*
    @Test
    public void randTest() {
        game.initializeGame();
        IPlayer player = game.getPlayers().get(1);
        ICard card = new Card(200, Action.ROTATE_LEFT);
        System.out.println(player.getPlayerDirection());
        System.out.println(player.getX());
        System.out.println(player.getY());

        player.movePlayer(card);
        System.out.println();

        System.out.println(player.getPlayerDirection());
        System.out.println(player.getX());
        System.out.println(player.getY());
    }
'/



/*
    @Test
    public void checkIfEveryPlayerWasDealtCards() {
        game.startGame();
        int properStartHandSize = 9;
        System.out.println("ran");
        for (IPlayer player: players) {
            System.out.println("player hand = " + player.getCardsInHand().getSize());
            assertEquals(properStartHandSize, player.getCardsInHand().getSize());
        }
    }*/
}