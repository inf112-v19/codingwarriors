package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;

public class Wall implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private ArrayList<GridDirection> walls;

    public Wall () {
        this.speed=0;
        this.direction=null;
        this.damage=0;
        this.rotation=null;
        this.walls=new ArrayList<>();
    }
    
    @Override
    public boolean isWall(GridDirection direction) {
        return false;
    }
    
    @Override
    public void buildWall(GridDirection direction) {
    
    }
    
    @Override
    public void removeWall(GridDirection direction) {
    
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

    @Override
    public String getTexture() {
        return null;
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
