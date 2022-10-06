package com.gbtec.MaxVersion;

public class presentation {
    static int i;
    static int j;
    static int[][] grid;

    public static void printTheGrid(){

        int[][] grid = {{0, 0, 1, 1}, {0, 1, 0, 0}, {1, 0, 0, 1}, {1, 1, 0, 0}};

        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + "  ");
            } System.out.println();
        }



    }

    public static void getGridStatus() {

    }
}
