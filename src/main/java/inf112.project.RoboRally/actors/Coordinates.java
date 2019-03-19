package inf112.project.RoboRally.actors;

import inf112.project.RoboRally.board.GameBoard;
import inf112.project.RoboRally.cards.*;
import inf112.project.RoboRally.game.Game;
import inf112.project.RoboRally.objects.Flag;
import inf112.project.RoboRally.objects.GridDirection;
import inf112.project.RoboRally.objects.Laser;

import java.util.ArrayList;
import java.util.List;

class Coordinates {
    private int x,y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates otherCord = (Coordinates) obj;
        if (otherCord.x == x && otherCord.y == y)
            return true;
        return false;
    }
}
