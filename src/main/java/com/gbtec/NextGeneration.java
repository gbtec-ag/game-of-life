package com.gbtec;

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
                Cell currentCell = currentRow.get(x);

                int neighbours = countNeighbours(generation, generationLength, y, currentRow, rowLength, x);

                // System.out.printf("Cell(%s, %s), Neighbours: %s%n", x, y, neighbours);

                // Implementing the rules
                if (!currentCell.getStatus() && neighbours == 3)
                    nextGeneration.getGeneration().get(y).get(x).setStatus(true);
                else if (currentCell.getStatus() && neighbours == 2 || neighbours == 3)
                    nextGeneration.getGeneration().get(y).get(x).setStatus(true);
                else {
                    nextGeneration.getGeneration().get(y).get(x).setStatus(false);
                }
            }
        }
        return nextGeneration;
    }

    static int countNeighbours(Generation generation, int generationLength, int y, List<Cell> currentRow, int rowLength, int x) {
        int neighbours = 0;
        // Checking cell right
        int lastXIndex = rowLength - 1;
        if (x < lastXIndex) {
            Cell neighbourCellRight = currentRow.get(x + 1);
            if (neighbourCellRight.getStatus()) {
                neighbours++;
            }
        }
        // Checking cell left
        Cell neighbourCellLeft;
        if (x >= 1) {
            neighbourCellLeft = currentRow.get(x - 1);
            if (neighbourCellLeft.getStatus()) {
                neighbours++;
            }
        }

        // Checking cell above currentCell
        if (y >= 1) {
            List<Cell> neighbourRowTop = generation.getGeneration().get(y - 1);
            Cell neighbourCellTop = neighbourRowTop.get(x);
            if (neighbourCellTop.getStatus()) {
                neighbours++;
            }
        }
        // Checking cell top right
        if (y >= 1) {
            if (x < rowLength - 1) {
                List<Cell> neighbourRowTop = generation.getGeneration().get(y - 1);
                Cell neighbourCellTopRight = neighbourRowTop.get(x + 1);
                if (neighbourCellTopRight.getStatus()) {
                    neighbours++;
                }
            }
        }
        // Checking cell top left
        if (y >= 1) {
            if (x >= 1) {
                List<Cell> neighbourRowTop = generation.getGeneration().get(y - 1);
                Cell neighbourCellTopLeft = neighbourRowTop.get(x - 1);
                if (neighbourCellTopLeft.getStatus()) {
                    neighbours++;
                }
            }
        }
        // Checking cell below
        if (y + 1 < generationLength) {
            List<Cell> neighbourRowBottom = generation.getGeneration().get(y + 1);
            Cell neighbourCellBottom = neighbourRowBottom.get(x);
            if (neighbourCellBottom.getStatus()) {
                neighbours++;
            }
        }
        // Checking cell bottom left
        if (y < generationLength - 1) {
            if (x >= 1) {
                List<Cell> neighbourRowBottom = generation.getGeneration().get(y + 1);
                Cell neighbourCellBottomLeft = neighbourRowBottom.get(x - 1);
                if (neighbourCellBottomLeft.getStatus()) {
                    neighbours++;
                }
            }
        }
        // Checking cell bottom right
        if (y + 1 < generationLength) {
            if (x < rowLength - 1) {
                List<Cell> neighbourRowBottom = generation.getGeneration().get(y + 1);
                Cell neighbourCellBottomRight = neighbourRowBottom.get(x + 1);
                if (neighbourCellBottomRight.getStatus()) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }
}
