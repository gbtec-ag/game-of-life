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
        boolean[][] generationData = GameOfLifeService.getDisplay().getGenerationData(Display.Type.MAIN);
        int displayMatrixSize = PropertiesLoader.getDisplayMatrixSize();
        boolean isInfiniteDisplay = PropertiesLoader.getInfiniteDisplay();
        int neighbourCount = 0;

        for (int relativeX = 0; relativeX < 3; relativeX++) {
            for (int relativeY = 0; relativeY < 3; relativeY++) {
                if (relativeX == 1 && relativeY == 1) {
                    // The current cell itself
                    continue;
                }

                int neighbourX = x + relativeX - 1;
                int neighbourY = y + relativeY - 1;

                if (neighbourX < 0 || neighbourX >= generationData.length || neighbourY < 0 || neighbourY >= displayMatrixSize) {
                    // Cell outside the matrix
                    if (isInfiniteDisplay) {
                        if (neighbourX < 0) {
                            neighbourX = generationData.length + neighbourX;
                        } else if (neighbourX >= generationData.length) {
                            neighbourX = neighbourX - generationData.length;
                        }

                        if (neighbourY < 0) {
                            neighbourY = displayMatrixSize + neighbourY;
                        } else if (neighbourY >= displayMatrixSize) {
                            neighbourY = neighbourY - displayMatrixSize;
                        }
                    } else {
                        continue;
                    }
                }

                // Check if neighbour is alive -> increment neighbour count
                neighbourCount += generationData[neighbourX][neighbourY] ? 1 : 0;

            }
        }

        return neighbourCount;
    }

    /**
     * Creates an empty generation data
     * @return The empty generation data
     */
    public static boolean[][] getEmptyGenerationData() {
        boolean[][] generationData = new boolean[PropertiesLoader.getDisplayMatrixSize()][PropertiesLoader.getDisplayMatrixSize()];

        for (int x = 0; x < PropertiesLoader.getDisplayMatrixSize(); x++) {
            for (int y = 0; y < PropertiesLoader.getDisplayMatrixSize(); y++) {
                generationData[x][y] = false;
            }
        }

        return generationData;
    }

}
