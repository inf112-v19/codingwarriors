package inf112.project.RoboRally.cards;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public interface IDeck extends Iterable<ICard>{

    /**
     * Give the specified amount of cards, from those available in the deck,
     * to the requesting player.
     *
     * @param num
     *              The number of requested cards.
     * @return The cards dealt to the requesting player.
     */
    ArrayList<ICard> handOutNCards(Integer num);


    /**
     * Make a new deck of program cards.
     */
    void createProgramCardsDeck();


    /**
     * Swaps the position of the two cards at the given indices.
     *
     * @param posA
     *              Position of card A.
     * @param posB
     *              Position of card B.
     */
    void swapCardsInPosition(int posA, int posB);


    /**
     * Shuffle the deck to make things a bit more random.
     */
    void shuffle();


    /**
     * Get the number of cards currently left in the deck.
     *
     * @return The number of cards currently in the deck.
     */
    int getSize();


    /**
     *
     *
     *
     * @param collection
     */
    void addCollectionOfCardsToDeck(Collection<ICard> collection);


    /**
     *
     *
     *
     * @param position
     * @return
     */
    ICard getCardAtPosition(int position);


    /**
     *
     *
     *
     *
     * @param card
     */
    void addCardToDeck(ICard card);


    /**
     * Sort the deck after card priority,
     * so that the highest priority cards come first.<br><br>
     *
     * For example, a deck with priorities:<br>
     * deck = {200, 300, 522, 120}
     * sorted becomes<br>
     * deck = {522, 300, 200, 120}
     */
    void sortDeckAfterCardPriority();


    /**
     * Fetches a listIterator for this deck.
     *
     * @return An iterator over this deck of cards.
     */
    Iterator<ICard> iterator();


    /**
     * Empties the deck, so that no cards remain.
     */
    void removeAllCardsFromDeck();


    /**
     * Gives A deck of cards containing the specified amount of cards, from those available in the deck,
     * to the requesting player.
     *
     * @param cards
     *              The number of requested cards.
     * @return A deck of cards containing the cards dealt to the requesting player.
     */
    Deck handOutCards(int cards);


    /**
     * Adds the card to the deck
     * @param card
     *              The card that will be inserted into the deck
     */
    void addCard(ICard card);
}
