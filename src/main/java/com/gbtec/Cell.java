package com.gbtec;

public class Cell {

    boolean isAlive;
    int positionX;
    int positionY;

    public Cell(int posX, int posY, boolean alive) {
        positionX = posX;
        positionY = posY;
        isAlive = alive;
    }

    public int getCoordinateY() {
        return positionY;
    }

    public int getCoordinateX() {
        return positionX;
    }

    public boolean getStatus() {
        return isAlive;
    }
}
