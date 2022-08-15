package com.gbtec;

import java.util.List;

public class NextGeneration2 extends Generation {

    public NextGeneration2() {
    }

    public List<List<Cell>> calculateNextGeneration(List<List<Cell>> generation, int size) {

        Generation2 nextGeneration = new Generation2(size);
        int generationLength = generation.size(); // number of rows in a generation, in this example 3

        for (int y = 0; y < generationLength; y++) {

            List<Cell> currentRow = generation.get(y);/* row of PreGeneration */
            int rowLength = currentRow.size(); // length of row

            for (int x = 0; x < rowLength; x++) {
                // Cell of preGeneration
                Cell currentCell = currentRow.get(x);

                int neighbours = countNeighbours(generation, generationLength, y, currentRow, rowLength, x);

                // System.out.printf("Cell(%s, %s), Neighbours: %s%n", x, y, neighbours);

                // Implementing the rules
                if (!currentCell.isAlive && neighbours == 3)
                    nextGeneration.generation2.get(y).get(x).setStatus(true);
                else if (currentCell.isAlive && neighbours == 2 || neighbours == 3)
                    nextGeneration.generation2.get(y).get(x).setStatus(true);
                else {
                    nextGeneration.generation2.get(y).get(x).setStatus(false);
                }
            }
        }
        return nextGeneration.generation2;
    }

    /*
     * private int countNeighboursWithLoops(List<List<Cell>> generation, int x, int y) {
     * int neighbours = 0;
     * for (int neighboursX = x - 1; neighboursX < x + 1; neighboursX++) {
     * for (int neighboursY = y - 1; neighboursY < y + 1; neighboursY++) {
     * // if out of bounds and not self
     * // isAlive --> neighbours++;
     * }
     * }
     * return neighbours;
     * }
     */
    private static int countNeighbours(List<List<Cell>> generation, int generationLength, int y, List<Cell> currentRow,
        int rowLength, int x) {
        int neighbours = 0;
        // Checking cell right
        int lastXIndex = rowLength - 1;
        if (x < lastXIndex) {
            Cell neighbourCellRight = currentRow.get(x + 1);
            if (neighbourCellRight.isAlive) {
                neighbours++;
            }
        }
        // Checking cell left
        Cell neighbourCellLeft;
        if (x >= 1) {
            neighbourCellLeft = currentRow.get(x - 1);
            if (neighbourCellLeft.isAlive) {
                neighbours++;
            }
        }

        // Checking cell above currentCell
        if (y >= 1) {
            List<Cell> neighbourRowTop = generation.get(y - 1);
            Cell neighbourCellTop = neighbourRowTop.get(x);
            if (neighbourCellTop.isAlive) {
                neighbours++;
            }
        }
        // Checking cell top right
        if (y >= 1) {
            if (x < rowLength - 1) {
                List<Cell> neighbourRowTop = generation.get(y - 1);
                Cell neighbourCellTopRight = neighbourRowTop.get(x + 1);
                if (neighbourCellTopRight.isAlive) {
                    neighbours++;
                }
            }
        }
        // Checking cell top left
        if (y >= 1) {
            if (x >= 1) {
                List<Cell> neighbourRowTop = generation.get(y - 1);
                Cell neighbourCellTopLeft = neighbourRowTop.get(x - 1);
                if (neighbourCellTopLeft.isAlive) {
                    neighbours++;
                }
            }
        }
        // Checking cell below
        if (y + 1 < generationLength) {
            List<Cell> neighbourRowBottom = generation.get(y + 1);
            Cell neighbourCellBottom = neighbourRowBottom.get(x);
            if (neighbourCellBottom.isAlive) {
                neighbours++;
            }
        }
        // Checking cell bottom left
        if (y < generationLength - 1) {
            if (x >= 1) {
                List<Cell> neighbourRowBottom = generation.get(y + 1);
                Cell neighbourCellBottomLeft = neighbourRowBottom.get(x - 1);
                if (neighbourCellBottomLeft.isAlive) {
                    neighbours++;
                }
            }
        }
        // Checking cell bottom right
        if (y + 1 < generationLength) {
            if (x < rowLength - 1) {
                List<Cell> neighbourRowBottom = generation.get(y + 1);
                Cell neighbourCellBottomRight = neighbourRowBottom.get(x + 1);
                if (neighbourCellBottomRight.isAlive) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }
}
