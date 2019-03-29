package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;
import inf112.project.RoboRally.board.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class Laser implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;
    private int x,y;
    private ArrayList<GridDirection> walls;
    ArrayList<Coordinates> visitedPositionsByLaser;
    private Player player;
    private LaserTower tower;

    public Laser(GridDirection direction, int damage, Player player) {
        this.visitedPositionsByLaser = new ArrayList<>();
        this.speed=0;
        this.direction=direction;
        this.damage=damage;
        this.rotation=null;
        this.walls=new ArrayList<>();
        this.player = player;
    }

    public Laser(GridDirection direction, int damage, LaserTower tower) {
        this.visitedPositionsByLaser = new ArrayList<>();
        this.speed=0;
        this.direction=direction;
        this.damage=damage;
        this.rotation=null;
        this.walls=new ArrayList<>();
        this.tower = tower;
        this.x = tower.getCoordinates().getX();
        this.y = tower.getCoordinates().getY();
    }

    public Player getPlayer() {
        return player;
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

    public List<Coordinates> doAction(int boardRows, int boardColumns) {
        visitedPositionsByLaser = new ArrayList<>();

        while (insideBoard(getX(), getY(), boardRows, boardColumns)) {
            moveLaser();
            if (insideBoard(getX(), getY(), boardRows, boardColumns)) {
                visitedPositionsByLaser.add(new Coordinates(getX(), getY()));
            }
        }

        return visitedPositionsByLaser;
    }


    public void resetLaserPosition(Coordinates coordinates, GridDirection direction) {
        setX(coordinates.getX());
        setY(coordinates.getY());
        setDirection(direction);
    }

    public void moveLaser() {
        switch (direction) {
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
        return (x <= boardColumns && x >= 0 && y <= boardRows && y >= 0);
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

    public void setDirection(GridDirection direction) {
        this.direction = direction;
    }

    public LaserTower getTower() {
        return tower;
    }

    public boolean hasPlayer() {
        return player != null;
    }

    public List<Coordinates> getCoordinates() {
        return visitedPositionsByLaser;
    }
}
