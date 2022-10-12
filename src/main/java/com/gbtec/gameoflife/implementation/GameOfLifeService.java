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
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
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
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    // @formatter:on
    private int[][] firstGenerationData = generationData;
    private boolean printRunning;

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

     public int[][] returnGenerationData() {
        return generationData;
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
        generationData = GameRules.checkRules(generationData); // checking the rules for next generation
        drawGeneration(generationData);         // printing the grid
    }

    @Override
    public void play(int delayMs) {
        printRunning = true;
        while (printRunning) {
            int[][] newGenerationData = GameRules.checkRules(generationData); // checking the rules for next generation
            drawGeneration(newGenerationData);         // printing the grid
/*
            if (newGenerationData == generationData) {
                stop();
            } else {
                generationData = newGenerationData;
            }
*/
/*
            if (newGenerationData == firstGenerationData) {
                stop();
            } else {
                generationData = newGenerationData;
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
/*
 * Idea: a function where the method 'play' stops when
 * the game has reached a static position without change
 * -> two ways to execute: (1) comparing current Gen with
 * firstGen or (2) current Gen with last Gen
 * and
 * a toroid grid (execution?)
 * -> necessary to change the game rules to consider the
 * edges as a continuum into the other / opposite site of
 * the grid
 */
    @Override
    public void stop() {
        printRunning = false;
    }
}
