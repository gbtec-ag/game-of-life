package com.gbtec.gameoflife.implementation.businessLogic;

public class GameRules {
    public static int[][] createNewGen(int[][] grid) {

        int[][] gridNewGen = new int[grid.length][grid.length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, y, x);
                gridNewGen[y][x] = RuleCheck.checkRules(grid[y][x], sumNeighbors);
            }
        }
        return gridNewGen;
    }
}
