package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public class Wall implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public Wall () {
        this.speed=0;
        this.direction=null;
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
    
    @Override
    public void doAction(IPlayer player) {
        // TODO No action needed here?
    }
}
