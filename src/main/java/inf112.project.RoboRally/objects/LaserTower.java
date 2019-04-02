package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;

import java.util.ArrayList;

public class LaserTower implements IObjects {
    private GridDirection direction;
    private Coordinates coordinates;
    private Laser laser;
    private int speed;
    private ArrayList<GridDirection> walls;
    private int damage;
    private Rotation rotation;
    
    public LaserTower(Coordinates coordinates, GridDirection direction) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.damage = 1;
        laser = new Laser(damage, this);
        this.speed = 0;
        this.walls = new ArrayList<>();
        this.rotation = Rotation.NONE;
    }


    @Override
    public boolean isWall(GridDirection direction) {
        return walls.contains(direction);
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

    }

    @Override
    public String getTexture() {
        return "assets/lasertowers/laserTower_" + direction.toString().toLowerCase() + "wards.png";
    }

    @Override
    public String getWallTexture() {
        return GridDirection.getWallTexture(walls);
    }

    public Laser getLaser() {
        return laser;
    }

    Coordinates getCoordinates() {
        return coordinates;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
