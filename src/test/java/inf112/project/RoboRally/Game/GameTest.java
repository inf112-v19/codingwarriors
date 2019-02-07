package inf112.project.RoboRally.Game;

import inf112.project.RoboRally.Actors.IPlayer;
import inf112.project.RoboRally.Cards.*;
import org.junit.Before;
import org.junit.Test;

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


}