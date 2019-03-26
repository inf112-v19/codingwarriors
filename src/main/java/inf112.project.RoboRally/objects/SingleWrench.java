package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.gui.Grid;

import java.util.ArrayList;

public class SingleWrench implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private ArrayList<GridDirection> walls;

    public SingleWrench () {
        this.speed=0;
        this.direction=GridDirection.NORTH;
        this.damage=0;
        this.rotation=null;
        this.walls=new ArrayList<>();
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
        return this.speed;
    }

    @Override
    public GridDirection getDirection() {
        return this.direction;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public Rotation getRotation() {
        return this.rotation;
    }

    @Override
    public void doAction(IPlayer player) {
        player.removeOneDamage();

        player.setThisPointAsNewBackup();
}

    @Override
    public String getTexture() {
        return "assets/singleWrench.png";
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
