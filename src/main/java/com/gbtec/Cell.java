package com.gbtec;

public class Cell {

    private boolean isAlive;

    public Cell() {
        isAlive = false;
    }

    public boolean getStatus() {
        return isAlive;
    }

    public void setStatus(boolean status) {
        isAlive = status;
    }
}
