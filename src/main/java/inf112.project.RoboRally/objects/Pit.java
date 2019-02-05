package inf112.project.RoboRally.objects;

public class Pit implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private int rotation;

    public Pit (int damage) {
        this.speed=0;
        this.direction=GridDirection.NORTH;
        this.damage=damage;
        this.rotation=0;
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

    @Override
    public int getRotation() {
        return rotation;
    }
}
