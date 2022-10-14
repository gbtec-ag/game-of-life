package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeCommandProxy;
import com.gbtec.gameoflife.implementation.MaxVersion.GameRules;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {
    // @formatter:off
    // Instead of "int[][]" you can also use "boolean[][]"

    /*
    private int[][] generationData = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
    };
    */

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

    // @formatter:on
    private int[][] firstGenerationData = generationData;
    private boolean printShouldRun = true;
        // Random object for random generation
    Random randomValues = new Random();

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void init(/* int y, int x*/) {

        // for initiation through web client
        /*
        private int[][] generationData = new int[y][x];
        */

        // Predetermined grid is printed
        drawGeneration(firstGenerationData);
        generationData = firstGenerationData;

        // Random generation
        /*
        for (int y = 0; y < generationData.length; y++) {
            for (int x = 0; x < generationData.length; x++) {
                // Storing random number either 0 or 1
                int randomValueForGeneration = randomValues.nextInt(2);

                // Matching random value to corresponding array index
                firstGenerationData[y][x] = randomValueForGeneration;
            }
        }
        // @formatter:off
        /* To generate random values, you can use the class "java.util.Random". This class is providing two functions
         * which are really handy for our case: "nextBoolean" and "nextInt".
         * - "nextBoolean" will give you randomly "true" or "false"
         * - "nextInt" will give you a random int number. To limit te numbers given by this method to "0" and "1"
         *   you must set the origin to 0 and the bound to 2 like "nextInt(0, 2)"

        // @formatter:on
        drawGeneration(firstGenerationData);
        generationData = firstGenerationData;
        // would be better placed in a single method with individual button -> separation of initiation and reset
        */
    }

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
        printShouldRun = true;
        while (printShouldRun) {
            int[][] newGenerationData = GameRules.createNewGen(generationData);
            drawGeneration(newGenerationData);

            /* Check if old array is the same as the new -> would mean that it's a static field and should stop
             * executing method 'play' to reduce computing power
             */
            if (Arrays.deepEquals(generationData, newGenerationData)) {
                stop();
            }

            generationData = newGenerationData;



            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }   // alternative: play button after push into stop button to force action

    @Override
    public void stop() {
        printShouldRun = false;
    }
}
