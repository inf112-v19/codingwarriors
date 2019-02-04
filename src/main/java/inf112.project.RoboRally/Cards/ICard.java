package inf112.project.RoboRally.Cards;

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
    Action getCommand();


}
