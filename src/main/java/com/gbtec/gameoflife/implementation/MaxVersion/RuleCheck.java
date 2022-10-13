package com.gbtec.gameoflife.implementation.MaxVersion;

public class RuleCheck {

    public static int checkRules(int [][] grid, int y, int x, int sumNeighbors) {

        int[][] gridNewGen = new int[grid.length][grid.length];

                if (grid[y][x] == 1) {
                    if (sumNeighbors == 2 || sumNeighbors == 3) {
                        gridNewGen[y][x] = 1;
                    } else {
                        gridNewGen[y][x] = 0;
                    }
                } else {
                    if (sumNeighbors == 3) {
                        gridNewGen[y][x] = 1;
                    } else {
                        gridNewGen[y][x] = 0;
                    }
        }
                /* Idea how to compare values of two 2d array's
        int[][] ex = new int[grid.length][grid.length];

        for(int k = 0; k == y; k++) {
            for(int j = 0; j == x; j++) {
                if (gridNewGen[k][j] == 1) {
                    ex[k][j] = 1;
                } else {
                    ex[k][j] = 0;
                }
            }
        }
 */
        return gridNewGen[y][x];
    }
}
