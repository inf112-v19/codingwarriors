package Objects;

public class Pit implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;

    public Pit (int damage) {
        this.speed=0;
        this.direction=GridDirection.NORTH;
        this.damage=damage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public GridDirection getDirection() {
        return direction;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
