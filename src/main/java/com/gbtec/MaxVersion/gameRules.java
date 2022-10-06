package com.gbtec.MaxVersion;

import static com.gbtec.MaxVersion.presentation.i;
import static com.gbtec.MaxVersion.presentation.j;

public class gameRules {

    static int a = presentation.grid[i--][j--];
    static int b = presentation.grid[i][j--];
    static int c = presentation.grid[i++][j--];
    static int d = presentation.grid[i--][j];
    static int e = presentation.grid[i++][j];
    static int f = presentation.grid[i--][j++];
    static int g = presentation.grid[i][j++];
    static int h = presentation.grid[i++][j++];
    static int sumNeighbors = a+b+c+d+e+f+g+h; // Problem??
    static int k;

    public static void checkRules() {
        for(k = 0; k < 8; k++) {
            if(sumNeighbors >= 4 || sumNeighbors <= 1) {
                presentation.grid[i][j] = 0;
            } else if(sumNeighbors == 2 || sumNeighbors == 3) {
                presentation.grid[i][j] = 1;
            } // Jan fragen wegen NullPointerException!!
        }
    }

}
