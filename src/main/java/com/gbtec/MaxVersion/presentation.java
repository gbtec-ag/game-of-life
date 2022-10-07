package com.gbtec.MaxVersion;

public class presentation {

    public static void printTheGrid(int[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            System.out.print("");
            for (int y = 0; y < grid.length; y++) {
                String i = (grid[x][y] == 1) ? " © " : "   ";
                System.out.print(i + "");
            }
            System.out.println();
        }
    }
}
