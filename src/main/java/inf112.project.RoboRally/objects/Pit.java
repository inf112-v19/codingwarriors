package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public class Pit implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public Pit (int damage) {
        this.speed=0;
        this.direction=GridDirection.NORTH;
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
    
    @Override
    public void doAction(IPlayer player) {
        player.respawnAtLastArchiveMarker();
    }
}
