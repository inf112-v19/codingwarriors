package inf112.project.RoboRally.objects;

import java.util.ArrayList;
import java.util.Random;

public enum GridDirection {
  EAST, WEST, NORTH, SOUTH;
  
  /**
   * The GridDirection obtained by rotating right
   * @return GridDirection obtained by rotating to the right
   */
  public GridDirection rotateRight() {
    switch (this) {
      case NORTH: return EAST;
      case EAST: return SOUTH;
      case SOUTH: return WEST;
      case WEST: return NORTH;
      default: return null;
    }
  }
  
  /**
   * The GridDirection obtained by rotating left
   * @return GridDirection obtained by rotating to the left
   */
  public GridDirection rotateLeft() {
    switch (this) {
      case NORTH: return WEST;
      case EAST: return NORTH;
      case SOUTH: return EAST;
      case WEST: return SOUTH;
      default: return null;
    }
  }
  
  /**
   * Finding the opposite GridDirection
   * @return The opposite GridDirection
   */
  public GridDirection invert() {
    switch (this) {
      case NORTH: return SOUTH;
      case EAST: return WEST;
      case SOUTH: return NORTH;
      case WEST: return EAST;
      default: return null;
    }
  }
  
  /**
   * For getting all the existing GridDirection
   * @return A list of all GridDirection
   */
  public static ArrayList<GridDirection> listOfDirections() {
    ArrayList<GridDirection> allDirections = new ArrayList<>();
    allDirections.add(GridDirection.NORTH);
    allDirections.add(GridDirection.SOUTH);
    allDirections.add(GridDirection.EAST);
    allDirections.add(GridDirection.WEST);
    return allDirections;
  }
  
  /**
   * For generating a random GridDirection
   * @return A random GridDirection
   */
  public static GridDirection randomDirection() {
    Random random = new Random();
    return listOfDirections().get(random.nextInt(listOfDirections().size()));
  }
  
  /**
   * A method for finding the GridDirections that are not present in the input list of GridDirection
   * @param directions The input list of GridDirection
   * @return A list of GridDirection that does not exist in the input list
   */
  public static ArrayList<GridDirection> getMissingGridDirections(ArrayList<GridDirection> directions) {
    ArrayList<GridDirection> missingDirections = listOfDirections();
    for (GridDirection direction: directions) {
      while (directions.contains(direction)) {
        directions.remove(direction);
      }
    }
    return directions;
  }
  
  /**
   * A method used for sorting the directions in a given order, following this priority: North, South, East, West.
   * If the same GridDirection appears multiple times, only one of each will be retained.
   * @param directions The directions that are to be sorted
   * @return The sorted list of unique directions
   */
  public static ArrayList<GridDirection> sortUniqueDirectionsInPrioritizedOrder(ArrayList<GridDirection> directions) {
    ArrayList<GridDirection> sortedUniqueList = new ArrayList<>();
    for (GridDirection direction: directions) {
      if (direction == GridDirection.NORTH) {
        directions.add(direction);
        break;
      }
    }
    
    for (GridDirection direction: directions) {
      if (direction == GridDirection.SOUTH) {
        directions.add(direction);
        break;
      }
    }
    
    for (GridDirection direction: directions) {
      if (direction == GridDirection.EAST) {
        directions.add(direction);
        break;
      }
    }
    
    for (GridDirection direction: directions) {
      if (direction == GridDirection.WEST) {
        directions.add(direction);
        break;
      }
    }
    return directions;
  }
  
  /**
   * This method is used for making the reference to the wall texture object
   * @param walls The walls to make reference to
   * @return The wall texture reference
   */
  public static String getWallTexture(ArrayList<GridDirection> walls) {
    String wallTexture=null;
    ArrayList<GridDirection> sortedDirectionList = GridDirection.sortUniqueDirectionsInPrioritizedOrder(walls);
    if (walls.size() > 0) {
      wallTexture="assets/walls/wall_";
      for (GridDirection direction: sortedDirectionList) {
        wallTexture += direction.toString();
      }
      wallTexture += ".png";
    }
    return wallTexture;
  }
  
  /**
   * For writing the name of the GridDirection
   * @return The GridDirection's name
   */
  public String toString() {
    String stringOutput="";
    switch(this) {
      case NORTH: stringOutput="North"; break;
      case SOUTH: stringOutput="South"; break;
      case EAST: stringOutput="East"; break;
      case WEST: stringOutput="West"; break;
    }
    return stringOutput;
  }
}

