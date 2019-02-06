package inf112.project.RoboRally.Game;

import inf112.project.RoboRally.Actors.IPlayer;
import inf112.project.RoboRally.Actors.Player;
import inf112.project.RoboRally.Board.GameBoard;
import inf112.project.RoboRally.Cards.Deck;
import inf112.project.RoboRally.Cards.IDeck;

import java.util.ArrayList;
import java.util.Scanner;


public class Game implements IGame {


    private IDeck programCards;
    private ArrayList<IPlayer> players;
    private GameBoard board;


    public void startGame() {
        initializeGame();
        playRoboRally();
        // EndGame()?
    }

    @Override
    public void playRoboRally() {
        boolean done = false; // Is the game finished.
        while (!done) {



            if (checkIfTheGameHasBeenWon()) {
                done = true;
            }

        }

    }

    @Override
    public boolean checkIfTheGameHasBeenWon() {




    }


    @Override
    public void initializeGame() {
        /*
        add players
        create board
        create deck
         */

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

    private void addPlayers() {
        /*
        Ask for number of players
        input player name, (select game piece, if pieces have different design?)
        add player to list of players, continue until all players have been added.
         */
//        int numberOfPlayers = playerCommunication.askForNumberOfPlayers();
        String userName = "";
        int numberOfPlayers = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            // Ask for players name,
            userName = playerCommunication.askForUserName();
            IPlayer player = new Player(userName);
            players.add(player);
        }
    }

}
