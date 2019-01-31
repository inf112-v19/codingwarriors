package Objects;

public class Laser implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;

    public Laser (GridDirection direction, int damage) {
        this.speed=0;
        this.direction=direction;
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
