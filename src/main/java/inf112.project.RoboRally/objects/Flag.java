package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;

public class Flag implements IObjects {
    private static int numberOfFlags=0;
    
    private int flagNumber;
    private int speed;
    private GridDirection direction;
    private int damage;
    private ArrayList<GridDirection> walls;

    public Flag () {
        this.speed=0;
        this.direction=null;
        this.damage=0;
        this.flagNumber=numberOfFlags++;
        this.walls = new ArrayList<>();
    }
    
    @Override
    public boolean isWall(GridDirection direction) {
        for (GridDirection dir: walls) {
            if (dir == direction) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void buildWall(GridDirection direction) {
        for (GridDirection dir: walls) {
            if (dir == direction) {
                return;
            }
        }
        walls.add(direction);
    }
    
    @Override
    public void removeWall(GridDirection direction) {
        for (GridDirection dir: walls) {
            if (dir == direction) {
                walls.remove(direction);
            }
        }
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
        if (player.getFlagsVisited() == this.flagNumber) {
            player.addNewFlagVisited();
            player.setThisPointAsNewBackup();
        }
    }

    @Override
    public String getTexture() {
        if (flagNumber>9) {
            return "assets/flags/flag.png";
        }
        return "assets/flags/flag" + (flagNumber+1) + ".png";
    }

    public static int getNumberOfFlags() {
        return Flag.numberOfFlags;
    }
    
    int getFlagNumber() {
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
    
    @Override
    public String getWallTexture() {
        return GridDirection.getWallTexture(walls);
    }
    
    @Override
    public boolean hasWalls() {
        return !walls.isEmpty();
    }
    
}
