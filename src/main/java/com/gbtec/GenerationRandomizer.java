package com.gbtec;

import java.util.Random;

public class GenerationRandomizer {

    public static void randomizeGeneration(Generation generation, int size) {
        Random random = new Random();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                boolean randomBoolean = random.nextBoolean();
                if (randomBoolean)
                    generation.getCell(x, y).createCell();
            }
        }
    }
}
