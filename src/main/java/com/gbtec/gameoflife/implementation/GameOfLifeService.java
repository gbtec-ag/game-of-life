package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeCommandProxy;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    int[][] generationData;
    int[][] tempGenerationData;
    @Override
    public void init() {
        // @formatter:off
        // Instead of "int[][]" you can also use "boolean[][]"
        int[][] generationData = {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        // @formatter:on

        drawGeneration(generationData);
        tempGenerationData = new int[generationData.length][generationData[0].length];
        // @formatter:off
        /* To generate random values, you can use the class "java.util.Random". This class is providing two functions
         * which are really handy for our case: "nextBoolean" and "nextInt".
         * - "nextBoolean" will give you randomly "true" or "false"
         * - "nextInt" will give you a random int number. To limit te numbers given by this method to "0" and "1"
         *   you must set the origin to 0 and the bound to 2 like "nextInt(0, 2)"
         */
        // @formatter:on
    }
    public void next() {

        for (int y = 0; y < generationData.length; y++) {
            for (int x = 0; x < generationData[y].length; x++) {

                int neighborCount = countNeighbors(y, x);

                applyRules(neighborCount, y, x);
            }
        }
        // other for loop bc all changes are done at this point, y and x are needed for copy
        for (int y = 0; y < generationData.length; y++) {
            for(int x = 0; x < generationData[y].length; x++){
                generationData[y][x] = tempGenerationData[y][x];
            }
        }

        drawGeneration(generationData);
        
    }
    private int countNeighbors(int y, int x) {
        int neighborCount = 0;

        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {

                int neighborY = y + r;
                int neighborX = x + c;

                // prove if neigbor is in bounds
                if (neighborY > -1 && neighborX > -1 && neighborY < generationData.length && neighborX < generationData.length) {
                    // prove if neighbor is alive
                    if (generationData[neighborY][neighborX] == 1) {
                        if (r + c != 0){ // every neighbor beside [1][-1], [-1][1], [0][0]
                            neighborCount++;
                        } else if (r == -1 || r == 1) { // every neighbor beside [0][0] (not counts himself)
                            neighborCount++;
                        }
                    }
                }
            }
        }
        return neighborCount;
    }
    private void applyRules(int neighborCount, int y, int x) {
        // proving rules in generationData, applying in tempGenerationData
        if(generationData[y][x] == 1) {
            if(neighborCount <2) {
                tempGenerationData[y][x] = 0;
            } else if (neighborCount > 3) {
                tempGenerationData[y][x] = 0;
            } else if (neighborCount == 2 || neighborCount == 3) {
                tempGenerationData[y][x] = 1;
            }
        } else if (generationData[y][x] == 0 && neighborCount == 3) {
            tempGenerationData[y][x] = 1;
        }
    }
}
