package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class Laser implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private int x,y;
    private ArrayList<GridDirection> walls;

    public Laser (GridDirection direction, int damage) {
        this.speed=0;
        this.direction=direction;
        this.damage=damage;
        this.rotation=null;
        this.walls=new ArrayList<>();
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


    }

    public List<Coordinates> doAction(IPlayer player, int boardRows, int boardColumns) {
        ArrayList<Coordinates> visitedPositionsByLaser = new ArrayList<>();
        setX(player.getX());
        setY(player.getY());

        while (insideBoard(getX(), getY(), boardRows, boardColumns)) {
            moveLaser(player);
            if (insideBoard(getX(), getY(), boardRows, boardColumns)) {
                visitedPositionsByLaser.add(new Coordinates(getX(), getY()));
            }
        }

        return visitedPositionsByLaser;
    }


    public void moveLaser(IPlayer player) {
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

    public boolean insideBoard(int x, int y, int boardRows, int boardColumns) {
        return (x < boardColumns && x >= 0 && y < boardRows && y >= 0);
    }


    @Override
    public String getTexture() {
        if (direction == GridDirection.SOUTH || direction == GridDirection.NORTH) {
            return "assets/laserVertical.png";
        }
        return "assets/laserHorizontal.png";
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
