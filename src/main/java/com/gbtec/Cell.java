package com.gbtec;

public class Cell {
    private boolean isAlive = false;
    int PositionX;
     int PositionY;
    String Name = "";

    public Cell(String bezeichnung, int posX, int posY) {
        bezeichnung = Name;
        posX = PositionX;
        posY = PositionY;
    }


    public boolean getStatus(int posx,int posy) {
        return isAlive;
    }

    public void changeStatus(int posx,int posy) {
        if (!isAlive) {
            isAlive = true;
        } else {
            isAlive = false;
        }

    }
}