package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeCommandProxy;
import com.gbtec.gameoflife.implementation.MaxVersion.GameRules;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {
    // @formatter:off
    // Instead of "int[][]" you can also use "boolean[][]"

    private int[][] generationData = new int[][]{
            {0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    /*
    private int[][] generationData = new int[][]{
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    };
    */
    // @formatter:on
    private int[][] firstGenerationData = generationData;
    private boolean printRunning;
    // private int equalIndex = 0;

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void init() {
        drawGeneration(firstGenerationData);
        generationData = firstGenerationData;
    }
        // @formatter:off
        /* To generate random values, you can use the class "java.util.Random". This class is providing two functions
         * which are really handy for our case: "nextBoolean" and "nextInt".
         * - "nextBoolean" will give you randomly "true" or "false"
         * - "nextInt" will give you a random int number. To limit te numbers given by this method to "0" and "1"
         *   you must set the origin to 0 and the bound to 2 like "nextInt(0, 2)"
         */
        // @formatter:on

    @Override
    public void next() {
        generationData = GameRules.createNewGen(generationData); // checking the rules for next generation
        drawGeneration(generationData);         // printing the grid
    }

    @Override
    public void play(int delayMs) {
        /* Possibility that you also input if u want a toroid grid when calling the method (either 'play' or 'init'
        *  which would make more sense)
        */
        printRunning = true;
        while (printRunning) {
            int[][] newGenerationData = GameRules.createNewGen(generationData); // checking the rules for next generation
            drawGeneration(newGenerationData);         // printing the grid

            generationData = newGenerationData;

/*
            for (int i = 0; i < newGenerationData.length - 1; i++) {
                for (int j = 0; j < newGenerationData.length - 1; j++) {
                    if (newGenerationData[i][j] != generationData[i][j]) {
                        // action
                    } else // stop();
                }
            }
*/
// alternative: play button after push into stop button to force action

            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void stop() {
        printRunning = false;
    }
}
