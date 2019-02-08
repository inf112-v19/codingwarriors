package inf112.project.RoboRally.Cards;

import java.util.ArrayList;
import java.util.Collections;

import static inf112.project.RoboRally.Cards.Action.*;

public class Deck implements IDeck{

    private ArrayList<ICard> cardDeck;

    public Deck() {
        this.cardDeck = new ArrayList<ICard>();
    }


    @Override
    public ArrayList<ICard> handOutNCards(Integer num) {
        if (num == null|| num < 0 || num > this.getSize()) {
            throw new IllegalArgumentException("num is not a valid amount of cards");
        }

        ArrayList<ICard> selectedCards = new ArrayList<ICard>();
        for (int i = 0; i < num; i++) {
            selectedCards.add(cardDeck.remove(0));
        }
        return selectedCards;
    }

    public Deck handOutCards(int numberOfCards) {
        ArrayList<ICard> DrawnCards = handOutNCards(numberOfCards);
        Deck deck = new Deck();
        for (ICard card : DrawnCards) {
            deck.addCard(card);
        }
        return deck;
    }

    public void addCard(ICard card) {
        cardDeck.add(card);
    }

    public ICard removeCard(int index) {
        if (index > this.getSize() || cardDeck.isEmpty()) { return null; }
        return cardDeck.remove(index);
    }

    public String showCard(int index) {
        if (index > this.getSize() || cardDeck.isEmpty()) { return ""; }
        return cardDeck.get(index).toString();
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
    public void swapCardsInPosition(int posA, int posB) {
        if (posA >= cardDeck.size() || posA < 0) {
            throw new IllegalArgumentException("posA is not a valid position");
        }
        if (posB >= cardDeck.size() || posB < 0) {
            throw new IllegalArgumentException("posB is not a valid position");
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

    public String toString() {
        StringBuilder deck = new StringBuilder();
        for (ICard card: cardDeck) {
            deck.append(card.toString());
        }
        return deck.toString();
    }
}
