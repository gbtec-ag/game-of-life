package com.gbtec;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] raster = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int i = 0;
        int j = 0;
        // int k = 0;
        for(i = 0; i < raster.length; i++) {
            for(j = 0; j < raster.length; j++) {
                System.out.print(raster[i][j] + "  ");
            }
            System.out.println();
        }
    }

}
