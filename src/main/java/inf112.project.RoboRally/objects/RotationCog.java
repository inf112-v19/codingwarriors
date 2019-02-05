package inf112.project.RoboRally.objects;

public class RotationCog implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private int rotation;

    public RotationCog (int rotation) {
        this.speed=0;
        this.direction=GridDirection.NORTH;
        this.damage=0;
        this.rotation=rotation;
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
