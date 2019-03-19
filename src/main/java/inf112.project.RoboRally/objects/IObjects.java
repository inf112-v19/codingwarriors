package inf112.project.RoboRally.objects;

import inf112.project.RoboRally.actors.IPlayer;

public interface IObjects {
    
    /**
     * For checking whether there is a wall on the given side of the object
     * @param direction: Which side to check for a wall
     * @return True if there is a wall, false otherwise
     */
    boolean isWall(GridDirection direction);
	
	/**
	 * For building a wall on the given side of the object
	 * @param direction: Which side to build wall on
	 */
	void buildWall(GridDirection direction);
	
	/**
	 * For removing a wall on the given side of the object
	 * @param direction: Which side of the object to remove the wall from
	 */
	void removeWall(GridDirection direction);
	
	/**
	 * For use when objects have a speed
     * @return object speed
     */
    int getSpeed();

    /**
     * For use when objects have a direction
     * @return object direction
     */
    GridDirection getDirection();

    /**
     * For use when objects cause damage (use negative values for repairing)
     * @return damage
     */
    int getDamage();

    /**
     * For use on objects rotating the character upon it
     * @return rotation
     */
    Rotation getRotation();
    
    /**
     * The object's specific action
     */
    void doAction(IPlayer player);

    /**
     * For use in drawing the object
     * @return Texture location and name in string form ex. assets/textureName.png
     */
    String getTexture();
	
	/**
	 * For use when the object has a wall
	 * @return Wall texture location and name in string form, e.g. assets/wallNorth.png
	 * The wall texture name is given in the form "wall_" and then the valid directions in the
	 * following prioritized order: North, south, east, west
	 */
	String getWallTexture();
}
