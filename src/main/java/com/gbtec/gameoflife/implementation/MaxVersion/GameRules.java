package com.gbtec.gameoflife.implementation.MaxVersion;

public class GameRules {
    public static int[][] checkRules(int[][] grid) {

        int[][] gridNewGen = new int[grid.length][grid.length];

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {

                int sumNeighbors = SpecialCases.specialCaseCheck(gridNewGen, x, y);

                if (grid[x][y] == 1) {
                    if (sumNeighbors == 2 || sumNeighbors == 3) {
                        gridNewGen[x][y] = 1;
                    } else {
                        gridNewGen[x][y] = 0;
                    }
                } else {
                    if (sumNeighbors == 3) {
                        gridNewGen[x][y] = 1;
                    } else {
                        gridNewGen[x][y] = 0;
                    }
                }
            }
        }
        return gridNewGen;
    }

    public static int testAddition(int x, int y) {
        return x+y;
    }
}
