package com.gbtec.model;

public class Cell {

    private boolean status;

    public Cell() {
    }

    public void createCell() {
        status=true;
    }

    public void killCell() {
        status = false;
    }
    public boolean getStatusOfCell(){
        return status;
    }
}
