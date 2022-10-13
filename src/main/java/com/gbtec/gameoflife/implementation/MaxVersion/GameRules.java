package com.gbtec.gameoflife.implementation.MaxVersion;

public class GameRules {
    public static int[][] createNewGen(int[][] grid /*, boolean toroidGrid */) {

        int[][] gridNewGen = new int[grid.length][grid.length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {

                int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, y, x);
                // int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, y, x);

                gridNewGen[y][x] = RuleCheck.checkRules(grid, y, x, sumNeighbors);

                /*
                if (boolean toroidGrid) {
                    int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, y, x);
                } else {
                    int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, y, x);
                }
                 */

            }
        }
        return gridNewGen;
    }
}
