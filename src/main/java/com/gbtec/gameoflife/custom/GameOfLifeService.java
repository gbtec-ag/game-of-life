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
        int[][] generationData = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        drawGeneration(generationData);

        // Instead of "int[][]" you can also use "boolean[][]"

        /* To generate random values use class "Random"
         *
         * Random random = new Random();
         * random.nextBoolean(); // Generates "true" or "false"
         * random.nextInt(0, 2); // Generates "0" or "1"
         */
    }

//    @Override
//    public void next() {}

//    @Override
//    public void play(int delayMs) {}

//    @Override
//    public void stop() {}
}
