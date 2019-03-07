package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public class SingleWrench implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public SingleWrench () {
        this.speed=0;
        this.direction=GridDirection.NORTH;
        this.damage=0;
        this.rotation=null;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public GridDirection getDirection() {
        return null;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public Rotation getRotation() {
        return null;
    }

    @Override
    public void doAction(IPlayer player) {
        player.discardOneDamage();

        player.setThisPointAsNewBackup();
}

    @Override
    public String getTexture() {
        return "assets/singleWrench.png";
    }
}
