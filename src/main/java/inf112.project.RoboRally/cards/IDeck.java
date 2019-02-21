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
    void swapCardsInPosition(Integer posA, Integer posB);


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
     * Adds a given collection of cards to the deck.
     *
     * @param collection
     *                  The collection of cards to be added.
     */
    void addCollectionOfCardsToDeck(Collection<ICard> collection);


    /**
     * Get the card residing at the given position in the deck.<br><br>
     * The card is not removed from the deck.
     *
     * @param position
     *                  The index of the card in the deck.
     * @return the card at the given position.
     */
    ICard getCardAtPosition(Integer position);


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
     * Adds the card to the deck at the given position.
     *
     * @param index
     *              The index to insert the card at.
     * @param card
     *              The card that will be inserted into the deck
     */
    void addCardToDeckAtPosition(Integer index, ICard card);


    /**
     * Add a card to the bottom off the deck.
     *
     * @param card
     *              The card to be added.
     */
    void addCardToDeck(ICard card);


    /**
     * Remove the card at the given position from the deck.
     *
     * @param position
     *              The index of the card to be removed.
     * @return A reference to the removed card.
     */
    ICard removeCard(Integer position);


    /**
     * Get a String representation of the card at the given position.
     *
     * @param position
     *                  The index of the card to be shown.
     * @return The String representation of the card at the given position.
     */
    String showCard(Integer position);


    /**
     * Transfer the given number of cards from this deck,
     * to the target.<br><br>
     *
     * Removes the cards from the origin,
     * and inserts them in the target deck.
     *
     * @param numberOfCardsToTransfer
     *                  The number of cards to relocate.
     * @param targetDeck
     *                  The deck to transfer the cards to.
     */
    void transferNCardsFromThisDeckToTargetDeck(Integer numberOfCardsToTransfer, IDeck targetDeck);
}
