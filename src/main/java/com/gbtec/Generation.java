package com.gbtec;

import java.util.ArrayList;
import java.util.List;

public class Generation {

    private List<List<Cell>> generation = new ArrayList<>();

    public Generation(int size) {
        for (int numberOfRows = 0; numberOfRows <= size - 1; numberOfRows++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int numberOfColumns = 0; numberOfColumns <= size - 1; numberOfColumns++) {
                row.add(new Cell());
            }
            generation.add(row);
        }
    }

    public List<List<Cell>> getGeneration() {
        return generation;
    }
}
