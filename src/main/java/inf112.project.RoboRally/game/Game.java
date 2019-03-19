package inf112.project.RoboRally.game;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.Deck;
import inf112.project.RoboRally.cards.ICard;
import inf112.project.RoboRally.cards.IDeck;

import java.util.ArrayList;
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
                "................" +
                ".r...f....|....." +
                ".r......ll....p.." +
                "rr......f......." +
                "ll.....w....C..." +
                ".r..p....lll...." +
                ".r.....w........" +
                ".r.....w.....p.." +
                ".r...f....-....." +
                ".r....WW....dd..";
        String gameBoardWalls = "" +
                "fnnnnnnnnnnnnnng" +
                "|atr...........l" +
                "w..............e" +
                "................" +
                "................" +
                "................" +
                "....Mexico......" +
                "................" +
                "................" +
                "................" +
                "dsolgndpgjpgojsd" +
                "......haha......";
        this.board = new GameBoard(gameBoardLayout, gameBoardWalls);
        this.programCards = new Deck();
        this.discardedProgramCards = new Deck();
        this.programCards.createProgramCardsDeck();
        this.everyFlagHasBeenVisited = false;
        this.numberOfPlayersLeftInTheGame = players.size();
    }

    @Override
    public void addPlayers() {
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
        for (IPlayer player: players) {
            if(board.moveValid(player.getX(),player.getY())) {
                board.getObject(player.getX(), player.getY()).doAction(player);
                List coordinatesHitByLaser = player.fireLaser(board.getRows(), board.getColumns());
                List shortestPathToPlayer = shortestPathToObstacle(coordinatesHitByLaser);
                for (IPlayer otherPlayer : players) { // poor optimization
                    if (shortestPathToPlayer.contains(((Player) otherPlayer).getCoordinates())) {
                        otherPlayer.takeOneDamage();
                        System.out.println(otherPlayer.getName() + " was hit by a laser from " + player.getName());
                    }
                }
            } else {
                player.respawnAtLastArchiveMarker();
            }
        }
        setGameStatus(EXECUTING_INSTRUCTIONS);

    }

    // in the case of multiple players being on the same line this method checks for the shortest path to a player
    public List shortestPathToObstacle(List laserCoordinates) {
        List finalLaserCoordinates = new ArrayList();
        List comparativeList = new ArrayList();
        int smallestListSize = laserCoordinates.size();

        for (IPlayer player : players) {
            for (int i = 0; i < laserCoordinates.size(); i++) {
                if (laserCoordinates.get(i).equals(((Player) player).getCoordinates())) {
                    comparativeList.add(laserCoordinates.get(i));
                    if (comparativeList.size() < smallestListSize) {
                        smallestListSize = comparativeList.size();
                        finalLaserCoordinates = new ArrayList(comparativeList);
                        comparativeList.clear();
                    }
                    break;
                }
                comparativeList.add(laserCoordinates.get(i));
            }
            comparativeList.clear();
        }
        return finalLaserCoordinates;
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
