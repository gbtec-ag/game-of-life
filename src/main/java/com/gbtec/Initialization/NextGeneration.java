package com.gbtec.Initialization;

import java.util.List;

public class NextGeneration {

    public NextGeneration() {

    }

    public Generation calculateNextGeneration(Generation generation, int size) {

        Generation nextGeneration = new Generation(size);
        int generationLength = generation.getGeneration().size(); // number of rows in a generation, in this example 3

        for (int y = 0; y < generationLength; y++) {

            List<Cell> currentRow = generation.getGeneration().get(y);/* row of PreGeneration */
            int rowLength = currentRow.size(); // length of row

            for (int x = 0; x < rowLength; x++) {
                // Cell of preGeneration
                Cell currentCell = generation.getCell(x,y);

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
            Cell neighbourCellRight =generation.getCell(x + 1,y);
            if (neighbourCellRight.getStatusOfCell()) {
                neighbours++;
            }
        }
        // Checking cell left

        if (x >= 1) {
            Cell neighbourCellLeft = generation.getCell(x - 1,y);
            if (neighbourCellLeft.getStatusOfCell()) {
                neighbours++;
            }
        }

        // Checking cell above currentCell
        if (y >= 1) {
            Cell neighbourCellTop = generation.getCell(x,y-1);
            if (neighbourCellTop.getStatusOfCell()) {
                neighbours++;
            }
        }
        // Checking cell top right
        if (y >= 1) {
            if (x < rowLength - 1) {
                Cell neighbourCellTopRight = generation.getCell(x+1,y-1);
                if (neighbourCellTopRight.getStatusOfCell()) {
                    neighbours++;
                }
            }
        }
        // Checking cell top left
        if (y >= 1) {
            if (x >= 1) {
                Cell neighbourCellTopLeft = generation.getCell(x-1,y-1);
                if (neighbourCellTopLeft.getStatusOfCell()) {
                    neighbours++;
                }
            }
        }
        // Checking cell below
        if (y + 1 < generationLength) {
            Cell neighbourCellBottom = generation.getCell(x,y+1);
            if (neighbourCellBottom.getStatusOfCell()) {
                neighbours++;
            }
        }
        // Checking cell bottom left
        if (y < generationLength - 1) {
            if (x >= 1) {
                Cell neighbourCellBottomLeft = generation.getCell(x-1,y+1);
                if (neighbourCellBottomLeft.getStatusOfCell()) {
                    neighbours++;
                }
            }
        }
        // Checking cell bottom right
        if (y + 1 < generationLength) {
            if (x < rowLength - 1) {
                Cell neighbourCellBottomRight = generation.getCell(x+1,y+1);
                if (neighbourCellBottomRight.getStatusOfCell()) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }
}
