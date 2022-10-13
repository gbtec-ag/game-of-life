package com.gbtec.gameoflife.implementation.MaxVersion;

public class SpecialCases {

    public static int countNeighborsForBlockedBorders(int[][] grid, int y, int x) {


        int a; int b; int c; int d; int e; int f; int g; int h;
        int sumNeighbors = 0;

                // Bc of (ArrayIndexOutOfBoundsException); Check outside grid -> impossible
                if (y == 0 && x == 0) {
                    // Case 1: a, b, c, g, h out of bound
                    d = grid[y + 1][x];
                    e = grid[y + 1][x + 1];
                    f = grid[y][x + 1];
                    sumNeighbors = d + e + f;
                } else if (y > 0 && y < grid.length - 1 && x == 0) {
                    // Case 2: a, b, c oob
                    d = grid[y + 1][x];
                    e = grid[y + 1][x + 1];
                    f = grid[y][x + 1];
                    g = grid[y - 1][x + 1];
                    h = grid[y - 1][x];
                    sumNeighbors = d + e + f + g + h;
                } else if (y == grid.length - 1 && x == 0) {
                    // Case 3: a, b, c, d, e oob
                    f = grid[y][x + 1];
                    g = grid[y - 1][x + 1];
                    h = grid[y - 1][x];
                    sumNeighbors = f + g + h;
                } else if (y == grid.length - 1 && x > 0 && x < grid.length - 1) {
                    // Case 4: c, d, e oob
                    a = grid[y - 1][x - 1];
                    b = grid[y][x - 1];
                    f = grid[y][x + 1];
                    g = grid[y - 1][x + 1];
                    h = grid[y - 1][x];
                    sumNeighbors = a + b + f + g + h;
                } else if (y == grid.length - 1 && x == grid.length - 1) {
                    // Case 5: c, d, e, f, g oob
                    a = grid[y - 1][x - 1];
                    b = grid[y][x - 1];
                    h = grid[y - 1][x];
                    sumNeighbors = a + b + h;
                } else if (y > 0 && y < grid.length - 1 && x == grid.length - 1) {
                    // Case 6: e, f, g oob
                    a = grid[y - 1][x - 1];
                    b = grid[y][x - 1];
                    c = grid[y + 1][x - 1];
                    d = grid[y + 1][x];
                    h = grid[y - 1][x];
                    sumNeighbors = a + b + c + d + h;
                } else if (y == 0 && x == grid.length - 1) {
                    // Case 7: a, e, f, g, h oob
                    b = grid[y][x - 1];
                    c = grid[y + 1][x - 1];
                    d = grid[y + 1][x];
                    sumNeighbors = b + c + d;
                } else if (y == 0 && x < grid.length - 1) {
                    // Case 8: a, g, h oob
                    b = grid[y][x - 1];
                    c = grid[y + 1][x - 1];
                    d = grid[y + 1][x];
                    e = grid[y + 1][x + 1];
                    f = grid[y][x + 1];
                    sumNeighbors = b + c + d + e + f;
                } else if (y > 0 && y < grid.length - 1 && x > 0 && x < grid.length - 1) {
                    a = grid[y - 1][x - 1];
                    b = grid[y][x - 1];
                    c = grid[y + 1][x - 1];
                    d = grid[y + 1][x];
                    e = grid[y + 1][x + 1];
                    f = grid[y][x + 1];
                    g = grid[y - 1][x + 1];
                    h = grid[y - 1][x];
                    sumNeighbors = a + b + c + d + e + f + g + h;
                }

        return sumNeighbors;
    }

    public static int countNeighborsForToroidGrid(int[][] grid, int y, int x){

        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int sumNeighbors;

                int xN = x - 1;
                int yN = y;
                if (xN < 0) xN = grid.length - 1;

                int xNE = x - 1;
                int yNE = y + 1;
                if (xNE < 0) xNE = grid.length - 1;
                if (yNE > grid.length - 1) yNE = 0;

                int xE = x;
                int yE = y + 1;
                if (yE > grid.length - 1) yE = 0;

                int xSE = x + 1;
                int ySE = y + 1;
                if (xSE > grid.length - 1) xSE = 0;
                if (ySE > grid.length - 1) ySE = 0;

                int xS = x + 1;
                int yS = y;
                if (xS > grid.length - 1) xS = 0;

                int xSW = x + 1;
                int ySW = y - 1;
                if (xSW > grid.length - 1) xSW = 0;
                if (ySW < 0) ySW = grid.length - 1;

                int xW = x;
                int yW = y - 1;
                if (yW < 0) yW = grid.length - 1;

                int xNW = x - 1;
                int yNW = y - 1;
                if (xNW < 0) xNW = grid.length - 1;
                if (yNW < 0) yNW = grid.length - 1;

                a = grid[yN][xN];
                b = grid[yNE][xNE];
                c = grid[yE][xE];
                d = grid[ySE][xSE];
                e = grid[yS][xS];
                f = grid[ySW][xSW];
                g = grid[yW][xW];
                h = grid[yNW][xNW];

                sumNeighbors = a + b + c + d + e + f + g + h;

        return sumNeighbors;
    }
}
