package com.gbtec.gameoflife.custom;

import com.gbtec.gameoflife.basic.GameOfLifeCommandProxy;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

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

        drawGeneration(generationData);

        /* To generate random values, you can use the class "java.util.Random". This class is providing two functions
         * which are really handy for our case: "nextBoolean" and "nextInt".
         * - "nextBoolean" will give you randomly "true" or "false"
         * - "nextInt" will give you a random int number. To limit te numbers given by this method to "0" and "1"
         *      you must set the origin to 0 and the bound to 2 like "nextInt(0, 2)"
         */
        // @formatter:on
    }
}
