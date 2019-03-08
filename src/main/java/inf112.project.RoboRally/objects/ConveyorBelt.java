package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.actors.Player;

public class ConveyorBelt implements IObjects {
    private int speed;
    private GridDirection direction;
    private int damage;
    private Rotation rotation;

    public ConveyorBelt(int speed, GridDirection direction) {
        this.speed=speed;
        this.direction=direction;
        this.damage=0;
        this.rotation=null;
    }
    
    public ConveyorBelt(int speed, GridDirection direction, Rotation rotation) {
    	this.speed=speed;
    	this.direction=direction;
    	this.damage=0;
    	this.rotation=rotation;
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
            return "assets/conveyorBelt_east.png";
        } else if (this.direction == GridDirection.WEST) {
            return "assets/conveyorBelt_west.png";
        } else if (this.direction == GridDirection.NORTH) {
            return  "assets/conveyorBelt_north.png";
        } else if (this.direction == GridDirection.SOUTH) {
            return "assets/conveyorBelt_south.png";
        }
        return null;
    }

}
