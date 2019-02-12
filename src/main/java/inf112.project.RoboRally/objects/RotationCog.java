package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.Player;

public class RotationCog implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public RotationCog (Rotation rotation) {
        this.speed=0;
        this.direction=GridDirection.NORTH;
        this.damage=0;
        this.rotation=rotation;
    }

    public void rotatePlayer(Player player) {
        System.out.println(rotation.toString());
        if (rotation == Rotation.LEFT) {
            player.rotateLeft();
        } else if (rotation == Rotation.RIGHT) {
            player.rotateRight();
        } else if (rotation == Rotation.HALFWAY) {
            player.uTurn();
        }
    }
    
    public GridDirection rotateRight() {
        switch(direction) {
            case EAST: return GridDirection.SOUTH;
            case SOUTH: return GridDirection.WEST;
            case WEST: return GridDirection.NORTH;
            case NORTH: return GridDirection.EAST;
            default: return null;
        }
    }

    public GridDirection rotateLeft() {
        switch(direction) {
            case EAST: return GridDirection.NORTH;
            case SOUTH: return GridDirection.EAST;
            case WEST: return GridDirection.SOUTH;
            case NORTH: return GridDirection.WEST;
            default: return null;
        }
    }

    public GridDirection rotateHalfway() {
        switch(direction) {
            case EAST: return GridDirection.WEST;
            case SOUTH: return GridDirection.NORTH;
            case WEST: return GridDirection.EAST;
            case NORTH: return GridDirection.SOUTH;
            default: return null;
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
}
