package com.gbtec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    public List<List<Cell>> generation = new ArrayList<>();

    public Generation() {
        for (int numberOfRows = 0; numberOfRows < 3; numberOfRows++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int numberOfColumns = 0; numberOfColumns < 3; numberOfColumns++) {
                row.add(new Cell(false));
            }
            generation.add(row);
        }
    }

    // List<List<>> gets filled with Cells
    public void initialize() {

        // create Blinker
        List<Cell> row2 = generation.get(1);
        row2.get(0).setStatus(true);
        row2.get(1).setStatus(true);
        row2.get(2).setStatus(true);

    }
}