package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;

public class Flag implements IObjects {
    private static int numberOfFlags=0;
    
    private int flagNumber;
    private int speed;
    private GridDirection direction;
    private int damage;

    public Flag () {
        this.speed=0;
        this.direction=null;
        this.damage=0;
        this.flagNumber=numberOfFlags++;
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
        return null;
    }
    
    @Override
    public void doAction(Player player) {
        player.setThisPointAsNewBackup();
        if (player.getFlagsVisited() == this.flagNumber) {
            player.addNewFlagVisited();
        }
    }
    
    public static int getNumberOfFlags() {
        return Flag.numberOfFlags;
    }
    
    public int getFlagNumber() {
        return flagNumber;
    }

    public static void setNumberOfFlags(int numberOfFlags) {
        Flag.numberOfFlags = numberOfFlags;
    }
}
