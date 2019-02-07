package inf112.project.RoboRally.Game;

import inf112.project.RoboRally.Actors.IPlayer;
import inf112.project.RoboRally.Actors.Player;
import inf112.project.RoboRally.Cards.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GameTest {

    private IGame game;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
    }

    @Test
    public void checkIfActivePlayersIsEqualToPlayersOnStartingANewGame() {
        game.addPlayers();
        assertEquals(game.getPlayers(), game.getActivePlayers());
    }


    @Test
    public void randomTest() {

        HashMap<IPlayer, ICard> playerAndCard = new HashMap<>();
        IDeck deck = new Deck();

        playerAndCard.put(new Player(), new Card(200, Action.ROTATE_LEFT));


        





    }
}