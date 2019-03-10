package inf112.project.RoboRally.cards;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface IDeck extends Iterable<ICard>{

    /**
     * Give the specified amount of cards, from those available in the deck,
     * to the requesting player.
     *
     * @param num
     *              The number of requested cards.
     * @return The cards dealt to the requesting player.
     *
     * @throws IllegalArgumentException
     *      if the number of cards is null (num == null),<br>
     *      or num is negative (num < 0),<br>
     *      or num is too high (num > deck.getSize()).
     */
    List<ICard> handOutNCards(Integer num);


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
     *
     * @throws IllegalArgumentException
     *      if posA == null,<br>
     *      or posB == null,<br>
     *      or posA < 0,<br>
     *      or posB < 0,<br>
     *      or posA >= deck.getSize(),<br>
     *      or posB >= deck.getSize().
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
     *
     * @throws IllegalArgumentException
     *      if the position is null (position == null),<br>
     *      or position is negative (position < 0),<br>
     *      or position is greater than the deck size (position >= deck.getSize()).
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
     *
     * @throws IllegalArgumentException
     *      if the index is null (index == null),<br>
     *      or the index is negative (index < 0),<br>
     *      or the index is greater than the deck size (index > deck.getSize()).
     *      <br>
     *      If the card is null (card == null).
     */
    void addCardToDeckAtPosition(Integer index, ICard card);


    /**
     * Add a card to the bottom off the deck.
     *
     * @param card
     *              The card to be added.
     *
     * @throws IllegalArgumentException
     *      if card == null.
     */
    void addCardToDeck(ICard card);


    /**
     * Remove the card at the given position from the deck.
     *
     * @param position
     *              The index of the card to be removed.
     * @return A reference to the removed card.
     *
     * @throws IllegalArgumentException
     *      if the position is null (position == null),<br>
     *      or position is negative (position < 0),<br>
     *      or position is greater than the deck size (position >= deck.getSize()).
     */
    ICard removeCard(Integer position);


    /**
     * Get a String representation of the card at the given position.
     *
     * @param position
     *                  The index of the card to be shown.
     * @return The String representation of the card at the given position.
     *
     * @throws IllegalArgumentException
     *      if the position is null (position == null),<br>
     *      or position is negative (position < 0),<br>
     *      or position is greater than the deck size (position >= deck.getSize()).
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
     *
     * @throws IllegalArgumentException <br>
     *      if the numberOfCardsToTransfer is null<br> (numberOfCardsToTransfer == null),<br>
     *      or numberOfCardsToTransfer is negative<br> (numberOfCardsToTransfer < 0),<br>
     *      or numberOfCardsToTransfer is greater than the deck size<br>
     *          (numberOfCardsToTransfer >= deck.getSize()).<br>
     *      If targetDeck is null (targetDeck == null).
     */
    void transferNCardsFromThisDeckToTargetDeck(Integer numberOfCardsToTransfer, IDeck targetDeck);


    /**
     * Replaces the card at the given position with the new card.
     *
     * @param position
     *              The position of the card to replace.
     * @param card
     *              The replacement card.
     * @return The removed card.
     *
     * @throws IllegalArgumentException
     *       if the position is negative (position < 0),<br>
     *       position is too high (position >= deck.getSize()),<br>
     *       position is null (position = null),<br>
     *       or if the card is null (card = null).
     */
    ICard replaceCardAtPosition(Integer position, ICard card);
}
