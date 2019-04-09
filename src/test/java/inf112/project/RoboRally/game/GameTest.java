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



}