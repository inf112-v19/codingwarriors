package inf112.project.RoboRally.Cards;
import java.util.ArrayList;

public interface IDeck {

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
     * Make a new deck of cards.
     */
    void createProgramCardsDeck();

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

}
