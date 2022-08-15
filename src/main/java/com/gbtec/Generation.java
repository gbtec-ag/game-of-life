package com.gbtec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

    public List<List<Cell>> generation = new ArrayList<>();

    public Generation(int size) {
        Random random = new Random();
        for (int numberOfRows = 0; numberOfRows <= size; numberOfRows++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int numberOfColumns = 0; numberOfColumns <= size; numberOfColumns++) {
                row.add(new Cell(random.nextBoolean()));
            }
            generation.add(row);
        }
    }
}
