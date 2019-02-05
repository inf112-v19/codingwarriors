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

    @Override
    public void createProgramCardsDeck() {
        int priority = 10;
        for (int i = 0; i < 6; i++) {
            cardDeck.add(new Card(priority, U_TURN));
            priority += 10;
        }
        System.out.println(this.getSize());
        System.out.println(priority);

        for (int i = 0; i < 18; i++) {
            cardDeck.add(new Card(priority, ROTATE_LEFT));
            priority += 20;
        }
        System.out.println(this.getSize());
        System.out.println(priority);

        priority = 80;
        for (int i = 0; i < 18; i++) {
            cardDeck.add(new Card(priority, ROTATE_RIGHT));
            priority += 20;
        }
        System.out.println(this.getSize());
        System.out.println(priority);


        for (int i = 0; i < 6; i++) {
            cardDeck.add(new Card(priority, BACKWARDS));
            priority += 10;
        }
        System.out.println(this.getSize());
        System.out.println(priority);

        //priority = 490;
        for (int i = 0; i < 18; i++) {
            cardDeck.add(new Card(priority, FORWARD_1));
            priority += 10;
        }
        System.out.println(this.getSize());
        System.out.println(priority);


//        priority = 670;
        for (int i = 0; i < 12; i++) {
            cardDeck.add(new Card(priority, FORWARD_2));
            priority += 10;
        }
        System.out.println(this.getSize());
        System.out.println(priority);

  //      priority = 790;
        for (int i = 0; i < 6; i++) {
            cardDeck.add(new Card(priority, FORWARD_3));
            priority += 10;
        }
        System.out.println(this.getSize());
        System.out.println(priority);
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
}
