package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public class Laser implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private int x,y;

    public Laser (GridDirection direction, int damage) {
        this.speed=0;
        this.direction=direction;
        this.damage=damage;
        this.rotation=null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
        switch (player.getPlayerDirection()) {
            case NORTH:
                y = y + 1;
                break;
            case WEST:
                x = x - 1;
                break;
            case EAST:
                x = x + 1;
                break;
            case SOUTH:
                y = y - 1;
                break;
        }
    }



    @Override
    public String getTexture() {
        if (direction == GridDirection.SOUTH || direction == GridDirection.NORTH) {
            return "assets/laserVertical.png";
        }
        return "assets/laserHorizontal.png";
    }
}
