package inf112.project.RoboRally.Game;

import inf112.project.RoboRally.Actors.IPlayer;
import inf112.project.RoboRally.Actors.Player;
import inf112.project.RoboRally.Board.GameBoard;
import inf112.project.RoboRally.Cards.Deck;
import inf112.project.RoboRally.Cards.ICard;
import inf112.project.RoboRally.Cards.IDeck;

import java.util.ArrayList;
import java.util.HashMap;


public class Game implements IGame {


    private IDeck programCards;
    private ArrayList<IPlayer> players; // All the participating players, ordered after player creation.
    // Certain events that prioritises starting order, may use this list for reference.
    // For example: players[0] has higher priority than players[1].
    private ArrayList<IPlayer> activePlayers; // Players that are not incapacitated.
    private GameBoard board;
    private int numberOfPlayersLeftInTheGame;
    private GameStatus currentGameStatus;


    @Override
    public void startGame() {
        initializeGame();
        playRoboRally();
        // EndGame()?
    }

    @Override
    public void playRoboRally() {
        boolean done = false; // Tracks if the game is finished.
        while (!done) {
        //  processPoweredDownPlayers();
            programCards.shuffle();
            dealOutProgramCards();
            playersProgramRobots();
        //  askPlayersIfTheyWantToPowerDown();


            int registerNumber = 1;
            final int MAX_NUMBER_OF_REGISTER_SLOTS = 5;
            HashMap<IPlayer, ICard> playerAndCard = new HashMap<>();
            for (; registerNumber <= MAX_NUMBER_OF_REGISTER_SLOTS; registerNumber++) {
                for (IPlayer player : activePlayers) {
                    player.revealProgramCardForRegisterNumber(registerNumber);
                }
                System.out.println(registerNumber);
               // playerAndCard.values();






//            moveBoardElements();
//            fireLasers();
//            touchFlagsAndRepairSites();
            }




//            cleanUp();



            if (checkIfTheGameHasBeenWon()) {
                done = true;
            }

        }

    }

    @Override
    public void askPlayersIfTheyWantToPowerDown() {
        // TODO: powered down players that have been damaged
        //  should be asked if they want to stay powered down for another round.
        for (IPlayer player : players) { // Ask each player
            // prioritised in order of starting dock.
            if (activePlayers.contains(player) && player.isDamaged()) {
               boolean playerWantsToPowerDown =
                       playerCommunication.askIfPlayerWantsToPowerDownNextRound();
               if (playerWantsToPowerDown) {
                   player.powerDownNextRound();
               }
            }
        }
    }

    @Override
    public void playersProgramRobots() {
        for (IPlayer player : activePlayers) {
            // TODO: Check if this can be made to run in parallel using streams.
            // TODO: Implement timer for slow players?
            player.addCardsToProgramRegister();
            player.removeRemainingCardsInHand();
        }
    }

    @Override
    public void dealOutProgramCards() {
        for (IPlayer player : activePlayers) {
            int numberOfCardsPlayerCanDraw =
                    calculateTheNumberOfCardsThePlayerCanDraw(player);
            player.receiveCards(programCards.handOutNCards(numberOfCardsPlayerCanDraw));
        }
    }

    @Override
    public void processPoweredDownPlayers() {
        for (IPlayer player : players) {
            if (player.isPoweredDown()) {
                player.removeAllDamageTokensFromPlayer();
                activePlayers.remove(player);
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
        //TODO: Make initializeGame take a gameboard as parameter,
        // which can be chosen at the start menu?
        addPlayers();
        String gameBoardLayout = "12C16R" +
                ".rr..r.rrr......" +
                ".rrrrrrr........" +
                ".r.............." +
                ".r.............." +
                ".r.............." +
                "rr.............." +
                "rr.............." +
                ".r.............." +
                ".r.............." +
                ".r.............." +
                ".r.............." +
                ".r..............";
        this.board = new GameBoard(gameBoardLayout);
        this.programCards = new Deck();
        this.programCards.createProgramCardsDeck();
    }

    @Override
    public void addPlayers() {
       // TODO: select game piece, if pieces have different design?
     /*   this.players = new ArrayList<>();
//        int numberOfPlayers = playerCommunication.askForNumberOfPlayers();
        String userName = "";
        int numberOfPlayers = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            userName = playerCommunication.askForUserName();
            IPlayer player = new Player(userName);
            players.add(player);
        }
        this.numberOfPlayersLeftInTheGame = numberOfPlayers;
      */
        this.players = new ArrayList<>();
        this.players.add(new Player());
        this.activePlayers = new ArrayList<>();
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
