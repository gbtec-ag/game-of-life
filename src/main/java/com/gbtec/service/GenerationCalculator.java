package com.gbtec.service;

import com.gbtec.model.Cell;
import com.gbtec.model.Generation;

import java.util.ArrayList;
import java.util.List;

public class GenerationCalculator {

    public static Generation calculateNextGeneration(Generation currentGeneration) {
        Generation nextGeneration = new Generation();

        int columnCount = currentGeneration.getCells().size();
        for (int y = 0; y < columnCount; y++) {
            List<Cell> column = currentGeneration.getCells().get(y);
            int rowCount = column.size();

            nextGeneration.getCells().add(new ArrayList<>(rowCount));

            for (int x = 0; x < rowCount; x++) {
                Cell cell = column.get(x);
                int neighbours = getNumberOfNeighbours(x, y, rowCount, columnCount, currentGeneration);

                if (neighbours == 3) {
                    nextGeneration.getCells().get(y).add(new Cell(true));
                } else if (neighbours == 2) {
                    nextGeneration.getCells().get(y).add(new Cell(cell.getAlive()));
                } else {
                    nextGeneration.getCells().get(y).add(new Cell(false));
                }
            }
        }

        return nextGeneration;
    }

    private static int getNumberOfNeighbours(int x, int y, int rowCount, int columnCount, Generation currentGeneration) {
        boolean isLeft = x == 0;
        boolean isTop = y == 0;
        boolean isRight = x == rowCount - 1;
        boolean isBottom = y == columnCount - 1;

        int neighbours = 0;

        if (!isTop && !isLeft && currentGeneration.getCells().get(y - 1).get(x - 1).getAlive()) {
            neighbours++;
        }
        if (!isTop && currentGeneration.getCells().get(y - 1).get(x).getAlive()) {
            neighbours++;
        }
        if (!isTop && !isRight && currentGeneration.getCells().get(y - 1).get(x + 1).getAlive()) {
            neighbours++;
        }
        if (!isLeft && currentGeneration.getCells().get(y).get(x - 1).getAlive()) {
            neighbours++;
        }
        if (!isRight && currentGeneration.getCells().get(y).get(x + 1).getAlive()) {
            neighbours++;
        }
        if (!isLeft && !isBottom && currentGeneration.getCells().get(y + 1).get(x - 1).getAlive()) {
            neighbours++;
        }
        if (!isBottom && currentGeneration.getCells().get(y + 1).get(x).getAlive()) {
            neighbours++;
        }
        if (!isBottom && !isRight && currentGeneration.getCells().get(y + 1).get(x + 1).getAlive()) {
            neighbours++;
        }

        return neighbours;
    }
}

