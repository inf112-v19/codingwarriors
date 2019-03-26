package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;

public class ConveyorBelt implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private ArrayList<GridDirection> walls;

    public ConveyorBelt(int speed, GridDirection direction, Rotation rotation) {
    	this.speed=speed;
    	this.direction=direction;
    	this.damage=0;
    	this.rotation=rotation;
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
    public boolean hasWalls() {
        return !walls.isEmpty();
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
        return rotation;
    }
    
    @Override
    public void doAction(IPlayer player) {
        for (int i=0; i<speed; i++) {
            player.movePlayer(direction);
        }
    }

    @Override
    public String getTexture() {
        if (this.direction == GridDirection.EAST) {
            return "assets/conveyorbelts/conveyorBelt_east" + getSpeed() +".png";
        } else if (this.direction == GridDirection.WEST) {
            return "assets/conveyorbelts/conveyorBelt_west" + getSpeed() + ".png";
        } else if (this.direction == GridDirection.NORTH) {
            return  "assets/conveyorbelts/conveyorBelt_north" + getSpeed() + ".png";
        } else if (this.direction == GridDirection.SOUTH) {
            return "assets/conveyorbelts/conveyorBelt_south" + getSpeed() + ".png";
        }
        return null;
    }
    
    @Override
    public String getWallTexture() {
        return GridDirection.getWallTexture(walls);
    }
    
}
