package com.gbtec.gameoflife.implementation;

import java.util.Random;

/**
 * Some tool methods
 */
public class Tools {

    /**
     * Generates a random generation data matrix with the given size
     *
     * @param matrixSize The size of the matrix
     * @return The generated matrix
     */
    public static boolean[][] generateRandomGenerationData(int matrixSize) {

        boolean[][] generationData = new boolean[matrixSize][matrixSize];

        // Generate Lines
        for (int i = 0; i < matrixSize; i++) {
            // Generate values in line
            for (int j = 0; j < matrixSize; j++) {
                boolean random = new Random().nextBoolean();
                generationData[i][j] = random;
            }
        }

        return generationData;

    }

    /**
     * Returns the number of living neighbours for the given coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @return The number of living neighbours
     */
    public static int getLivingNeighbourCount(int x, int y) {
        boolean[][] generationData = GameOfLifeService.getDisplay().getCurrentGenerationData();
        int neighbourCount = 0;

        for (int relativeX = 0; relativeX < 3; relativeX++) {
            for (int relativeY = 0; relativeY < 3; relativeY++) {
                if (relativeX == 1 && relativeY == 1) {
                    // The current cell itself
                    continue;
                }

                int neighbourX = x + relativeX - 1;
                int neighbourY = y + relativeY - 1;

                if (neighbourX < 0 || neighbourX >= generationData.length || neighbourY < 0 || neighbourY >= Integer.parseInt(PropertiesLoader.getProperty("displayMatrixSize"))) {
                    // Outside the matrix
                    continue;
                }

                // Check if neighbour is alive -> increment neighbour count
                neighbourCount += generationData[neighbourX][neighbourY] ? 1 : 0;

            }
        }

        return neighbourCount;
    }

}
