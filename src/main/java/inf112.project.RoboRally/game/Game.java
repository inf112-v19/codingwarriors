package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static inf112.project.RoboRally.game.GameStatus.*;


public class Game implements IGame {


    private IDeck programCards;
    private IDeck discardedProgramCards;
    private List<IPlayer> players; // All the participating players, ordered after player creation.
    // Certain events that prioritises starting order, may use this list for reference.
    // For example: players[0] has higher priority than players[1].
    private List<IPlayer> activePlayers; // Players that are not incapacitated.
    private GameBoard board;
    private int numberOfPlayersLeftInTheGame;
    private boolean everyFlagHasBeenVisited;
    private GameStatus currentGameStatus;
    private IPlayer currentlyActingPlayer; // The player whose cards are to be displayed.
    private IDeck[] selectedCards;


    @Override
    public void startGame() {
        initializeGame();
        playRoboRally();
        // EndGame()? Show score board, victory fanfare, etc.
    }

    @Override
    public void playRoboRally() {
        boolean done = false; // Tracks if the game is finished.
        while (!done) {
        //  processPoweredDownPlayers();

            dealOutProgramCards();
            playersProgramRobots();
        //  askPlayersIfTheyWantToPowerDown();
        //    revealProgramCardsAndExecuteTheirCommands();
        //    cleanUp();

            programCards.removeAllCardsFromDeck(); // Moved here from cleanUp
            programCards.createProgramCardsDeck(); // for demonstration purposes.
            if (checkIfTheGameIsOver()) {
                done = true;
            }
        }
        currentGameStatus = THE_END;
    }

    @Override
    public void revealProgramCardsAndExecuteTheirCommands() {
        currentGameStatus = EXECUTING_INSTRUCTIONS;
        IDeck revealedCardsForThisRegister = new Deck();
        int registerNumber = 1;
        final int MAX_NUMBER_OF_REGISTER_SLOTS = 5;
        ICard revealedCard;
        HashMap<ICard, IPlayer> cardAndPlayer = new HashMap<>();
        for (; registerNumber <= MAX_NUMBER_OF_REGISTER_SLOTS; registerNumber++) {
            cardAndPlayer.clear();
            for (IPlayer player : activePlayers) {
                revealedCard = player.revealProgramCardForRegisterNumber(registerNumber);
                cardAndPlayer.put(revealedCard, player);
                revealedCardsForThisRegister.addCardToDeckAtPosition(revealedCardsForThisRegister.getSize(),revealedCard); // Correct position?
            }
            revealedCardsForThisRegister.sortDeckAfterCardPriority();
            for (ICard card : revealedCardsForThisRegister) {
                IPlayer player = cardAndPlayer.get(card);
                player.movePlayer(card);
            }
        //    board.moveGameBoardElements();
            registerLaserDamage();
            registerFlagsAndRepairSites();
        }
    }

    @Override
    public boolean checkIfTheGameIsOver() {
        return (numberOfPlayersLeftInTheGame <= 0 || everyFlagHasBeenVisited);
    }

    @Override
    public void cleanUp() {
        currentGameStatus = FINISHING_UP_THE_TURN;
        for (IPlayer player : players) {
         /*   if (player.isStandingOnASingleWrenchSpace()) {
                player.removeOneDamageToken();
            }
            if (player.isStandingOnACrossedWrenchAndHammerSpace()) {
                player.removeOneDamageToken();
                player.drawOneOptionCard();
            }*/
            player.clearRegister();
            /*if (player.isPoweredDown()) {
                boolean answer =
                        playerCommunication.askIfPlayerWantsToRemainPoweredDown();
                if (answer) {
                    player.powerDownNextRound();
                } else {
                    player.powerUp();
                }
            }*/
            if (player.wasDestroyedThisTurn()) {
                player.respawnAtLastArchiveMarker();
             /*   if (player.isDamaged()) {
                    boolean answer =
                            playerCommunication.askIfPlayerWantsToPowerDownNextRound();
                    if (answer) {
                        player.powerDownNextRound();
                    }
                }*/
            }
        }
        programCards.removeAllCardsFromDeck();
        programCards.createProgramCardsDeck();
    }

    @Override
    public void registerFlagsAndRepairSites() {
    /*    for (IPlayer player : players) {
            if (player.isStandingOnAFlag()) {
                player.registerFlag();
                player.updateArchiveLocation();
            }
            if (player.isStandingOnARepairSite()) {
                player.updateArchiveLocation();
            }
        }*/
    }

    @Override
    public void registerLaserDamage() {
     /*   board.fireLasers();
        for (IPlayer player : players) { // Question: Do powered down robots shoot lasers?
            player.fireLaser();
        }*/
    }

    @Override
    public void askPlayersIfTheyWantToPowerDown() {
    /*    // TODO: powered down players that have been damaged
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
        }*/
    }

    @Override
    public void playersProgramRobots() {
        currentGameStatus = SELECT_CARDS;
        for (IPlayer player : activePlayers) {
            // TODO: Check if this can be made to run in parallel using streams.
            // TODO: Implement timer for slow players?


            //player.movePlayer();
            //   player.addCardsToProgramRegister();
            player.removeRemainingCardsInHand();
        }
    }

    @Override
    public void dealOutProgramCards() {
        programCards.shuffle();
        for (IPlayer player : activePlayers) {
            int numberOfCardsPlayerCanDraw =
                    calculateTheNumberOfCardsThePlayerCanDraw(player);
            System.out.println("player receives " + numberOfCardsPlayerCanDraw + " cards");
            player.receiveCards(programCards.handOutNCards(numberOfCardsPlayerCanDraw));
        }
    }

    @Override
    public void processPoweredDownPlayers() {
    /*    for (IPlayer player : players) {
            if (player.isPoweredDown()) {
                player.removeAllDamageTokensFromPlayer();
                activePlayers.remove(player);
            }
        }*/
    }

    @Override
    public int calculateTheNumberOfCardsThePlayerCanDraw(IPlayer player) {
        int numberOfCards = 9; // The default and maximum number of cards
        // that can be dealt to a player.
        int playerDamage = player.getPlayerDamage(); // The number of damage tokens
        // the player has received.
        if (playerDamage >= 10) {
            return 0;
        }
        return (numberOfCards - playerDamage);
    }

    @Override
    public void initializeGame() {
        //TODO: Make initializeGame take a gameboard as parameter,
        // which can be chosen at the start menu?
        addPlayers();
        String gameBoardLayout = "16C12R" +
                "f....r.rrr...f.." +
                ".rrrrrrr....uu.." +
                ".r.........c...." +
                ".r...f....|....." +
                ".r......ll....p.." +
                "rr......f......." +
                "ll.....w....C..." +
                ".r..p....lll...." +
                ".r.....w........" +
                ".r.....w.....p.." +
                ".r...f....-....." +
                ".r....WW....dd..";
        this.board = new GameBoard(gameBoardLayout);
        this.programCards = new Deck();
        this.discardedProgramCards = new Deck();
        this.programCards.createProgramCardsDeck();
        this.everyFlagHasBeenVisited = false;
        this.numberOfPlayersLeftInTheGame = players.size();
    }

    @Override
    public void addPlayers() {
       // TODO: select game piece, if pieces have different design?
     /*   this.players = new ArrayList<>();
        int numberOfPlayers = playerCommunication.askForNumberOfPlayers();
        String userName = "";
        int numberOfPlayers = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            userName = playerCommunication.askForUserName();
            IPlayer player = new Player(userName);
            players.add(player);
        }
      */

     // Hardcoded players for demonstration.
        IPlayer player1 = new Player("Buzz", 0, 10);
        IPlayer player2 = new Player("Emma", 5, 10);
        IPlayer player3 = new Player("G-bot", 2, 5);
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.activePlayers = new ArrayList<>();
        this.activePlayers.addAll(players);
    }

    @Override
    public List<IPlayer> getActivePlayers() {
        return activePlayers;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public GameStatus getTheCurrentGameStatus() {
        return this.currentGameStatus;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return this.currentlyActingPlayer;
    }


    public void removeTargetPlayerFromTheGame(IPlayer player) {
        this.activePlayers.remove(player);

        this.numberOfPlayersLeftInTheGame -= 1;
    }

    public GameBoard getBoard() {
        return board;
    }

    @Override
    public void setGameStatus(GameStatus status) {
        currentGameStatus = status;
    }

    @Override
    public void doTurn() {
        switch (this.currentGameStatus) {
            case EXECUTING_INSTRUCTIONS:
                executingInstructions();
                return;
            case EXECUTING_GAME_BOARD_OBJECTS:
                executingGameBoardObjects();
        }

    }

    private void executingGameBoardObjects() {
        for (IPlayer p: players) {
            if(board.moveValid(p.getX(),p.getY())) {
                board.getObject(p.getX(), p.getY()).doAction(p);
            } else {
                p.respawnAtLastArchiveMarker();
            }
        }
        setGameStatus(EXECUTING_INSTRUCTIONS);

    }

    private void executingInstructions() {
        if (selectedCards[0].getSize() == 0) {
            setupCardSelectionForNewRound();
            setGameStatus(GameStatus.SELECT_CARDS);

            return;
        }
        for (int i = 0; i < players.size(); i++) {
            ICard currentCard = selectedCards[i].removeCard(selectedCards[i].getSize() - 1);
            players.get(i).movePlayer(currentCard);
            discardedProgramCards.addCardToDeck(currentCard);
        }
        setGameStatus(EXECUTING_GAME_BOARD_OBJECTS);
    }

    private void setupCardSelectionForNewRound() {
        // all un used cards is moved to discard pile
        for (IPlayer player: players) {
            IDeck cardsInPlayerDeck = player.getCardsInHand();
            while (cardsInPlayerDeck.getSize() != 0) {
                ICard unUsedPlayerCard = cardsInPlayerDeck.removeCard(0);
                
                discardedProgramCards.addCardToDeck(unUsedPlayerCard);
            }
        }
        // new cards is dealt
        for (IPlayer player: players) {
            IDeck playerCards = player.getCardsInHand();
            while (playerCards.getSize() != 9) {
                if (programCards.getSize() == 0) {
                    int discardSize = discardedProgramCards.getSize();
                    programCards.addCollectionOfCardsToDeck(discardedProgramCards.handOutNCards(discardSize));
                    programCards.shuffle();
                }
                playerCards.addCardToDeck(programCards.removeCard(0));
            }
        }
    }

    @Override
    public void setUpTurn(IDeck[] selectedCards) {
        this.selectedCards = selectedCards;
    }
}
