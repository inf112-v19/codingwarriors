package inf112.project.RoboRally.game;

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