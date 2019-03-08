package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

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
    
    @Override
    public void doAction(IPlayer player) {
        player.takeOneDamage();
    }

    @Override
    public String getTexture() {
        if (direction == GridDirection.SOUTH || direction == GridDirection.NORTH) {
            return "assets/laserVertical.png";
        }
        return "assets/laserHorizontal.png";
    }
}
