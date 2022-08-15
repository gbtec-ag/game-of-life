package com.gbtec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation2 {
    public List<List<Cell>> generation2 = new ArrayList<>();

    public Generation2(int size) {
        Random random = new Random();
        for (int numberOfRows = 0; numberOfRows <= size; numberOfRows++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int numberOfColumns = 0; numberOfColumns <= size; numberOfColumns++) {
                row.add(new Cell(random.nextBoolean()));
            }
            generation2.add(row);
        }
    }
}