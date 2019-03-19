package inf112.project.RoboRally.cards;

import java.util.Collection;

public interface IProgramRegister {


    /**
     * Get the current size of the register.
     *
     * @return The amount of cards in the register.
     */
    int getSize();


    /**
     * Add a card to the register in the specified slot.
     *
     * @param slotNumber
     *                  The position the card should be inserted into.
     * @param card
     *              The card to add.
     * @return The removed card.
     *
     * @throws IllegalArgumentException
     *        if the slotNumber is negative (slotNumber < 0),<br>
     *        slotNumber is too high (slotNumber >= register.getSize()),<br>
     *        slotNumber is null (slotNumber = null),<br>
     *        or if the card is null (card = null),<br>
     *        or if the register slot with this slotNumber is locked.
     */
    ICard replaceTheCardInRegisterSlotNumberNWithThisCard(Integer slotNumber, ICard card);


    /**
     * Get the card residing in the specified register slot.<br><br>
     *
     * The card is not removed.
     *
     * @param slotNumber
     *                  The index of the card to fetch.
     * @return The card from the specified slot.
     *
     * @throws IllegalArgumentException
     *        if the slotNumber is negative (slotNumber < 0),<br>
     *        slotNumber is greater than the number of slots
     *        (slotNumber >= register.getSize()),<br>
     *        or if slotNumber is null (slotNumber = null).
     */
    ICard getCardInSlotNumber(Integer slotNumber);


    /**
     * Takes a collection of ICards and adds the cards to the register.<br>
     *
     * If a register slot is locked, the new card is discarded.
     *
     * @param listOfCards
     *                      The collection of cards to add to the register.
     * @throws IllegalArgumentException
     *        if the list of cards is null (listOfCards = null),<br>
     *        or contains more elements than the number of slots
     *        (listOfCards.size() > register.getNumberOfRegisterSlots()).
     */
    void addCollectionOfCardsToRegister(Collection<ICard> listOfCards);


    /**
     * Converts a deck of cards into a collection,
     * and adds it to the register.
     *
     * @param deckOfCards
     *                  The deck of cards to be added.
     */
    void addADeckOfCardsToTheRegister(IDeck deckOfCards);

    /**
     * Checks if the specified register slot is locked.
     *
     * @param slotNumber
     *                  The register slot to be checked.
     * @return true if the register slot is locked,<br>
     *     false otherwise.
     * @throws IllegalArgumentException
     *        if the slotNumber is negative (slotNumber < 0),<br>
     *        slotNumber is too high (slotNumber > register.getNumberOfRegisterSlots()),<br>
     *        or if slotNumber is null (slotNumber = null).
     */
    boolean checkIsTheRegisterSlotNumberNLocked(Integer slotNumber);


    /**
     * Lock the specified register slot.<br><br>
     *
     * Prevents the card residing there from being replaced.
     *
     * @param slotNumber
     *                  The register slot to be locked.
     * @throws IllegalArgumentException
     *        if the slotNumber is negative (slotNumber < 0),<br>
     *        slotNumber is too high (slotNumber >= register.getSize()),<br>
     *        or if slotNumber is null (slotNumber = null).
     */
    void lockRegisterSlotNumber(Integer slotNumber);


    /**
     * Get the number of slots in the register.
     *
     * @return The number of register slots.
     */
    int getNumberOfRegisterSlots();


    /**
     * Unlocks a locked register slot.<br><br>
     *
     * The card residing in the unlocked register slot can now be replaced.
     *
     * @param slotNumber
     *                  The index of the register slot to be unlocked.
     * @throws IllegalArgumentException
     *        if the slotNumber is negative (slotNumber < 0),<br>
     *        slotNumber is too high (slotNumber > register.getNumberOfRegisterSlots()),<br>
     *        or if slotNumber is null (slotNumber = null).
     */
    void unlockRegisterSlotNumberN(Integer slotNumber);


    /**
     * Replace all cards in the register,
     * except those in locked slots.
     *
     * @return A deck holding the removed cards.
     */
    IDeck removeAllUnlockedCardsFromTheRegister();


    /**
     * Get the number of unlocked register slots.
     *
     * @return The number of unlocked slots.
     */
    int numberOfUnlockedRegisterSlots();
}
