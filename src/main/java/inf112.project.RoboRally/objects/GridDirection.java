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
   * A method used for sorting the directions in a given order, following this priority: North, South, East, West.
   * If the same GridDirection appears multiple times, only one of each will be retained.
   * @param directions The directions that are to be sorted
   * @return The sorted list of unique directions
   */
  private static ArrayList<GridDirection> sortUniqueDirectionsInPrioritizedOrder(ArrayList<GridDirection> directions) {
    ArrayList<GridDirection> sortedUniqueList = new ArrayList<>();
    ArrayList<GridDirection> allDirections = GridDirection.listOfDirections();
    for (GridDirection directionFromList: listOfDirections()) {
      for (GridDirection direction: directions) {
        if (direction==directionFromList) {
          sortedUniqueList.add(direction);
          break;
        }
      }
    }
    return sortedUniqueList;
  }
  
  /**
   * This method is used for making the reference to the wall texture object
   * @param walls The walls to make reference to
   * @return The wall texture reference
   */
  public static String getWallTexture(ArrayList<GridDirection> walls) {
    StringBuilder wallTexture= new StringBuilder();
    ArrayList<GridDirection> sortedDirectionList = GridDirection.sortUniqueDirectionsInPrioritizedOrder(walls);
    if (walls.size() > 0) {
      wallTexture = new StringBuilder("assets/walls/wall_");
      for (GridDirection direction: sortedDirectionList) {
        wallTexture.append(direction.toString());
      }
      wallTexture.append(".png");
    }
    return wallTexture.toString();
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

