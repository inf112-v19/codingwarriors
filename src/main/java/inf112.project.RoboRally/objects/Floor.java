package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public class Floor implements IObjects {

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
        // No action needed here for this specific object ;)
    }
}
