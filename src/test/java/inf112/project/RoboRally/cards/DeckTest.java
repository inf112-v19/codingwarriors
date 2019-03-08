package inf112.project.RoboRally.cards;

import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.objects.ConveyorBelt;
import inf112.project.RoboRally.objects.Flag;
import inf112.project.RoboRally.objects.Floor;
import inf112.project.RoboRally.objects.RotationCog;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    private final int NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK = 84;
    private IDeck deck;

    @Before
    public void setUp() throws Exception {
        this.deck = new Deck();
    }

    @Test
    public void newlyCreatedDecksShouldBeEmpty() {
        IDeck newDeck = new Deck();
        assertEquals(0, newDeck.getSize());
    }

    @Test
    public void newlyCreatedProgramCardsDeckShouldHaveCorrectAmountOfCards() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());
    }

    @Test
    public void getSizeShouldReturnCorrectAmountOfCardsInADeck() {
        deck.addCardToDeck(new Card(200, Action.FORWARD_3));
        assertEquals(1, deck.getSize());

        deck.addCardToDeck(new Card(100, Action.ROTATE_LEFT));
        assertEquals(2, deck.getSize());

        deck.addCardToDeck(new Card(300, Action.U_TURN));
        assertEquals(3, deck.getSize());

        deck.removeAllCardsFromDeck();
        assertEquals(0, deck.getSize());
    }

    @Test
    public void removingAllCardsFromADeckShouldLeaveTheDeckEmpty() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());

        deck.removeAllCardsFromDeck();
        assertEquals(0, deck.getSize());
    }

    @Test
    public void enhancedForLoopShouldWork() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());
        
        try {
            for (ICard card : deck) {}
        } catch (NullPointerException e) {
            fail("Deck iterator is not working properly");
        }
    }

    @Test
    public void sortingADeckShouldLeaveItSortedByPriority() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(300, Action.ROTATE_LEFT);
        ICard card3 = new Card(180, Action.FORWARD_2);

        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        deck.sortDeckAfterCardPriority();
        assertEquals(card2, deck.getCardAtPosition(0));
        assertEquals(card1, deck.getCardAtPosition(1));
        assertEquals(card3, deck.getCardAtPosition(2));
    }

    @Test
    public void getCardAtPositionShouldReturnCardAtTheGivenPosition() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(100, Action.FORWARD_2);
        ICard card3 = new Card(420, Action.FORWARD_2);

        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        assertEquals(card1, deck.getCardAtPosition(0));
        assertEquals(card2, deck.getCardAtPosition(1));
        assertEquals(card3, deck.getCardAtPosition(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCardAtPositionShouldFailIfPositionIsTooHigh() {
        deck.getCardAtPosition(4000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCardAtPositionShouldFailIfPositionIsNegative() {
        deck.getCardAtPosition(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCardAtPositionShouldFailIfPositionIsNull() {
        deck.getCardAtPosition(null);
    }

    @Test
    public void addingACollectionOfCardsToADeckShouldWork() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(100, Action.FORWARD_2);
        ICard card3 = new Card(420, Action.FORWARD_2);

        newDeck.addCardToDeck(card1);
        newDeck.addCardToDeck(card2);
        newDeck.addCardToDeck(card3);

        assertEquals(3, newDeck.getSize());
        assertEquals(0, deck.getSize());

        deck.addCollectionOfCardsToDeck(newDeck.handOutNCards(2));
    }

    @Test
    public void transferNCardsFromOneDeckToAnotherShouldWork() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(220, Action.ROTATE_LEFT);
        ICard card2 = new Card(150, Action.FORWARD_2);
        ICard card3 = new Card(490, Action.FORWARD_2);

        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        assertEquals(3, deck.getSize());
        assertEquals(0, newDeck.getSize());

        int numberOfCardsToTransfer = 2;
        deck.transferNCardsFromThisDeckToTargetDeck(numberOfCardsToTransfer, newDeck);

        assertEquals(numberOfCardsToTransfer, newDeck.getSize());
        assertEquals(card1, newDeck.getCardAtPosition(0));
        assertEquals(card2, newDeck.getCardAtPosition(1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void transferringCardsShouldFailIfAmountOfCardsIsTooHigh() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(220, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.transferNCardsFromThisDeckToTargetDeck(10000, newDeck);
    }

    @Test (expected = IllegalArgumentException.class)
    public void transferringCardsShouldFailIfAmountOfCardsIsNegative() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(220, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.transferNCardsFromThisDeckToTargetDeck(-1, newDeck);
    }

    @Test (expected = IllegalArgumentException.class)
    public void transferringCardsShouldFailIfAmountOfCardsIsNull() {
        IDeck newDeck = new Deck();
        ICard card1 = new Card(220, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.transferNCardsFromThisDeckToTargetDeck(null, newDeck);
    }

    @Test (expected = IllegalArgumentException.class)
    public void transferringCardsShouldFailIfTargetDeckIsNull() {
        ICard card1 = new Card(220, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.transferNCardsFromThisDeckToTargetDeck(1, null);
    }

    @Test
    public void deckIsCorrectlyRepresentedAsAString() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(100, Action.FORWARD_2);
        ICard card3 = new Card(150, Action.U_TURN);
        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        String card1Rep = card1.toString();
        String card2Rep = card2.toString();
        String card3Rep = card3.toString();

        assertEquals(card1Rep + card2Rep + card3Rep, deck.toString());
    }

    @Test
    public void showCardAtPosShouldReturnCorrectStringRepresentation() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        ICard card2 = new Card(100, Action.FORWARD_2);
        ICard card3 = new Card(150, Action.U_TURN);
        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        deck.addCardToDeck(card3);

        assertEquals(card1.toString(), deck.showCard(0));
        assertEquals(card2.toString(), deck.showCard(1));
        assertEquals(card3.toString(), deck.showCard(2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void handOutNCardsShouldRemoveCardsFromDeck() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());

        int currentNumberOfCardsInTheDeck = NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK;
        for (int i = 0; i < 13; i++) {
            deck.handOutNCards(i);
            currentNumberOfCardsInTheDeck -= i;
            assertEquals(currentNumberOfCardsInTheDeck, deck.getSize());
        }

        deck.handOutNCards(null);
        deck.handOutNCards(-1);
        deck.handOutNCards(100000);
    }

    @Test
    public void shouldBeAbleToSwapPositionOfCardsAtTwoGivenPositions() {
        ICard card1 = new Card(300, Action.U_TURN);
        ICard card2 = new Card(500, Action.FORWARD_3);
        deck.addCardToDeck(card1);
        deck.addCardToDeck(card2);
        assertEquals(card1, deck.getCardAtPosition(0));
        assertEquals(card2, deck.getCardAtPosition(1));

        deck.swapCardsInPosition(0, 1);

        assertEquals(card1, deck.getCardAtPosition(1));
        assertEquals(card2, deck.getCardAtPosition(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void swapCardsShouldFailIfPositionAIsNegative() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.swapCardsInPosition(-1, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void swapCardsShouldFailIfPositionBIsNegative() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.swapCardsInPosition(0, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void swapCardsShouldFailIfPositionAIsTooHigh() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.swapCardsInPosition(53, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void swapCardsShouldFailIfPositionBIsToHigh() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.swapCardsInPosition(0, 56);
    }

    @Test (expected = IllegalArgumentException.class)
    public void swapCardsShouldFailIfPositionAIsNull() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.swapCardsInPosition(null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void swapCardsShouldFailIfPositionBIsNull() {
        ICard card1 = new Card(200, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.swapCardsInPosition(0, null);
    }

    @Test
    public void shouldBeAbleToRemoveACardAtAGivenPosition() {
        ICard card1 = new Card(400, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        assertEquals(1, deck.getSize());

        deck.removeCard(0);
        assertEquals(0, deck.getSize());
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeCardShouldFailIfPositionIsTooHigh() {
        ICard card1 = new Card(230, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        assertEquals(1, deck.getSize());

        deck.removeCard(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeCardShouldFailIfPositionIsNegative() {
        ICard card1 = new Card(230, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        assertEquals(1, deck.getSize());

        deck.removeCard(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeCardShouldFailIfPositionIsNull() {
        ICard card1 = new Card(230, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        assertEquals(1, deck.getSize());

        deck.removeCard(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void showCardShouldFailIfPositionIsTooHigh() {
        ICard card1 = new Card(230, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.showCard(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void showCardShouldFailIfPositionIsNegative() {
        ICard card1 = new Card(230, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.showCard(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void showCardShouldFailIfPositionIsNull() {
        ICard card1 = new Card(230, Action.ROTATE_LEFT);
        deck.addCardToDeck(card1);
        deck.showCard(null);
    }

    @Test
    public void shufflingTheDeckShouldLeaveItShuffled() {
        deck.createProgramCardsDeck();
        assertEquals(NUMBER_OF_CARDS_IN_A_PROGRAM_CARDS_DECK, deck.getSize());
        List<ICard> originalCardPositions = new ArrayList<>();
        for (int i = 0; i < deck.getSize(); i++) {
            originalCardPositions.add(deck.getCardAtPosition(i));
        }

        int numberOfCards = deck.getSize(); // Shuffle the deck many times,
        int[] numberOfChanges = new int[numberOfCards]; // and count the number of times
        int numberOfIterations = 100000;    // a card is not in its original position.
        for (int i = 0; i < numberOfIterations; i++) {
            deck.shuffle();
            for (int index = 0; index < deck.getSize(); index++) {
                if (deck.getCardAtPosition(index) != originalCardPositions.get(index)) {
                    numberOfChanges[index] += 1;
                }
            }
        }

        int deviation = numberOfIterations / 500;  // Test that the distribution of card
        int baseCase = numberOfChanges[0];  // placement is relatively even.
        for (int i = 0; i < numberOfCards; i++) {
            if (numberOfChanges[i] > (baseCase + deviation)) {
                fail("Deck is not shuffled evenly.");
            }
            if (numberOfChanges[i] < (baseCase - deviation)) {
                fail("Deck is not shuffled evenly.");
            }
        }
    }

    @Test
    public void addingACardShouldAddTheCardToTheBottomOfTheDeck() {
        ICard card = new Card(400, Action.ROTATE_LEFT);
        assertEquals(0, deck.getSize());

        deck.addCardToDeck(card);
        assertEquals(1, deck.getSize());
        assertEquals(card, deck.getCardAtPosition(0));

        ICard anotherCard = new Card(100, Action.U_TURN);
        deck.addCardToDeck(anotherCard);

        assertEquals(2, deck.getSize());
        assertEquals(anotherCard, deck.getCardAtPosition(1));
        assertNotEquals(anotherCard, deck.getCardAtPosition(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingNullToADeckShouldFail() {
        deck.addCardToDeck(null);
    }

    @Test
    public void addingACardToPositionShouldWork() {
        ICard card1 = new Card(300, Action.ROTATE_LEFT);
        deck.addCardToDeckAtPosition(0, card1);
        assertEquals(1, deck.getSize());
        assertEquals(card1, deck.getCardAtPosition(0));

        ICard card2 = new Card(530, Action.FORWARD_3);
        deck.addCardToDeckAtPosition(0, card2);
        assertEquals(2, deck.getSize());
        assertEquals(card1, deck.getCardAtPosition(1));
        assertEquals(card2, deck.getCardAtPosition(0));

        ICard card3 = new Card(300, Action.U_TURN);
        deck.addCardToDeckAtPosition(1, card3);
        assertEquals(3, deck.getSize());
        assertEquals(card1, deck.getCardAtPosition(2));
        assertEquals(card2, deck.getCardAtPosition(0));
        assertEquals(card3, deck.getCardAtPosition(1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingACardToDeckShouldFailIfPositionIsNegative() {
        ICard card = new Card(200, Action.FORWARD_3);
        deck.addCardToDeckAtPosition(-1, card);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingACardToDeckShouldFailIfPositionIsTooHigh() {
        ICard card = new Card(200, Action.FORWARD_3);
        deck.addCardToDeckAtPosition(1, card);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingACardToDeckShouldFailIfPositionIsNull() {
        ICard card = new Card(200, Action.FORWARD_3);
        deck.addCardToDeckAtPosition(null, card);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addingACardToDeckShouldFailIfCardIsNull() {
        deck.addCardToDeckAtPosition(0, null);
    }
}