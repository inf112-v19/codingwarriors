package inf112.project.RoboRally.objects;

public class ConveyorBelt implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public ConveyorBelt(int speed, GridDirection direction) {
        this.speed=speed;
        this.direction=direction;
        this.damage=0;
        this.rotation=null;
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
    public Rotation getRotation() {
        return rotation;
    }
}
