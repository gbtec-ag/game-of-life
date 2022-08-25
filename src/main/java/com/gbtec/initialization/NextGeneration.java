package com.gbtec.initialization;

import com.gbtec.model.Cell;
import com.gbtec.model.Generation;

import java.util.List;

public class NextGeneration {

    public NextGeneration() {

    }

    int neighbours = 0;

    public Generation calculateNextGeneration(Generation generation, int size) {

        Generation nextGeneration = new Generation(size);
        int generationLength = generation.getGeneration().size(); // number of rows in a generation, in this example 3

        for (int y = 0; y < generationLength; y++) {

            List<Cell> currentRow = generation.getGeneration().get(y);/* row of PreGeneration */
            int rowLength = currentRow.size(); // length of row

            for (int x = 0; x < rowLength; x++) {
                // Cell of preGeneration
                Cell currentCell = generation.getCell(x, y);

                int neighbours = countNeighbours(generation, generationLength, y, rowLength, x);

                // System.out.printf("Cell(%s, %s), Neighbours: %s%n", x, y, neighbours);

                // Implementing the rules
                if (!currentCell.getStatusOfCell() && neighbours == 3)
                    nextGeneration.getGeneration().get(y).get(x).createCell();
                else if (currentCell.getStatusOfCell() && neighbours == 2 || neighbours == 3)
                    nextGeneration.getGeneration().get(y).get(x).createCell();
                else {
                    nextGeneration.getGeneration().get(y).get(x).killCell();
                }
            }
        }
        return nextGeneration;
    }

    public static int countNeighbours(Generation generation, int generationLength, int y, int rowLength, int x) {
        int neighbours = 0;
        // Checking cell right
        int lastXIndex = rowLength - 1;
        if (x < lastXIndex) {
            neighbours = neighbours + incrementNeighbourCount(generation.getCell(x + 1, y));
        }

        // Checking cell left
        if (x >= 1) {
            neighbours = incrementNeighbourCount(generation.getCell(x - 1, y));
        }

        // Checking cell above currentCell
        if (y >= 1) {
            neighbours = neighbours + incrementNeighbourCount(generation.getCell(x, y - 1));
        }
        // Checking cell top right
        if (y >= 1) {
            if (x < rowLength - 1) {
               neighbours = neighbours + incrementNeighbourCount(generation.getCell(x + 1, y - 1));
            }
        }
        // Checking cell top left
        if (y >= 1 &&x >= 1) {
               neighbours = neighbours + incrementNeighbourCount(generation.getCell(x - 1, y - 1));
        }
        // Checking cell below
        if (y + 1 < generationLength) {
            neighbours = neighbours + incrementNeighbourCount(generation.getCell(x, y + 1));
        }
        // Checking cell bottom left
        if (y < generationLength - 1 && x >= 1) {
                neighbours= neighbours + incrementNeighbourCount(generation.getCell(x - 1, y + 1));
        }
        // Checking cell bottom right
        if (y + 1 < generationLength && x < rowLength - 1) {
                neighbours = neighbours + incrementNeighbourCount(generation.getCell(x + 1, y + 1));
        }
        return neighbours;
    }

    public static int incrementNeighbourCount(Cell cell) {
        if (cell.getStatusOfCell()) {
            return 1;
        }
        return 0;
    }
}
