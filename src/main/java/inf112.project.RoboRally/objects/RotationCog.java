package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public class RotationCog implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public RotationCog (Rotation rotation) {
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
    public Rotation getRotation() {
        return rotation;
    }
    
    @Override
    public void doAction(IPlayer player) {
        if (rotation == Rotation.LEFT) {
            player.rotateLeft();
        } else if (rotation == Rotation.RIGHT) {
            player.rotateRight();
        } else if (rotation == Rotation.HALFWAY) {
            player.uTurn();
        }
    }
}
