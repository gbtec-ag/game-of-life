package com.gbtec;

import java.util.List;

public class NextGeneration extends Generation {

    public NextGeneration() {
    }

    public List<List<Cell>> calculateNextGeneration(List<List<Cell>> generation) {

        Generation nextGeneration = new Generation();

        int generationLength = generation.size(); // number of rows in a generation, in this example 3
        for (int y = 0; y < generationLength - 1; y++) {

            /* row of PreGeneration */
            List<Cell> currentRow = generation.get(y); // for Checking which NeighborCells are neighbours
            int neighbours; // number of neighbour Cells
            int rowLength = currentRow.size(); // length of row

            for (int x = 0; x < rowLength - 1; x++) {
                neighbours = 0;
                // Cell of preGeneration

                Cell currentCell = currentRow.get(x);

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
                if (y <= generationLength - 1) {
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
                if (y <= generationLength - 1) {
                    if (x < rowLength - 1) {
                        List<Cell> neighbourRowBottom = generation.get(y + 1);
                        Cell neighbourCellBottomRight = neighbourRowBottom.get(x + 1);
                        if (neighbourCellBottomRight.isAlive) {
                            neighbours++;
                        }
                    }
                }

                // Implementing the rules
                if (currentCell.isAlive && neighbours < 2) {
                    nextGeneration.generation.get(y).get(x).setStatus(false);

                } else if (currentCell.isAlive && neighbours >= 4) {
                    nextGeneration.generation.get(y).get(x).setStatus(false);

                } else if (!currentCell.isAlive && neighbours == 3) {
                    nextGeneration.generation.get(y).get(x).setStatus(true);
                } else if (currentCell.isAlive && neighbours==2 || neighbours==3) {
                    nextGeneration.generation.get(y).get(x).setStatus(true);
                } else {
                    nextGeneration.generation.get(y).get(x).setStatus(false);


                }
            }
        }
        return nextGeneration.generation;
    }
}
