package inf112.project.RoboRally.game;

public enum GameStatus {

    SELECT_CARDS,
    POWER_DOWN,
    EXECUTING_INSTRUCTIONS,
    EXECUTING_GAME_BOARD_OBJECTS,
    FIRING_LASERS,
 //   TOUCH_FLAGS_AND_REPAIR_SITES,?
    FINISHING_UP_THE_TURN,
    THE_END;

    /**
     * Checks if the given game status is valid.
     *
     * @param status
     *              The gamestate to check.
     * @return true if status matches an existing game state,<br>
     *     false otherwise.
     */
    public static boolean validStatus(GameStatus status) {
        boolean isValidStatus = false;
        for (GameStatus value : GameStatus.values()) {
            System.out.println("checked: " + value);
            if (value == status) {
                isValidStatus = true;
                System.out.println(value + " is a valid");
            }
        }
        return isValidStatus;
    }
}
