package com.gbtec.gameoflife.implementation.MaxVersion;

public class GameRules {
    public static int[][] checkRules(int[][] grid) {

        int[][] gridNewGen = new int[grid.length][grid.length];

        int a; int b; int c; int d; int e; int f; int g; int h;
        int sumNeighbors = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                // Bc of (ArrayIndexOutOfBoundsException); Check outside grid -> impossible
                if (x == 0 && y == 0) {
                    // Case 1: a, b, c, g, h out of bound
                    d = grid[x+1][y];
                    e = grid[x+1][y+1];
                    f = grid[x][y+1];
                    sumNeighbors = d + e + f;
                } else if (x > 0 && x < grid.length - 1 && y == 0) {
                    // Case 2: a, b, c oob
                    d = grid[x+1][y];
                    e = grid[x+1][y+1];
                    f = grid[x][y+1];
                    g = grid[x-1][y+1];
                    h = grid[x-1][y];
                    sumNeighbors = d + e + f + g + h;
                } else if (x == grid.length - 1 && y == 0) {
                    // Case 3: a, b, c, d, e oob
                    f = grid[x][y+1];
                    g = grid[x-1][y+1];
                    h = grid[x-1][y];
                    sumNeighbors = f + g + h;
                } else if (x == grid.length - 1 && y > 0 && y < grid.length - 1) {
                    // Case 4: c, d, e oob
                    a = grid[x-1][y-1];
                    b = grid[x][y-1];
                    f = grid[x][y+1];
                    g = grid[x-1][y+1];
                    h = grid[x-1][y];
                    sumNeighbors = a + b + f + g + h;
                } else if (x == grid.length - 1 && y == grid.length - 1) {
                    // Case 5: c, d, e, f, g oob
                    a = grid[x-1][y-1];
                    b = grid[x][y-1];
                    h = grid[x-1][y];
                    sumNeighbors = a + b + h;
                } else if (x > 0 && x < grid.length - 1 && y == grid.length -1) {
                    // Case 6: e, f, g oob
                    a = grid[x-1][y-1];
                    b = grid[x][y-1];
                    c = grid[x+1][y-1];
                    d = grid[x+1][y];
                    h = grid[x-1][y];
                    sumNeighbors = a + b + c + d + h;
                } else if (x == 0 && y == grid.length - 1) {
                    // Case 7: a, e, f, g, h oob
                    b = grid[x][y-1];
                    c = grid[x+1][y-1];
                    d = grid[x+1][y];
                    sumNeighbors = b + c + d;
                } else if (x == 0 && y < grid.length - 1) {
                    // Case 8: a, g, h oob
                    b = grid[x][y-1];
                    c = grid[x+1][y-1];
                    d = grid[x+1][y];
                    e = grid[x+1][y+1];
                    f = grid[x][y+1];
                    sumNeighbors = b + c + d + e + f;
                } else if (x > 0 && x < grid.length - 1 && y > 0 && y < grid.length - 1) {
                    a = grid[x-1][y-1];
                    b = grid[x][y-1];
                    c = grid[x+1][y-1];
                    d = grid[x+1][y];
                    e = grid[x+1][y+1];
                    f = grid[x][y+1];
                    g = grid[x-1][y+1];
                    h = grid[x-1][y];
                    sumNeighbors = a + b + c + d + e + f + g + h;
                }

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
