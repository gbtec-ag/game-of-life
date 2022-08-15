package com.gbtec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    public List<List<Cell>> generation = new ArrayList<>();
    Random random = new Random();

    public Generation() {
        for (int numberOfRows = 0; numberOfRows <= 2; numberOfRows++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int numberOfColumns = 0; numberOfColumns <= 2; numberOfColumns++) {
                row.add(new Cell(random.nextBoolean()));
            }
            generation.add(row);
        }
    }

    // List<List<>> gets filled with Cells
  /*  public void initialize() {
        List<Cell> row1 = generation.get(0);
        row1.get(0).setStatus(false);
        row1.get(1).setStatus(true);
        row1.get(2).setStatus(false);

        List<Cell> row2 = generation.get(1);
        row2.get(0).setStatus(false);
        row2.get(1).setStatus(true);
        row2.get(2).setStatus(false);

        List<Cell> row3 = generation.get(2);
        row3.get(0).setStatus(false);
        row3.get(1).setStatus(true);
        row3.get(2).setStatus(false);*/
    }