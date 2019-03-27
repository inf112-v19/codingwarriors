package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Coordinates;
import inf112.project.RoboRally.actors.IPlayer;

public class LaserTower implements IObjects {
    private GridDirection direction;
    private Coordinates coordinates;
    private Laser laser;

    public LaserTower(Coordinates coordinates, GridDirection direction) {
        this.coordinates = coordinates;
        this.direction = direction;
        laser = new Laser(direction, 1, this);
    }


    @Override
    public boolean isWall(GridDirection direction) {
        return false;
    }

    @Override
    public boolean hasWalls() {
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
        return 0;
    }

    @Override
    public GridDirection getDirection() {
        return null;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public Rotation getRotation() {
        return null;
    }

    @Override
    public void doAction(IPlayer player) {

    }

    @Override
    public String getTexture() {
        return null;
    }

    @Override
    public String getWallTexture() {
        return null;
    }

    public Laser getLaser() {
        return laser;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
