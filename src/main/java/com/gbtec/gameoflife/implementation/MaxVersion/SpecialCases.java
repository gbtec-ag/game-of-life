package com.gbtec.gameoflife.implementation.MaxVersion;

public class SpecialCases {

    public static int specialCaseCheck(int[][] gridNewGen, int x, int y) {

        int[][] gridNewGenForSCC = new int[gridNewGen.length][gridNewGen.length];

        int a; int b; int c; int d; int e; int f; int g; int h;
        int sumNeighbors = 0;

        // Bc of (ArrayIndexOutOfBoundsException); Check outside grid -> impossible
        if (x == 0 && y == 0) {
            // Case 1: a, b, c, g, h out of bound
            d = gridNewGenForSCC[x+1][y];
            e = gridNewGenForSCC[x+1][y+1];
            f = gridNewGenForSCC[x][y+1];
            sumNeighbors = d + e + f;
        } else if (x > 0 && x < gridNewGenForSCC.length - 1 && y == 0) {
            // Case 2: a, b, c oob
            d = gridNewGenForSCC[x+1][y];
            e = gridNewGenForSCC[x+1][y+1];
            f = gridNewGenForSCC[x][y+1];
            g = gridNewGenForSCC[x-1][y+1];
            h = gridNewGenForSCC[x-1][y];
            sumNeighbors = d + e + f + g + h;
        } else if (x == gridNewGenForSCC.length - 1 && y == 0) {
            // Case 3: a, b, c, d, e oob
            f = gridNewGenForSCC[x][y+1];
            g = gridNewGenForSCC[x-1][y+1];
            h = gridNewGenForSCC[x-1][y];
            sumNeighbors = f + g + h;
        } else if (x == gridNewGenForSCC.length - 1 && y > 0 && y < gridNewGenForSCC.length - 1) {
            // Case 4: c, d, e oob
            a = gridNewGenForSCC[x-1][y-1];
            b = gridNewGenForSCC[x][y-1];
            f = gridNewGenForSCC[x][y+1];
            g = gridNewGenForSCC[x-1][y+1];
            h = gridNewGenForSCC[x-1][y];
            sumNeighbors = a + b + f + g + h;
        } else if (x == gridNewGenForSCC.length - 1 && y == gridNewGenForSCC.length - 1) {
            // Case 5: c, d, e, f, g oob
            a = gridNewGenForSCC[x-1][y-1];
            b = gridNewGenForSCC[x][y-1];
            h = gridNewGenForSCC[x-1][y];
            sumNeighbors = a + b + h;
        } else if (x > 0 && x < gridNewGenForSCC.length - 1 && y == gridNewGenForSCC.length -1) {
            // Case 6: e, f, g oob
            a = gridNewGenForSCC[x-1][y-1];
            b = gridNewGenForSCC[x][y-1];
            c = gridNewGenForSCC[x+1][y-1];
            d = gridNewGenForSCC[x+1][y];
            h = gridNewGenForSCC[x-1][y];
            sumNeighbors = a + b + c + d + h;
        } else if (x == 0 && y == gridNewGenForSCC.length - 1) {
            // Case 7: a, e, f, g, h oob
            b = gridNewGenForSCC[x][y-1];
            c = gridNewGenForSCC[x+1][y-1];
            d = gridNewGenForSCC[x+1][y];
            sumNeighbors = b + c + d;
        } else if (x == 0 && y < gridNewGenForSCC.length - 1) {
            // Case 8: a, g, h oob
            b = gridNewGenForSCC[x][y-1];
            c = gridNewGenForSCC[x+1][y-1];
            d = gridNewGenForSCC[x+1][y];
            e = gridNewGenForSCC[x+1][y+1];
            f = gridNewGenForSCC[x][y+1];
            sumNeighbors = b + c + d + e + f;
        } else if (x > 0 && x < gridNewGenForSCC.length - 1 && y > 0 && y < gridNewGenForSCC.length - 1) {
            a = gridNewGenForSCC[x-1][y-1];
            b = gridNewGenForSCC[x][y-1];
            c = gridNewGenForSCC[x+1][y-1];
            d = gridNewGenForSCC[x+1][y];
            e = gridNewGenForSCC[x+1][y+1];
            f = gridNewGenForSCC[x][y+1];
            g = gridNewGenForSCC[x-1][y+1];
            h = gridNewGenForSCC[x-1][y];
            sumNeighbors = a + b + c + d + e + f + g + h;
        }

        return sumNeighbors;
    }
}
