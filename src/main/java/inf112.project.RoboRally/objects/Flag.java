package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

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
    public void doAction(IPlayer player) {
        player.setThisPointAsNewBackup();
        if (player.getFlagsVisited() == this.flagNumber) {
            player.addNewFlagVisited();
            System.out.println("Found a flag!");
        }
    }

    @Override
    public String getTexture() {
        if (flagNumber>9) {
            return "assets/flag.png";
        }
        return "assets/flag" + (flagNumber+1) + ".png";
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
    
    /**
     * A method made for randomizing the order of the flags
     * @param flagNumber The assigned number for the flag
     */
    public void setFlagNumber(int flagNumber) {
        this.flagNumber=flagNumber;
    }
}
