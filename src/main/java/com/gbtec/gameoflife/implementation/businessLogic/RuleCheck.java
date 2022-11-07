package com.gbtec.gameoflife.implementation.businessLogic;

public class RuleCheck {

    public static int checkRules(int currentCell, int sumNeighbors) {
        if (currentCell == 1 && (sumNeighbors < 2 || sumNeighbors > 3)) {
            // live cell dies due to underpopulation or overpopulation
            return 0;
        } else if (currentCell == 0 && sumNeighbors == 3) {
            // dead cell is reborn
            return 1;
        }
        return currentCell;
    }

    public static int countNeighborsForToroidGrid(int[][] grid, int y, int x) {
        int l = grid.length;
        return grid[wrap(y - 1, l)][wrap(x - 1, l)] +
                grid[wrap(y - 1, l)][x] +
                grid[wrap(y - 1, l)][wrap(x + 1, l)] +
                grid[y][wrap(x - 1, l)] +
                grid[y][wrap(x + 1, l)] +
                grid[wrap(y + 1, l)][wrap(x - 1, l)] +
                grid[wrap(y + 1, l)][x] +
                grid[wrap(y + 1, l)][wrap(x + 1, l)];
    }

    private static int wrap(int position, int length) {
        return (position + length) % length;
    }
}
