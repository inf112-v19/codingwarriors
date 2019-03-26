package inf112.project.RoboRally.board;

import inf112.project.RoboRally.actors.IPlayer;
import inf112.project.RoboRally.objects.*;
import inf112.project.RoboRally.objects.IObjects;
import inf112.project.RoboRally.objects.Flag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private int rows;
    private int columns;
    private IObjects [][] board;

    public GameBoard(String level, String walls) {
        Flag.setNumberOfFlags(0); // resetting the static variable in Flag to be able to generate more than one gameboard
        int counter = extractDimensions(level);
        board = new IObjects[rows][columns];
        ArrayList<Integer> xCoordinates = new ArrayList<>();
        ArrayList<Integer> yCoordinates = new ArrayList<>();
        for (int x = rows -1; x >= 0; x--) {
            for (int y = 0; y < columns; y++) {
                board[x][y] = factory(level.charAt(counter));
                counter++;
                if (board[x][y] instanceof  Flag) {
                    xCoordinates.add(x);
                    yCoordinates.add(y);
                }
            }
        }
        for (int x=0; x<rows; x++) {
            for (int y=0; y<columns; y++) {
                buildWalls(walls.charAt(rows*columns-x*columns-y-1),x,columns-1-y);
            }
        }
        randomizeOrderOfFlags(xCoordinates, yCoordinates);
    }
    
    /**
     * A method where the flag numbers are randomized on the GameBoard. Without it, the flag numbers will be set in sequence.
     * @param xCoordinates The x-coordinates for each flag
     * @param yCoordinates The y-coordinates for each flag
     * Each index in xCoordinates corresponds with the same index in yCoordinates, so for each i there is a flag located at
     *                     (xCoordinates.get(i), yCoordinates.get(i))
     */
    private void randomizeOrderOfFlags(ArrayList<Integer> xCoordinates, ArrayList<Integer> yCoordinates) {
        if (xCoordinates.size() != yCoordinates.size()) {
            System.out.println("Something is wrong with the randomization of the flags' orders");
            return;
        }
        List<Integer> flagNumbers = new ArrayList<>();
        for (int i=0; i<Flag.getNumberOfFlags(); i++) {
            flagNumbers.add(i);
        }
        Random random = new Random();
        for (int j=0; j<xCoordinates.size(); j++) {
            int flagNumber=random.nextInt(flagNumbers.size());
            assert board[xCoordinates.get(j)][yCoordinates.get(j)] instanceof Flag;
            Flag flag = (Flag) board[xCoordinates.get(j)][yCoordinates.get(j)];
            flag.setFlagNumber(Integer.parseInt(""+flagNumbers.get(flagNumber)));
            flagNumbers.remove(flagNumber);
        }
    }
    
    private int extractDimensions(String level) {
        this.columns = Integer.parseInt(level.substring(0,level.indexOf("C")));
        this.rows = Integer.parseInt(level.substring(level.indexOf("C")+1,level.indexOf("R")));
        return level.indexOf("R")+1; // returns an integer containing the index of the start of board
    }

    private IObjects factory(char c) {
        switch (c) {
            case 'r':
                return new ConveyorBelt(1,GridDirection.EAST, Rotation.NONE);
            case 'u':
                return new ConveyorBelt(1,GridDirection.NORTH, Rotation.NONE);
            case 'd':
                return new ConveyorBelt(1,GridDirection.SOUTH, Rotation.NONE);
            case 'l':
                return new ConveyorBelt(1,GridDirection.WEST, Rotation.NONE);
            case 'f':
                return new Flag();
            case '.':
                return new Floor();
            case 'c':
                return new RotationCog(Rotation.LEFT);
            case 'C':
                return new RotationCog(Rotation.RIGHT);
            case 'w':
                return new SingleWrench();
            case 'p':
                return new Pit();
            case 'W':
                return new CrossedWrench();
            case '|':
                return new Laser(GridDirection.NORTH,1);
            case '-':
                return new Laser(GridDirection.EAST,1);
            default:
                return new Floor();
        }
    }
    
    private void buildWalls(char c, int x, int y) {
        IObjects object = getObject(y,x);
        switch (c) {
            default: break;
            case 'w':
                object.buildWall(GridDirection.WEST); break;
            case 'e':
                object.buildWall(GridDirection.EAST); break;
            case 'n':
                object.buildWall(GridDirection.NORTH); break;
            case 's':
                object.buildWall(GridDirection.SOUTH); break;
            case 'f':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.WEST);
                break;
            case 'g':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.EAST);
                break;
            case '|':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.SOUTH);
                break;
            case '-':
                object.buildWall(GridDirection.EAST);
                object.buildWall(GridDirection.WEST);
                break;
            case 'a':
                object.buildWall(GridDirection.SOUTH);
                object.buildWall(GridDirection.WEST);
                break;
            case 't':
                object.buildWall(GridDirection.SOUTH);
                object.buildWall(GridDirection.EAST);
                break;
            case 'u':
                object.buildWall(GridDirection.SOUTH);
                object.buildWall(GridDirection.EAST);
                object.buildWall(GridDirection.WEST);
                break;
            case 'd':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.EAST);
                object.buildWall(GridDirection.WEST);
                break;
            case 'r':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.SOUTH);
                object.buildWall(GridDirection.WEST);
            case 'l':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.SOUTH);
                object.buildWall(GridDirection.EAST);
                break;
            case 'o':
                object.buildWall(GridDirection.NORTH);
                object.buildWall(GridDirection.SOUTH);
                object.buildWall(GridDirection.EAST);
                object.buildWall(GridDirection.WEST);
                break;
        }
    }

    /**
     *
     * @return number of rows in this board
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return number of columns of this board
     */
    public int getColumns() {
        return columns;
    }

    /**
     * The content of the board at location x,y
     * @param row
     *             The x-coordinate of the object.
     * @param column
     *              The y-coordinate of the object.
     * @return the iObject at location x,y of the board
     */
    public IObjects getObject(int column, int row) {
        return board[row][column];
    }

    private boolean locationInsideBoard(int x, int y){
        return x >= 0 && x < columns && y >= 0 && y < rows;
    }

    public boolean moveValid(int x, int y) {
        return locationInsideBoard(x, y);
    }
    
    public void getObjectType(int x, int y) {
        IObjects object = getObject(x,y);
        System.out.println(object.getClass());
    }

}
