package Objects;

public class Flag implements IObjects {

    private int speed;
    private GridDirection direction;
    private int damage;

    public Flag () {
        this.speed=0;
        this.direction=null;
        this.damage=0;
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
