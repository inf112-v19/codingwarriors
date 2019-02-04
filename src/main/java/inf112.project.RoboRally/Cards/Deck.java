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
