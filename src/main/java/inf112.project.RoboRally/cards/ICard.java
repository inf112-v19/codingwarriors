package inf112.project.RoboRally.cards;

public interface ICard {


    /**
     * Get the priority level associated with this card.
     *
     * @return The cards priority number.
     */
    int getPriority();


    /**
     * Get the command/action associated with this card.
     *
     * @return The command contained on the card.
     */
    Action getCardCommand();


    /**
     * Compare two cards based on their priority.
     *
     * @return 1 if this has a higher priority,<br>
     *     -1 if this has a lower priority,<br>
     *     and 0 if both cards have equal priority.
     */
    int compareTo(ICard that);
}
