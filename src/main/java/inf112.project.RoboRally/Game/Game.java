package inf112.project.RoboRally.Game;

import inf112.project.RoboRally.Actors.IPlayer;
import inf112.project.RoboRally.Actors.Player;
import inf112.project.RoboRally.Board.GameBoard;
import inf112.project.RoboRally.Cards.Deck;
import inf112.project.RoboRally.Cards.IDeck;

import java.util.ArrayList;


public class Game implements IGame {


    private IDeck programCards;
    private ArrayList<IPlayer> players;
    private ArrayList<IPlayer> activePlayers;
    private GameBoard board;
    private int numberOfPlayersLeftInTheGame;


    public void startGame() {
        initializeGame();
        playRoboRally();
        // EndGame()?
    }

    @Override
    public void playRoboRally() {
        boolean done = false; // Tracks if the game is finished.
        while (!done) {
            for (IPlayer player : players) {
                if (player.isPoweredDown()) {
                    player.removeAllDamageTokensFromPlayer();
                }
            }


            programCards.shuffle();
            for (IPlayer player : players) { // Each player draw cards.
                int numberOfCardsPlayerCanDraw = calculateTheNumberOfCardsThePlayerCanDraw(player);
                player.receiveCards(programCards.handOutNCards(numberOfCardsPlayerCanDraw));
            }

            for (IPlayer player : players) { // Each player programs their robot,
                // TODO: Check if this can be made to run in parallel using streams.
                // TODO: Implement timer for slow players?
                player.addCardsToProgramRegister();
                player.removeRemainingCardsInHand(); // and discard their hand.
            }








            if (checkIfTheGameHasBeenWon()) {
                done = true;
            }

        }

    }

    @Override
    public int calculateTheNumberOfCardsThePlayerCanDraw(IPlayer player) {
        int numberOfCards = 9; // The default and maximum number of cards
        // that can be dealt to a player.
        int playerDamage = player.getPlayerDamage(); // The number of damage tokens
        // the player has received.
        return (numberOfCards - playerDamage);
    }

    @Override
    public boolean checkIfTheGameHasBeenWon() {




    }


    @Override
    public void initializeGame() {
        addPlayers();
        String gameBoardLayout = "" +
                "" +
                "" +
                "" +
                "";
        this.board = new GameBoard(gameBoardLayout);
        this.programCards = new Deck();
        this.programCards.createProgramCardsDeck();
    }

    @Override
    public void addPlayers() {
       // TODO: select game piece, if pieces have different design?
        this.players = new ArrayList<IPlayer>();
//        int numberOfPlayers = playerCommunication.askForNumberOfPlayers();
    /*    String userName = "";
        int numberOfPlayers = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            userName = playerCommunication.askForUserName();
            IPlayer player = new Player(userName);
            players.add(player);
        }
        this.numberOfPlayersLeftInTheGame = numberOfPlayers;
      */

    this.players.add(new Player());
    this.activePlayers = new ArrayList<IPlayer>();
        this.activePlayers.addAll(players);
    }


    @Override
    public ArrayList<IPlayer> getActivePlayers() {
        return activePlayers;
    }


    @Override
    public ArrayList<IPlayer> getPlayers() {
        return players;
    }
}
