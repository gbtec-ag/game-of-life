package com.gbtec;

public class GenerationPattern {

    public GenerationPattern() {

    }

    public static void createGlider(Generation generation) {
        generation.generation.get(0).get(0).setStatus(true);
        generation.generation.get(0).get(2).setStatus(true);
        generation.generation.get(1).get(1).setStatus(true);
        generation.generation.get(1).get(2).setStatus(true);
        generation.generation.get(2).get(1).setStatus(true);
    }

    public static void createFlower(Generation generation, int generationSize) {
        int centralPoint = (generationSize - 1) / 2;
        generation.generation.get(centralPoint - 1).get(centralPoint + 1).setStatus(true);
        generation.generation.get(centralPoint - 2).get(centralPoint + 1).setStatus(true);
        generation.generation.get(centralPoint - 3).get(centralPoint + 1).setStatus(true);
        generation.generation.get(centralPoint - 3).get(centralPoint).setStatus(true);
        generation.generation.get(centralPoint - 1).get(centralPoint - 1).setStatus(true);
        generation.generation.get(centralPoint - 2).get(centralPoint - 1).setStatus(true);
        generation.generation.get(centralPoint - 3).get(centralPoint - 1).setStatus(true);

        generation.generation.get(centralPoint + 1).get(centralPoint - 1).setStatus(true);
        generation.generation.get(centralPoint + 2).get(centralPoint - 1).setStatus(true);
        generation.generation.get(centralPoint + 3).get(centralPoint - 1).setStatus(true);
        generation.generation.get(centralPoint + 3).get(centralPoint).setStatus(true);
        generation.generation.get(centralPoint + 1).get(centralPoint + 1).setStatus(true);
        generation.generation.get(centralPoint + 2).get(centralPoint + 1).setStatus(true);
        generation.generation.get(centralPoint + 3).get(centralPoint + 1).setStatus(true);
    }
}
