package inf112.project.RoboRally.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static inf112.project.RoboRally.cards.Action.*;

public class Deck implements IDeck{

    private List<ICard> cardDeck;

    public Deck() {
        this.cardDeck = new ArrayList<>();
    }


    @Override
    public List<ICard> handOutNCards(Integer numberOfCards) {
        if (numberOfCards == null
                || numberOfCards < 0
                || numberOfCards > this.getSize()) {
            throw new IllegalArgumentException();
        }

        List<ICard> selectedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            selectedCards.add(cardDeck.remove(0));
        }
        return selectedCards;
    }

    @Override
    public void addCardToDeckAtPosition(Integer index, ICard card) {
        if (index == null
                || index > this.getSize()
                || index < 0) {
            throw new IllegalArgumentException();
        }
        if (card == null) {
            throw new IllegalArgumentException();
        }
        this.cardDeck.add(index, card);
    }

    @Override
    public void addCardToDeck(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("null is not a valid card");
        }
        this.cardDeck.add(card);
    }

    @Override
    public ICard removeCard(Integer position) {
        if (position == null
                || position >= this.getSize()
                || position < 0) {
            throw new IllegalArgumentException();
        }
        return this.cardDeck.remove((int) position);
    }

    @Override
    public String showCard(Integer position) {
        if (position == null
                || position >= this.getSize()
                || position < 0) {
            throw new IllegalArgumentException("Position is not valid.");
        }
        return cardDeck.get(position).toString();
    }

    @Override
    public void transferNCardsFromThisDeckToTargetDeck(Integer numberOfCardsToTransfer, IDeck targetDeck) {
        if (numberOfCardsToTransfer == null
                || numberOfCardsToTransfer < 0
                || numberOfCardsToTransfer > this.getSize()
                || targetDeck == null) {
            throw new IllegalArgumentException();
        }
        List<ICard> selectedCards = this.handOutNCards(numberOfCardsToTransfer);
        targetDeck.addCollectionOfCardsToDeck(selectedCards);
    }

    @Override
    public ICard replaceCardAtPosition(Integer position, ICard card) {
        if (position == null
                || position < 0
                || position >= this.getSize()) {
            throw new IllegalArgumentException();
        }
        if (card == null) {
            throw new IllegalArgumentException();
        }
        ICard removedCard = this.removeCard(position);
        this.addCardToDeckAtPosition(position, card);
        return removedCard;
    }

    @Override
    public void createProgramCardsDeck() {
        int priority = 0; // The priority to give the card.
        for (int i = 0; i < 6; i++) {
            priority += 10;
            cardDeck.add(new Card(priority, U_TURN));
        }
        for (int i = 0; i < 18; i++) {
            priority += 20;
            cardDeck.add(new Card(priority, ROTATE_RIGHT));
        }

        priority = 50;
        for (int i = 0; i < 18; i++) {
            priority += 20;
            cardDeck.add(new Card(priority, ROTATE_LEFT));
        }

        priority = 420;
        for (int i = 0; i < 6; i++) {
            priority += 10;
            cardDeck.add(new Card(priority, BACKWARDS));
        }
        for (int i = 0; i < 18; i++) {
            priority += 10;
            cardDeck.add(new Card(priority, FORWARD_1));
        }
        for (int i = 0; i < 12; i++) {
            priority += 10;
            cardDeck.add(new Card(priority, FORWARD_2));
        }
        for (int i = 0; i < 6; i++) {
            priority += 10;
            cardDeck.add(new Card(priority, FORWARD_3));
        }
    }

    @Override
    public void swapCardsInPosition(Integer posA, Integer posB) {
        if (posA == null || posA >= cardDeck.size() || posA < 0 ) {
            throw new IllegalArgumentException();
        }
        if (posB == null || posB >= cardDeck.size() || posB < 0) {
            throw new IllegalArgumentException();
        }

        ICard cardA = cardDeck.get(posA);
        ICard cardB = cardDeck.get(posB);
        cardDeck.set(posA, cardB);
        cardDeck.set(posB, cardA);
    }


    @Override
    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    @Override
    public int getSize() {
        return cardDeck.size();
    }

    @Override
    public String toString() {
        StringBuilder deck = new StringBuilder();
        for (ICard card: cardDeck) {
            deck.append(card.toString());
        }
        return deck.toString();
    }

    @Override
    public void addCollectionOfCardsToDeck(Collection<ICard> collection) {
        this.cardDeck.addAll(collection);
    }

    @Override
    public ICard getCardAtPosition(Integer position) {
        if (position == null
                || position >= this.cardDeck.size()
                || position < 0) {
            throw new IllegalArgumentException();
        }
        return this.cardDeck.get(position);
    }

    @Override
    public void sortDeckAfterCardPriority() {
            Collections.sort(this.cardDeck, Collections.<ICard>reverseOrder());
    }

    @Override
    public Iterator<ICard> iterator() {
        return this.cardDeck.listIterator();
    }

    @Override
    public void removeAllCardsFromDeck() {
        this.cardDeck.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.cardDeck.size() == 0;
    }
}
