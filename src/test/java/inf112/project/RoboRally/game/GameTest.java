package inf112.project.RoboRally.game;

import com.badlogic.gdx.graphics.Color;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.cards.Action;
import inf112.project.RoboRally.cards.Card;
import inf112.project.RoboRally.cards.ICard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private IGame game;
    private List<IPlayer> players;
    private List<IPlayer> activePlayers;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
        this.players = game.getPlayers();
        this.activePlayers = game.getActivePlayers();
    }

    @Test
    public void checkIfActivePlayersIsEqualToPlayersOnStartingANewGame() {
        assertEquals(players, activePlayers);
    }

    @Test
    public void aPlayerThatHasTakenZeroDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenZeroDamage = new Player("ZeroDamage", 0, 0, Color.RED);
        int numberOfCards = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenZeroDamage);
        int maxNumberOfCards = 9;
        assertEquals(maxNumberOfCards, numberOfCards);
    }

    @Test
    public void aPlayerThatHasTakenFiveDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenFiveDamage = new Player("FiveDamage", 0,0, Color.RED);
        int correctAmountOfCards = 4;
        for (int i = 0; i < 5; i++) {
            hasTakenFiveDamage.takeOneDamage();
        }
        int numberOfCardsDrawn = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenFiveDamage);
        assertEquals(correctAmountOfCards, numberOfCardsDrawn);
    }

    @Test
    public void aPlayerThatHasTakenNineDamageDrawsCorrectAmountOfCards() {
        IPlayer hasTakenNineDamage = new Player("NineDamage", 0,0, Color.RED);
        int correctAmountOfCards = 0;
        for (int i = 0; i < 9; i++) {
            hasTakenNineDamage.takeOneDamage();
        }
        int numberOfCardsDrawn = game.calculateTheNumberOfCardsThePlayerCanDraw(hasTakenNineDamage);
        assertEquals(correctAmountOfCards, numberOfCardsDrawn);
    }


}