package com.gbtec;
//
public class Cell {

    boolean isAlive;

    public Cell(boolean alive) {
        isAlive = alive;
    }

    public boolean getStatus() {
        return isAlive;
    }

    public void setStatus(boolean status) {
        isAlive = status;
    }
}
