package inf112.project.RoboRally.objects;

public class Laser implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public Laser (GridDirection direction, int damage) {
        this.speed=0;
        this.direction=direction;
        this.damage=damage;
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
