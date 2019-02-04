package inf112.project.RoboRally.Cards;

import java.util.ArrayList;

import static inf112.project.RoboRally.Cards.Action.*;

public class Deck implements IDeck{

    private ArrayList<ICard> cardDeck;

    public Deck() {
        this.cardDeck = new ArrayList<ICard>();
    }



    @Override
    public ArrayList<IDeck> handOutCards(int num) {
        return null;
    }

    @Override
    public void createDeck() {

    }

    @Override
    public void shuffle() {

    }

    @Override
    public int size() {
        return cardDeck.size();
    }
}
