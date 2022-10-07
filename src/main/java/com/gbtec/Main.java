package com.gbtec;

import com.gbtec.MaxVersion.gameRules;
import static com.gbtec.MaxVersion.presentation.printTheGrid;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[][] grid = {{0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 1, 0, 1, 1, 0, 1, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 1, 0, 1, 1, 0, 1, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0}};

        for (int c = 0; c < 100; c++) {

            // if (c != 0) System.out.println();
            System.out.println();

            printTheGrid(grid);         // printing the grid
            System.out.println();
            grid = gameRules.checkRules(grid); // checking the rules for next generation
            if (c < 99) {
                Thread.sleep(10);
            }
        }

    }
}
