package Objects;

public interface IObjects {

    /**
     * For use when objects have a speed
     * @return object speed
     */
    int getSpeed();

    /**
     * For use when objects have a direction
     * @return object direction
     */
    GridDirection getDirection();

    /**
     * For use when objects cause damage (use negative values for repairing)
     * @return damage
     */
    int getDamage();
}
