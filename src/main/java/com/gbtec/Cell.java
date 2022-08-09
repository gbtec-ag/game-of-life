package com.gbtec;

public class Cell {
    private boolean isAlive = false;
    int PositionX;
     int PositionY;

    public Cell(int posX, int posY) {
        posX = PositionX;
        posY = PositionY;
        if(isAlive){
            System.out.print("O");
        }
        else{
            System.out.print("X");
        }
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