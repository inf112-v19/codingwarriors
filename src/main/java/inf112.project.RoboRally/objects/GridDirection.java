package inf112.project.RoboRally.objects;

import java.util.SortedMap;

public enum GridDirection {
  EAST, WEST, NORTH, SOUTH;
  
  
  public  GridDirection rotateRight() {
    switch (this) {
      case NORTH: return EAST;
      case EAST: return SOUTH;
      case SOUTH: return WEST;
      case WEST: return NORTH;
      default: return null;
    }
  }
  
  public  GridDirection rotateLeft() {
    switch (this) {
      case NORTH: return WEST;
      case EAST: return NORTH;
      case SOUTH: return EAST;
      case WEST: return SOUTH;
      default: return null;
    }
  }
  
  public GridDirection invert() {
    switch (this) {
      case NORTH: return SOUTH;
      case EAST: return WEST;
      case SOUTH: return NORTH;
      case WEST: return EAST;
      default: return null;
    }
  }
}

