package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;

public class RotationCog implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private ArrayList<GridDirection> walls;

    public RotationCog (Rotation rotation) {
        this.speed=0;
        this.direction=GridDirection.NORTH;
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
        if (rotation == Rotation.LEFT) {
            player.rotateLeft();
        } else if (rotation == Rotation.RIGHT) {
            player.rotateRight();
        } else if (rotation == Rotation.HALFWAY) {
            player.uTurn();
        }
    }

    @Override
    public String getTexture() {
        if (this.rotation == Rotation.LEFT) {
            return "assets/rotationCog_left.png";
        } else if (this.rotation == Rotation.RIGHT) {
            return "assets/rotationCog_right.png";
        }
        return null;
    }
}
